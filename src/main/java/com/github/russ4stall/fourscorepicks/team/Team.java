package com.github.russ4stall.fourscorepicks.team;

/**
 * Date: 7/19/13
 * Time: 9:45 AM
 *
 * @author Russ Forstall
 */
public class Team {
    int id;
    String location;
    String name;

    public Team() {
    }

    public Team(int id, String location, String name) {
        this.id = id;
        this.location = location;
        this.name = name;
    }

    public static Team valueOf(String s) {
        return TeamEnum.getEnum(s).toTeam();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
