package com.github.russ4stall.fourscorepicks.content;

import com.github.russ4stall.fourscorepicks.user.User;

import java.util.List;

/**
 * Date: 7/29/13
 * Time: 1:06 PM
 *
 * @author Russ Forstall
 */
public interface ContentDao {

    void addNews(String news, User user);

    void deleteNews(int id);

    List<News> getNewsList();

    void addComment(String comment, User user);

    void deleteComment(int id);

}
