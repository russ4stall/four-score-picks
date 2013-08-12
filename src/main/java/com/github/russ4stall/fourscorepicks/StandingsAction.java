package com.github.russ4stall.fourscorepicks;

import com.github.russ4stall.fourscorepicks.game.WeekCalculator;
import com.github.russ4stall.fourscorepicks.user.RosterFactory;
import com.github.russ4stall.fourscorepicks.user.User;
import com.opensymphony.xwork2.ActionSupport;

import java.util.*;

/**
 * Date: 7/25/13
 * Time: 3:38 PM
 *
 * @author Russ Forstall
 */
public class StandingsAction extends ActionSupport {
    private List<User> weekRoster;
    private List<User> seasonRoster;
    private int weekNum;



    public String input(){
        RosterFactory rosterFactory = new RosterFactory();
        rosterFactory.calculateScores();
        seasonRoster = rosterFactory.getSeasonRoster();
        weekRoster = rosterFactory.getWeekRoster();
        WeekCalculator weekCalculator = new WeekCalculator();
        weekNum = weekCalculator.getPreviousWeekOfSeason();
        return INPUT;
    }



    public List<User> getWeekRoster() {
        return weekRoster;
    }

    public List<User> getSeasonRoster() {
        return seasonRoster;
    }

    public int getWeekNum() {
        return weekNum;
    }
}
