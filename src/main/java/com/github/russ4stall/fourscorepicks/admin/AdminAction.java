package com.github.russ4stall.fourscorepicks.admin;

import com.github.russ4stall.fourscorepicks.content.ContentDao;
import com.github.russ4stall.fourscorepicks.content.ContentDaoImpl;
import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.game.WeekCalculator;
import com.github.russ4stall.fourscorepicks.game.dao.GameDao;
import com.github.russ4stall.fourscorepicks.game.dao.GameDaoImpl;
import com.github.russ4stall.fourscorepicks.user.RosterFactory;
import com.github.russ4stall.fourscorepicks.user.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.SessionAware;

import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Date: 7/19/13
 * Time: 8:44 AM
 *
 * @author Russ Forstall
 */
public class AdminAction extends ActionSupport implements SessionAware, Preparable {
    List<Game> gameList = new ArrayList<Game>();
    GameDao gameDao = new GameDaoImpl();
    private int homeTeamId;
    private int awayTeamId;
    private int weekNumHotGame;
    private int gameIdHotGame;
    private int weekNumNewGame;
    private String gameTime;
    private String gameDay;
    private Map<String, Object> session;
    private User user = null;
    private List<User> seasonRoster;
    WeekCalculator weekCalculator = new WeekCalculator();
    private String newsText;
    private int weekNum;

    @Override
    public void prepare() throws Exception {
        user = (User)session.get("user");
        RosterFactory rosterFactory = new RosterFactory();
        rosterFactory.calculateScores();
        seasonRoster = rosterFactory.getSeasonRoster();
        weekNum = weekCalculator.getWeekOfSeason();

    }

    public String input(){
        gameList = gameDao.getGamesByWeek(weekCalculator.getWeekOfSeason());
        return INPUT;
    }

    public String postNews(){
        ContentDao contentDao = new ContentDaoImpl();
        contentDao.addNews(getNewsText(), user);

        return "redirectHome";
    }



    public String addGame(){
        String dateTime = getGameDay() + " " + getGameTime();
        gameDao.addGame(getAwayTeamId(), getHomeTeamId(), getWeekNumNewGame(), dateTime);
        return SUCCESS;
    }

    public String addHotGame(){
        if(user != null && user.isAdmin()){
            gameDao.addHotGame(weekNumHotGame, gameIdHotGame);
        }

        return SUCCESS;
    }

    public String removeHotGame(){
        if(user != null && user.isAdmin()){
            gameDao.removeHotGame(weekNumHotGame, gameIdHotGame);
        }

        return SUCCESS;
    }

    public String execute() {

        return SUCCESS;
    }

    public List<User> getSeasonRoster() {
        return seasonRoster;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }



    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public String getNewsText() {
        return newsText;
    }

    public String getGameDay() {
        return gameDay;
    }

    public void setGameDay(String gameDay) {
        this.gameDay = gameDay;
    }

    public void setWeekNumHotGame(int weekNumHotGame) {
        this.weekNumHotGame = weekNumHotGame;
    }

    public void setGameIdHotGame(int gameIdHotGame) {
        this.gameIdHotGame = gameIdHotGame;
    }

    public void setWeekNumNewGame(int weekNumNewGame) {
        this.weekNumNewGame = weekNumNewGame;
    }

    public int getWeekNumNewGame() {
        return weekNumNewGame;
    }

    public int getWeekNum() {
        return weekNum;
    }
}
