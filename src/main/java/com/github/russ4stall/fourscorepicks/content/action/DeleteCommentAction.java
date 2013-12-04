package com.github.russ4stall.fourscorepicks.content.action;

import com.github.russ4stall.fourscorepicks.content.dao.ContentDao;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDaoImpl;

/**
 * Date: 9/17/13
 * Time: 9:14 AM
 *
 * @author Russ Forstall
 */
public class DeleteCommentAction {
    private int commentId;


    public String execute(){
        ContentDao contentDao = new ContentDaoImpl();
        contentDao.deleteComment(commentId);


        return "success";
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
}
