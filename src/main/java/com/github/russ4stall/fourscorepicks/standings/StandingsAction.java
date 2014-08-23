package com.github.russ4stall.fourscorepicks.standings;

import com.github.russ4stall.fourscorepicks.content.Comment;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDao;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDaoImpl;
import com.github.russ4stall.fourscorepicks.utility.WeekCalculator;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;
import com.opensymphony.xwork2.ActionSupport;

import java.util.*;



/**
 * Date: 7/25/13
 * Time: 3:38 PM
 *
 * @author Russ Forstall
 */
public class StandingsAction extends ActionSupport {

    private WeekStat weekStat;
    private Roster seasonRoster;
    private List<Comment> comments;
    private String commentText;
    private int userId;

    public String input(){

        ContentDao contentDao = new ContentDaoImpl();
        comments = contentDao.getCommentList();

        WeekCalculator weekCalculator = new WeekCalculator();
        int weekNum = weekCalculator.getWeekOfSeason();

        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList();
        for (User user : users){
            user.calculateScores();
        }

        seasonRoster = new Roster(weekNum, users);
        seasonRoster.sortBySeasonScore();

        Roster tempWeekRoster = new Roster(weekNum, new ArrayList<User>(users));
        tempWeekRoster.sortByWeekScore();

        Roster tempSeasonRoster = new Roster(weekNum, new ArrayList<User>(users));
        tempSeasonRoster.sortBySeasonScore();

        weekStat = new WeekStat(weekNum, tempWeekRoster, tempSeasonRoster);


        return INPUT;
    }

    public String addComment() {
        ContentDao contentDao = new ContentDaoImpl();
        contentDao.addComment(commentText, userId);

        return SUCCESS;
    }

    public Roster getSeasonRoster() {
        return seasonRoster;
    }

    public WeekStat getWeekStat() {
        return weekStat;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
