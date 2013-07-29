package com.github.russ4stall.fourscorepicks.pick;

/**
 * Date: 7/22/13
 * Time: 9:17 AM
 *
 * @author Russ Forstall
 */
public class Pick {
    private int gameId;
    private int userId;
    private int pickTeamId;
    private String pickTeamName;

    public String getPickTeamName() {
        return pickTeamName;
    }

    public void setPickTeamName(String pickTeamName) {
        this.pickTeamName = pickTeamName;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPickTeamId() {
        return pickTeamId;
    }

    public void setPickTeamId(int pickTeamId) {
        this.pickTeamId = pickTeamId;
    }
}
