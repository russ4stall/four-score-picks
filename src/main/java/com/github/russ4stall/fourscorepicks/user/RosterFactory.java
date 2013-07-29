package com.github.russ4stall.fourscorepicks.user;

import com.github.russ4stall.fourscorepicks.game.WeekCalculator;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.UserScoreCalculator;
import com.github.russ4stall.fourscorepicks.user.UserScoreCalculatorImpl;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Date: 7/26/13
 * Time: 4:15 PM
 *
 * @author Russ Forstall
 */
public class RosterFactory {
    private List<User> weekRoster;
    private List<User> seasonRoster;
    UserDao userDao = new UserDaoImpl();
    UserScoreCalculator userScoreCalculator = new UserScoreCalculatorImpl();
    WeekCalculator weekCalculator = new WeekCalculator();

    public void calculateScores(){
        List<User> users = userDao.getUserList();
        weekRoster = new ArrayList<User>();
        seasonRoster = new ArrayList<User>();

        for(User user : users){
            user.setWeekScore(userScoreCalculator.getWeekScore(user.getId(), weekCalculator.getPreviousWeekOfSeason()));
            user.setSeasonScore(userScoreCalculator.getSeasonScore(user.getId()));
            weekRoster.add(user);
            seasonRoster.add(user);
        }

        Collections.sort(seasonRoster, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return (user1.getSeasonScore().compareTo(user2.getSeasonScore())) * -1;
            }
        });

        Collections.sort(weekRoster, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return (user1.getWeekScore().compareTo(user2.getWeekScore())) * -1;
            }
        });

    }

    public List<User> getWeekRoster() {
        return weekRoster;
    }

    public List<User> getSeasonRoster() {
        return seasonRoster;
    }
}
