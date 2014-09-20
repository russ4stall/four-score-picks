package com.github.russ4stall.fourscorepicks.admin;

import com.github.russ4stall.fourscorepicks.content.dao.ContentDao;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDaoImpl;
import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.utility.WeekCalculator;
import com.github.russ4stall.fourscorepicks.game.dao.GameDao;
import com.github.russ4stall.fourscorepicks.game.dao.GameDaoImpl;
import com.github.russ4stall.fourscorepicks.user.RosterFactory;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.SessionAware;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Date: 7/19/13
 * Time: 8:44 AM
 *
 * @author Russ Forstall
 * testing vcs
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
    private boolean sendAsEmail;

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

        if(sendAsEmail){
            UserDao userDao = new UserDaoImpl();
            List<User> users = userDao.getUserList();
            for (User recipients : users){
                sendNewsEmail(recipients.getEmail());
            }
        }

        return "redirectHome";
    }


    private void sendNewsEmail(String userEmail){
        Properties props = new Properties();

        final String host = "smtp.mandrillapp.com";
        final String smtpUsername = "russ4stall@gmail.com";
        final String password = "ACCGYGlUROzLTI7oVQJBsQ";

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", smtpUsername );
        props.put("mail.smtp.password", password);

        Session session = Session.getDefaultInstance(props, null);

        session.setPasswordAuthentication(new URLName("smtp", host, -1, null, smtpUsername, null),
                new PasswordAuthentication(smtpUsername, password));


        String footer = "\n \n \n \nThis is an automated email. Do not respond to this address";

        String msgBody = newsText + footer;

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("no-reply@fourscorepicks.com", "Four Score Picks"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(userEmail));
            msg.setSubject("Four Score Picks: News Update!");
            //msg.setText(msgBody);
            msg.setContent(msgBody, "text/html");
            Transport.send(msg);

        } catch (AddressException e) {
            System.out.println(e);
        } catch (MessagingException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }


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

    public void setSendAsEmail(boolean sendAsEmail) {
        this.sendAsEmail = sendAsEmail;
    }
}
