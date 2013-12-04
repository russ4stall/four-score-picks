package com.github.russ4stall.fourscorepicks.content.dao;

import com.github.russ4stall.fourscorepicks.content.Comment;
import com.github.russ4stall.fourscorepicks.content.News;
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

    void editNews(int id, String newsText);

    News getNewsById(int id);

    List<News> getNewsList();

    List<Comment> getCommentList();

    int addComment(String comment, int userId);

    void deleteComment(int id);

    Comment getComment(int id);

}
