package com.github.russ4stall.fourscorepicks.standings;

/**
 * Date: 11/1/13
 * Time: 10:40 AM
 *
 * @author Russ Forstall
 */
public class WeekStat {
    private Roster weekRoster;
    private Roster seasonRoster;
    private int weekNum;

    public WeekStat(int weekNum, Roster weekRoster, Roster seasonRoster) {
        this.weekRoster = weekRoster;
        this.seasonRoster = seasonRoster;
        this.weekNum = weekNum;
    }


    public Roster getWeekRoster() {
        return weekRoster;
    }

    public Roster getSeasonRoster() {
        return seasonRoster;
    }

    public int getWeekNum() {
        return weekNum;
    }
}
