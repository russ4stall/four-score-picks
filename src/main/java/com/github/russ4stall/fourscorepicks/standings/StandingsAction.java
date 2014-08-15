package com.github.russ4stall.fourscorepicks.standings;

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

    private List<WeekStat> weekStats;
    private Roster seasonRoster;

    public String input(){

        weekStats = new ArrayList<WeekStat>();

        WeekCalculator weekCalculator = new WeekCalculator();
        int weekNum = weekCalculator.getWeekOfSeason();

        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList();
        for (User user : users){
            user.calculateScores();
        }

        seasonRoster = new Roster(weekNum, users);
        seasonRoster.sortBySeasonScore();

        for (int  i = weekNum; i>= 1; i--){
            Roster tempWeekRoster = new Roster(i, new ArrayList<User>(users));
            tempWeekRoster.sortByWeekScore();

            Roster tempSeasonRoster = new Roster(i, new ArrayList<User>(users));
            tempSeasonRoster.sortBySeasonScore();

            WeekStat weekStat = new WeekStat(i, tempWeekRoster, tempSeasonRoster);
            weekStats.add(weekStat);
        }

        return INPUT;
    }

    public Roster getSeasonRoster() {
        return seasonRoster;
    }

    public List<WeekStat> getWeekStats() {
        return weekStats;
    }
}
