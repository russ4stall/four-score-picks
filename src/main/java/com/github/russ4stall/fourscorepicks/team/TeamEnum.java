package com.github.russ4stall.fourscorepicks.team;

/**
 * Created by russ on 8/14/14.
 */
public enum TeamEnum {
    RAVENS(1, "Ravens", "Baltimore"),
    BENGALS(2, "Bengals", "Cincinnati"),
    BROWNS(3, "Browns", "Cleveland"),
    STEELERS(4, "Steelers", "Pittsburgh"),
    TEXANS(5, "Texans", "Houston"),
    COLTS(6, "Colts", "Indianapolis"),
    JAGUARS(7, "Jaguars", "Jacksonville"),
    TITANS(8, "Titans", "Tennessee"),
    BILLS(9, "Bills", "Buffalo"),
    DOLPHINS(10, "Dolphins", "Miami"),
    PATRIOTS(11, "Patriots", "New England"),
    JETS(12, "Jets", "New York"),
    BRONCOS(13, "Broncos", "Denver"),
    CHIEFS(14, "Chiefs", "Kansas City"),
    RAIDERS(15, "Raiders", "Oakland"),
    CHARGERS(16, "Chargers", "San Diego"),
    BEARS(17, "Bears", "Chicago"),
    LIONS(18, "Lions", "Detroit"),
    PACKERS(19, "Packers", "Green Bay"),
    VIKINGS(20, "Vikings", "Minnesota"),
    FALCONS(21, "Falcons", "Atlanta"),
    PANTHERS(22, "Panthers", "Carolina"),
    SAINTS(23, "Saints", "New Orleans"),
    BUCCANEERS(24, "Buccaneers", "Tampa Bay"),
    COWBOYS(25, "Cowboys", "Dallas"),
    GIANTS(26, "Giants", "New York"),
    EAGLES(27, "Eagles", "Philadelphia"),
    REDSKINS(28, "Redskins", "Washington"),
    CARDINALS(29, "Cardinals", "Arizona"),
    FORTYNINERS(30, "49ers", "San Francisco"),
    SEAHAWKS(31, "Seahawks", "Seattle"),
    RAMS(32, "Rams", "St. Louis");

    private final int id;
    private final String name;
    private final String location;

    TeamEnum(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Team toTeam() {
        return new Team(this.id, this.location, this.name);
    }

    public static TeamEnum getEnum(String s) {
        for (TeamEnum teamEnum : TeamEnum.values()) {
            if(teamEnum.getName().equalsIgnoreCase(s)) return teamEnum;
        }
        if (s.equalsIgnoreCase("49ers")) return TeamEnum.FORTYNINERS;

        throw new IllegalArgumentException("No match for TeamEnum");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
