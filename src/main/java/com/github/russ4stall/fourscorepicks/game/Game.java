package com.github.russ4stall.fourscorepicks.game;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date: 7/19/13
 * Time: 9:12 AM
 *
 * @author Russ Forstall
 */
public class Game {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private String date;
    private Timestamp gameTime;
    private int week;
    private int homeTeamScore;
    private int awayTeamScore;
    private Team winningTeam;
    private boolean isHotGame;
    private boolean gameHasStarted;


    public boolean isGameHasStarted() {
        gameHasStarted = false;
        Date date = new Date();
        if(gameTime.before(new Timestamp(date.getTime()))){
            gameHasStarted = true;
        }
        return gameHasStarted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getWinningTeam() {
       /* if (homeTeamScore > awayTeamScore){
            winningTeam = homeTeam;
        } else if (awayTeamScore > homeTeamScore){
            winningTeam = awayTeam;
        } else {
            winningTeam = null;
        }*/
        return winningTeam;
    }

    public void setWinningTeam(Team winningTeam) {
        this.winningTeam = winningTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public boolean isHotGame() {
        return isHotGame;
    }

    public void setHotGame(boolean hotGame) {
        isHotGame = hotGame;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Timestamp getGameTime() {
        return gameTime;
    }

    public void setGameTime(Timestamp gameTime) {
        this.gameTime = gameTime;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }


    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE h:mm a");
        date = simpleDateFormat.format(gameTime);

        return date;
    }
}
