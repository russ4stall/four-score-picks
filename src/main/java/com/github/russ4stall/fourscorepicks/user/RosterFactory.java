package com.github.russ4stall.fourscorepicks.user;

import com.github.russ4stall.fourscorepicks.utility.WeekCalculator;
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
    private List weekRosterList;
    private List seasonRosterList;


    public void calculateScores(){
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList();
        weekRoster = new ArrayList<User>();
        seasonRoster = new ArrayList<User>();
        WeekCalculator weekCalculator = new WeekCalculator();
        UserScoreCalculator userScoreCalculator = new UserScoreCalculatorImpl();


        for(User user : users){
            //user.setWeekScore(userScoreCalculator.getWeekScore(user.getId(), weekCalculator.getPreviousWeekOfSeason()));
            user.setWeekScore(userScoreCalculator.getWeekScore(user.getId(), weekCalculator.getWeekOfSeason()));
            user.setSeasonScore(userScoreCalculator.getSeasonScore(user.getId()));
            weekRoster.add(user);
            seasonRoster.add(user);
        }

        Collections.sort(this.getSeasonRoster(), new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return (user1.getSeasonScore().compareTo(user2.getSeasonScore())) * -1;
            }
        });

        Collections.sort(this.getWeekRoster(), new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return (user1.getWeekScore().compareTo(user2.getWeekScore())) * -1;
            }
        });

    }

    public void calculateLists(){
        weekRosterList = new ArrayList();
        seasonRosterList = new ArrayList();
        WeekCalculator weekCalculator = new WeekCalculator();
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList();

        for(int i=weekCalculator.getWeekOfSeason(); i>=1; i--){

            List<User> week = new ArrayList<User>();
            List<User> season = new ArrayList<User>();

            UserScoreCalculator userScoreCalculator = new UserScoreCalculatorImpl();


            for(User user : users){
                user.setWeekScore(userScoreCalculator.getWeekScore(user.getId(), i));
                user.setSeasonScore(userScoreCalculator.getSeasonScore(user.getId(), i));
                week.add(user);
                season.add(user);
            }

            Collections.sort(season, new Comparator<User>() {
                @Override
                public int compare(User user1, User user2) {
                    return (user1.getSeasonScore().compareTo(user2.getSeasonScore())) * -1;
                }
            });

            Collections.sort(week, new Comparator<User>() {
                @Override
                public int compare(User user1, User user2) {
                    return (user1.getWeekScore().compareTo(user2.getWeekScore())) * -1;
                }
            });

                weekRosterList.add(week);
                seasonRosterList.add(season);
        }

    }

    public List<User> getWeekRoster() {
        return weekRoster;
    }

    public List<User> getSeasonRoster() {
        return seasonRoster;
    }

    public List getSeasonRosterList() {
        return seasonRosterList;
    }

    public List getWeekRosterList() {
        return weekRosterList;
    }
}
