package com.github.russ4stall.fourscorepicks.pick;

import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.game.WeekCalculator;
import com.github.russ4stall.fourscorepicks.game.dao.GameDao;
import com.github.russ4stall.fourscorepicks.game.dao.GameDaoImpl;
import com.github.russ4stall.fourscorepicks.pick.GameAndPick;
import com.github.russ4stall.fourscorepicks.pick.Pick;
import com.github.russ4stall.fourscorepicks.pick.dao.PickDao;
import com.github.russ4stall.fourscorepicks.pick.dao.PickDaoImpl;
import com.github.russ4stall.fourscorepicks.user.RosterFactory;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.UserScoreCalculator;
import com.github.russ4stall.fourscorepicks.user.UserScoreCalculatorImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Date: 7/19/13
 * Time: 11:28 PM
 *
 * @author Russ Forstall
 */
public class MyPicksAction extends ActionSupport implements SessionAware, Preparable {
    private List<Game> gameList;
    private List<GameAndPick> userResultList;
    private List<GameAndPick> gameAndPickList;
    private GameDao gameDao = new GameDaoImpl();
    private PickDao pickDao = new PickDaoImpl();
    private Map<String, Object> session;
    private Map<Integer, Integer> picks;
    private User user;
    private List<User> seasonRoster;
    private Integer gameId;
    WeekCalculator weekCalculator = new WeekCalculator();


    @Override
    public void prepare() throws Exception {
        RosterFactory rosterFactory = new RosterFactory();
        rosterFactory.calculateScores();
        seasonRoster = rosterFactory.getSeasonRoster();
        user = (User) session.get("user");
        gameList = gameDao.getGamesByWeek(weekCalculator.getWeekOfSeason());
        userResultList = pickDao.getGameAndPickByWeek(user.getId(), weekCalculator.getPreviousWeekOfSeason());
        gameAndPickList = pickDao.getGameAndPickByWeek(user.getId(), weekCalculator.getWeekOfSeason());

    }

    public String input() {
        UserScoreCalculator userScoreCalculator = new UserScoreCalculatorImpl();


        userScoreCalculator.getWeekScore(user.getId(), weekCalculator.getPreviousWeekOfSeason());


        return INPUT;
    }

    public String execute() {

        List<Pick> pickList = new ArrayList<Pick>();
        if (picks != null) {
            for (Game game : gameList) {
                if (picks.get(game.getId()) != null) {
                    Pick pick = new Pick();
                    pick.setGameId(game.getId());
                    pick.setUserId(user.getId());
                    pick.setPickTeamId(picks.get(game.getId()));
                    pickList.add(pick);
                }
            }
            PickDao pickDao = new PickDaoImpl();
            pickDao.setPicks(pickList);
        }
        return SUCCESS;
    }

    public String deletePick() {
        if(gameId!=null){
        pickDao.deletePick(user.getId(), gameId);
        }

        return SUCCESS;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public List<User> getSeasonRoster() {
        return seasonRoster;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map<Integer, Integer> getPicks() {
        return picks;
    }

    public void setPicks(Map<Integer, Integer> picks) {
        this.picks = picks;
    }


    public List<GameAndPick> getGameAndPickList() {
        return gameAndPickList;
    }

    public void setGameAndPickList(List<GameAndPick> gameAndPickList) {
        this.gameAndPickList = gameAndPickList;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public List<GameAndPick> getUserResultList() {
        return userResultList;
    }

    public void setUserResultList(List<GameAndPick> userResultList) {
        this.userResultList = userResultList;
    }
}
