package com.github.russ4stall.fourscorepicks.user;

import com.github.russ4stall.fourscorepicks.game.WeekCalculator;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Date: 7/15/13
 * Time: 10:48 AM
 *
 * @author Russ Forstall
 */
@Entity
public class User {
    private String name;
    private String email;
    private String password;
    private boolean admin;
    private Integer weekScore;
    private Integer seasonScore;


    private int id;
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
        WeekCalculator weekCalculator = new WeekCalculator();
        UserScoreCalculator userScoreCalculator = new UserScoreCalculatorImpl();
        weekScore = userScoreCalculator.getWeekScore(id, weekCalculator.getPreviousWeekOfSeason());

        return weekScore;
    }

    public void setWeekScore(Integer weekScore) {
        this.weekScore = weekScore;
    }
}
