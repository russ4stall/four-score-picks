package com.github.russ4stall.fourscorepicks.utility;

import com.github.russ4stall.fourscorepicks.game.Game;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Date: 7/29/13
 * Time: 9:48 AM
 *
 * @author Russ Forstall
 */
public class GameUtilities {

    private boolean gameHasStarted(Game game){
        boolean gameHasStarted = false;
        Date date = new Date();
        if(game.getGameTime().before(new Timestamp(date.getTime()))){

            gameHasStarted = true;
        }

        return gameHasStarted;
    }


}
