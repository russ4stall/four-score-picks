package com.github.russ4stall.fourscorepicks.score;

/**
 * Created by russellf on 8/11/2014.
 */
public interface ScoreDao {
    void addUserWeeklyScore(int userId, int week, int score);
}
