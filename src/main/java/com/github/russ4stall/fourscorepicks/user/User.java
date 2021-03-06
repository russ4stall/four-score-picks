package com.github.russ4stall.fourscorepicks.user;

import com.github.russ4stall.fourscorepicks.game.WeekCalculator;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 7/15/13
 * Time: 10:48 AM
 *
 * @author Russ Forstall
 */
//@Entity
public class User {
    private String name;
    private String email;
    private String password;
    private boolean admin;
    private Integer weekScore;
    private List<Integer> weeklyScores;
    private List<Integer> weeklySeasonScores;
    private Integer seasonScore;
    private int id;



    public void calculateScores(){
        UserScoreCalculator userScoreCalculator = new UserScoreCalculatorImpl();
        WeekCalculator weekCalculator = new WeekCalculator();
        weeklyScores = new ArrayList<Integer>();
        weeklySeasonScores = new ArrayList<Integer>();
        for (int i = 1; i <= weekCalculator.getWeekOfSeason(); i++) {
            weekScore = userScoreCalculator.getWeekScore(id, i);
            weeklyScores.add(weekScore);

            seasonScore = userScoreCalculator.getSeasonScore(id, i);
            weeklySeasonScores.add(seasonScore);
        }
    }

    public Integer getSeasonScoreOfWeek(int i) {
        return weeklySeasonScores.get(i-1);
    }

    public Integer getScoreOfWeek(int i) {
        return weeklyScores.get(i-1);
    }

    public List<Integer> getWeeklySeasonScores() {
        return weeklySeasonScores;
    }


    public void setWeeklySeasonScores(List<Integer> weeklySeasonScores) {
        this.weeklySeasonScores = weeklySeasonScores;
    }

    public List<Integer> getWeeklyScores() {
        return weeklyScores;
    }

    public void setWeeklyScores(List<Integer> weeklyScores) {
        this.weeklyScores = weeklyScores;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Integer getSeasonScore() {
        return seasonScore;
    }

    public void setSeasonScore(Integer seasonScore) {
        this.seasonScore = seasonScore;
    }

    public Integer getWeekScore() {
        return weekScore;
    }

    public void setWeekScore(Integer weekScore) {
        this.weekScore = weekScore;
    }

}
