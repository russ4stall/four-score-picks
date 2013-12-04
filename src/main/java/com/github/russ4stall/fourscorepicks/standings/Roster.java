package com.github.russ4stall.fourscorepicks.standings;

import com.github.russ4stall.fourscorepicks.user.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Date: 10/29/13
 * Time: 11:07 AM
 *
 * @author Russ Forstall
 */
public class Roster {
    int week;
    List<User> users;

    public Roster(int week, List<User> users) {
        this.week = week;
        this.users = users;

    }

    public void sortByWeekScore(){
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return (user1.getScoreOfWeek(week).compareTo(user2.getScoreOfWeek(week))) * -1;
            }
        });
    }

    public void sortBySeasonScore(){
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return (user1.getSeasonScoreOfWeek(week).compareTo(user2.getSeasonScoreOfWeek(week))) * -1;
            }
        });
    }

    public int getWeek() {
        return week;
    }

    public List<User> getUsers() {
        return users;
    }
}
