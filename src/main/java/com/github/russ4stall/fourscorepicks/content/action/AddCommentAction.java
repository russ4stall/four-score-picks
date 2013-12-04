package com.github.russ4stall.fourscorepicks.content.action;

import com.github.russ4stall.fourscorepicks.content.Comment;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDao;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDaoImpl;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletResponse;

/**
 * Date: 9/17/13
 * Time: 1:00 PM
 *
 * @author Russ Forstall
 */
public class AddCommentAction implements ServletResponseAware {
    private String commentText;
    private int userId;
    private int testNum;
    private HttpServletResponse response;
    private Comment comment;

    public String execute(){
        ContentDao contentDao = new ContentDaoImpl();
        int newId = contentDao.addComment(commentText, userId);
        System.out.println(newId);
        comment = contentDao.getComment(newId);

        response.setHeader("Comment-Id", String.valueOf(comment.getId()));

        return "success";
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTestNum(int testNum) {
        this.testNum = testNum;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Comment getComment() {
        return comment;
    }
}
