package com.github.russ4stall.fourscorepicks.content.action;

import com.github.russ4stall.fourscorepicks.content.dao.ContentDao;
import com.github.russ4stall.fourscorepicks.content.dao.ContentDaoImpl;
import com.github.russ4stall.fourscorepicks.content.News;
import com.github.russ4stall.fourscorepicks.user.RosterFactory;
import com.github.russ4stall.fourscorepicks.user.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

/**
 * Date: 8/7/13
 * Time: 10:42 AM
 *
 * @author Russ Forstall
 */
public class EditNewsAction extends ActionSupport implements Preparable, SessionAware {
    private int newsId;
    private News news;
    private Map<String, Object> session;
    private User user = null;
    private List<User> seasonRoster;
    private String newsText;


    @Override
    public void prepare() throws Exception {
        user = (User)session.get("user");
        RosterFactory rosterFactory = new RosterFactory();
        rosterFactory.calculateScores();
        seasonRoster = rosterFactory.getSeasonRoster();


    }

    public String input(){
        if (!user.isAdmin()){
            return "redirectHome";
        }
        System.out.println(newsId);
        ContentDao contentDao = new ContentDaoImpl();
        news = contentDao.getNewsById(newsId);
        return INPUT;
    }

    public String deleteNews(){
        if (!user.isAdmin()){
            return "redirectHome";
        }
        System.out.println(newsId);
        ContentDao contentDao = new ContentDaoImpl();
        contentDao.deleteNews(newsId);
        return "redirectHome";
    }

    public String editNews(){
        System.out.println(newsId);
        if (!user.isAdmin()){
            return "redirectHome";
        }

        ContentDao contentDao = new ContentDaoImpl();
        contentDao.editNews(newsId, newsText);
        return "redirectHome";
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public List<User> getSeasonRoster() {
        return seasonRoster;
    }

    public News getNews() {
        return news;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }
}
