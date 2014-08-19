package com.github.russ4stall.fourscorepicks.tools;

import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.team.TeamEnum;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This object is the product of scraping the NFL website for scores.
 *
 * Created by russ on 8/14/14.
 */
public class RawScrapedGame {
    int week;
    String date;
    String time;
    String awayTeam;
    String homeTeam;
    String awayTeamScore;
    String homeTeamScore;


    @Override
    public String toString() {
        return "RawScrapedGame{" +
                "week=" + week +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeamScore='" + awayTeamScore + '\'' +
                ", homeTeamScore='" + homeTeamScore + '\'' +
                '}';
    }


    /**
     * This compares a raw scraped game with an existing game.
     * For validating that scraped scores belong to the right game.
     *
     * @param game
     * @return true if they are referring to the same actual game.
     */
    public boolean matches(Game game) {
        //Do away teams match?
        if (TeamEnum.getEnum(this.awayTeam) != TeamEnum.getEnum(game.getAwayTeam().getName()))
            return false;
        //Do home teams match?
        if (TeamEnum.getEnum(this.homeTeam) != TeamEnum.getEnum(game.getHomeTeam().getName()))
            return false;
        //Do week numbers match?
        if (game.getWeek() != this.week)
            return false;

        return true;
    }

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd H:mm:ss");
        DateTime dateTime = DateTime.parse("2014-09-07 13:00:00", dateTimeFormatter);
        System.out.println(dateTime);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("EEE, MMM d");
        DateTime rawDateTime = DateTime.parse("Sun, Sep 7", formatter);
        System.out.println(rawDateTime.toString(formatter));
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(String awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(String homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }
}
