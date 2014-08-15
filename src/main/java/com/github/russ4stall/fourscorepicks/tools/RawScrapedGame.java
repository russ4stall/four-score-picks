package com.github.russ4stall.fourscorepicks.tools;

/**
 * Created by russ on 8/14/14.
 */
public class RawScrapedGame {
    String date;
    String awayTeam;
    String homeTeam;
    String awayTeamScore;
    String homeTeamScore;


    @Override
    public String toString() {
        return "RawScrapedGame{" +
                "date='" + date + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeamScore='" + awayTeamScore + '\'' +
                ", homeTeamScore='" + homeTeamScore + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
