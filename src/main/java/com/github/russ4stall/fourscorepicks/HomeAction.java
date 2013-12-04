package com.github.russ4stall.fourscorepicks;

import com.github.russ4stall.fourscorepicks.content.dao.ContentDao;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDaoImpl;
import com.github.russ4stall.fourscorepicks.content.News;
import com.github.russ4stall.fourscorepicks.user.RosterFactory;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;

import java.util.List;

/**
 * Date: 7/12/13
 * Time: 1:08 PM
 *
 * @author Russ Forstall
 */
public class HomeAction {
    List<User> seasonRoster;
    List<News> newsList;

    public String input(){

        ContentDao contentDao = new ContentDaoImpl();
        newsList = contentDao.getNewsList();
        RosterFactory rosterFactory = new RosterFactory();
        rosterFactory.calculateScores();
        seasonRoster = rosterFactory.getSeasonRoster();

        return "success";
    }

    public List<User> getSeasonRoster() {
        return seasonRoster;
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
