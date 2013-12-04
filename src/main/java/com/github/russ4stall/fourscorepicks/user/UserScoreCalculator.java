package com.github.russ4stall.fourscorepicks.user;

/**
 * Date: 7/25/13
 * Time: 9:12 AM
 *
 * @author Russ Forstall
 */
public interface UserScoreCalculator {
    /**
     *
     * @param userId
     * @param weekNum
     * @return an integer that is the users score for the given week
     */
    int getWeekScore(int userId, int weekNum);

    /**
     *
     * @param userId
     *
     * @return an integer that is the users score for the whole season
     */
    int getSeasonScore(int userId);

    int getSeasonScore(int userId, int weekOfSeason);

}
