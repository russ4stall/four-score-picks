package com.github.russ4stall.fourscorepicks.game;

/**
 * Date: 7/19/13
 * Time: 9:45 AM
 *
 * @author Russ Forstall
 */
public class Team {
    String name;
    String location;
    int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
