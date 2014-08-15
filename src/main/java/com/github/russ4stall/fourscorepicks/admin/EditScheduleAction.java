package com.github.russ4stall.fourscorepicks.admin;

import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.team.Team;
import com.github.russ4stall.fourscorepicks.utility.WeekCalculator;
import com.github.russ4stall.fourscorepicks.game.dao.GameDao;
import com.github.russ4stall.fourscorepicks.game.dao.GameDaoImpl;
import com.github.russ4stall.fourscorepicks.user.RosterFactory;
import com.github.russ4stall.fourscorepicks.user.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Date: 8/7/13
 * Time: 5:16 PM
 *
 * @author Russ Forstall
 */
public class EditScheduleAction extends ActionSupport implements SessionAware, Preparable {
    private Map<String, Object> session;
    private List<Game> gameList = new ArrayList<Game>();
    private int gameId;
    private int awayTeamScore;
    private int homeTeamScore;
    private int awayTeamId;
    private int homeTeamId;
    private String gameTime;
    private String gameDay;
    private int weekNumNewGame;
    private int weekNum;
    private int weekNumHotGame;
    private int gameIdHotGame;
    private User user;
    GameDao gameDao;
    private List<User> seasonRoster;


    public void prepare() {
        gameDao = new GameDaoImpl();
        WeekCalculator weekCalculator = new WeekCalculator();
        weekNum = weekCalculator.getWeekOfSeason();
        gameList = gameDao.getAllGames();
        user = (User) session.get("user");
        RosterFactory rosterFactory = new RosterFactory();
        seasonRoster = rosterFactory.getSeasonRoster();


    }

    @Override
    public String input() throws Exception {


        return INPUT;
    }

    public String setResult() {
        Game game = new Game();
        game.setId(gameId);

        game.setAwayTeamScore(awayTeamScore);
        game.setHomeTeamScore(homeTeamScore);

        Team winningTeam = new Team();

        if(awayTeamScore > homeTeamScore){
            winningTeam.setId(awayTeamId);
        } else if (awayTeamScore < homeTeamScore){
            winningTeam.setId(homeTeamId);
        } else {
            winningTeam.setId(0);
        }
        game.setWinningTeam(winningTeam);

        gameDao.setResult(game);

        return SUCCESS;
    }

    public String addGame() {
        if (user != null && user.isAdmin()) {
            String dateTime = getGameDay() + " " + getGameTime();
            gameDao.addGame(getAwayTeamId(), getHomeTeamId(), getWeekNumNewGame(), dateTime);
        }
        return SUCCESS;
    }

    public String removeGame() {
        if (user != null && user.isAdmin()) {
            gameDao.removeGame(gameId);
        }

        return SUCCESS;
    }

    public String addHotGame() {
        if (user != null && user.isAdmin()) {
            gameDao.addHotGame(weekNumHotGame, gameIdHotGame);
        }

        return SUCCESS;
    }

    public String removeHotGame() {
        if (user != null && user.isAdmin()) {
            gameDao.removeHotGame(weekNumHotGame, gameIdHotGame);
        }

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getGameDay() {
        return gameDay;
    }

    public void setGameDay(String gameDay) {
        this.gameDay = gameDay;
    }

    public int getWeekNumNewGame() {
        return weekNumNewGame;
    }

    public void setWeekNumNewGame(int weekNumNewGame) {
        this.weekNumNewGame = weekNumNewGame;
    }

    public int getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public List<User> getSeasonRoster() {
        return seasonRoster;
    }

    public void setWeekNumHotGame(int weekNumHotGame) {
        this.weekNumHotGame = weekNumHotGame;
    }

    public void setGameIdHotGame(int gameIdHotGame) {
        this.gameIdHotGame = gameIdHotGame;
    }
}

