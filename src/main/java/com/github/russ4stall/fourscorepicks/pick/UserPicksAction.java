package com.github.russ4stall.fourscorepicks.pick;

import com.github.russ4stall.fourscorepicks.game.WeekCalculator;
import com.github.russ4stall.fourscorepicks.pick.dao.PickDao;
import com.github.russ4stall.fourscorepicks.pick.dao.PickDaoImpl;
import com.github.russ4stall.fourscorepicks.user.RosterFactory;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 9/19/13
 * Time: 4:51 PM
 *
 * @author Russ Forstall
 */
public class UserPicksAction {
    private int userId;

    private List<List> weekUserResultList;
    private User user;
    private List<User> seasonRoster;

    public String execute(){
        WeekCalculator weekCalculator = new WeekCalculator();
        PickDao pickDao = new PickDaoImpl();
        UserDao userDao = new UserDaoImpl();
        user = userDao.getUserById(userId);

        RosterFactory rosterFactory = new RosterFactory();
        rosterFactory.calculateScores();
        seasonRoster = rosterFactory.getSeasonRoster();



        weekUserResultList = new ArrayList<List>();
        for (int i=weekCalculator.getWeekOfSeason(); i >= 1; i--){
            List<GameAndPick> userResultList = pickDao.getGameAndPickByWeek(userId, i);
            weekUserResultList.add(userResultList);
        }


        return "success";
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<List> getWeekUserResultList() {
        return weekUserResultList;
    }

    public User getUser() {
        return user;
    }

    public List<User> getSeasonRoster() {
        return seasonRoster;
    }
}
