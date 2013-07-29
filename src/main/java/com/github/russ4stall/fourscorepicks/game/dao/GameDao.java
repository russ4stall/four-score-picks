package com.github.russ4stall.fourscorepicks.game.dao;

import com.github.russ4stall.fourscorepicks.game.Game;

import java.sql.Date;
import java.util.List;

/**
 * Date: 7/19/13
 * Time: 9:35 AM
 *
 * @author Russ Forstall
 */
public interface GameDao {

    /**
     *
     * @param weekNum determines which week schedule to return
     * @return a list of games
     */
    List<Game> getGamesByWeek(int weekNum);

    /**
     * adds to hotgame table
     * @param gameId
     */
    void addHotGame(int weekNum, int gameId);

    void removeHotGame(int weekNum, int gameId);

    /**
     * records the results of the game to results table
     * @param game
     */
    void setResult(Game game);

    void addGame(int awayTeamId, int homeTeamId, int weekNum, String gameTime);



}
