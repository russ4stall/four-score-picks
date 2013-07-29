package com.github.russ4stall.fourscorepicks.pick;

import com.github.russ4stall.fourscorepicks.game.Game;

/**
 * Date: 7/22/13
 * Time: 1:35 PM
 *
 * @author Russ Forstall
 */
public class GameAndPick {
    Game game;
    Pick pick;

    public GameAndPick(Game game, Pick pick) {
        this.pick = pick;
        this.game = game;
    }

    public Pick getPick() {
        return pick;
    }

    public void setPick(Pick pick) {
        this.pick = pick;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
