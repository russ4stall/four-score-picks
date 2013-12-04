package com.github.russ4stall.fourscorepicks.content.action;

import com.github.russ4stall.fourscorepicks.content.Comment;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDao;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDaoImpl;
import com.github.russ4stall.fourscorepicks.user.RosterFactory;
import com.github.russ4stall.fourscorepicks.user.User;
import com.opensymphony.xwork2.Preparable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Date: 9/13/13
 * Time: 4:20 PM
 *
 * @author Russ Forstall
 */
public class TrashTalkAction implements Preparable {
    List<User> seasonRoster;
    List<Comment> comments;
    private String commentText;
    private int userId;



    public void prepare(){
        ContentDao contentDao = new ContentDaoImpl();
        comments = contentDao.getCommentList();
        RosterFactory rosterFactory = new RosterFactory();
        rosterFactory.calculateScores();
        seasonRoster = rosterFactory.getSeasonRoster();

    }

    public String input(){

        return "success";
    }

    public String execute() {
        ContentDao contentDao = new ContentDaoImpl();
        int newId = contentDao.addComment(commentText, userId);

        return "success";

    }


    public List<User> getSeasonRoster() {
        return seasonRoster;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
