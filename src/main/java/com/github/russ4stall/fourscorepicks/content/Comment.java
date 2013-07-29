package com.github.russ4stall.fourscorepicks.content;

import com.github.russ4stall.fourscorepicks.user.User;

import java.sql.Timestamp;

/**
 * Date: 7/29/13
 * Time: 1:56 PM
 *
 * @author Russ Forstall
 */
public class Comment {
    private User user;
    private String commentText;
    private Timestamp datePosted;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Timestamp datePosted) {
        this.datePosted = datePosted;
    }
}
