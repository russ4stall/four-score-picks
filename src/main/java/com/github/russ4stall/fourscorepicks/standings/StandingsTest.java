package com.github.russ4stall.fourscorepicks.standings;

import com.github.russ4stall.fourscorepicks.utility.WeekCalculator;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;

import java.util.ArrayList;
import java.util.List;




/**
 * Date: 10/29/13
 * Time: 1:15 PM
 *
 * @author Russ Forstall
 */
public class StandingsTest {

    public static void main(String[] args) {

        List<WeekStat> weekStats = new ArrayList<WeekStat>();

        WeekCalculator weekCalculator = new WeekCalculator();
        int weekNum = weekCalculator.getWeekOfSeason();

        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList();
        for (User user : users){
            user.calculateScores();
        }

        for (int  i = 1; i<= weekNum; i++){
            Roster weekRoster = new Roster(i, new ArrayList<User>(users));
            weekRoster.sortByWeekScore();

            Roster seasonRoster = new Roster(i, new ArrayList<User>(users));
            seasonRoster.sortBySeasonScore();

            WeekStat weekStat = new WeekStat(i, weekRoster, seasonRoster);
            weekStats.add(weekStat);
        }







        /*Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();


        List<Roster> rosterList = new ArrayList<Roster>();
        WeekCalculator weekCalculator = new WeekCalculator();
        int weekNum = weekCalculator.getWeekOfSeason();

        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList();
        for (User user : users){
            user.calculateScores();
        }

        //test
        for (int  i = 1; i<= weekNum; i++){
            Roster roster = new Roster(i, new ArrayList<User>(users));
            roster.sortByWeekScore();
            rosterList.add(roster);
            System.out.println("added");
        }

        int i = 0;
        for (Roster roster : rosterList){
            System.out.println("-----------WEEK " + roster.getWeek());

            for (User user : roster.getUsers()){
                System.out.println(user.getName() + " \t" + user.getWeeklyScores().get(i));
            }
            i++;
        }
        stopwatch.stop();
        System.out.println(stopwatch);*/


    }



}
