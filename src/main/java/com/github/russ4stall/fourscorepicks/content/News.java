package com.github.russ4stall.fourscorepicks.content;

import java.sql.Timestamp;

/**
 * Date: 7/29/13
 * Time: 1:54 PM
 *
 * @author Russ Forstall
 */
public class News {
    private String newsText;
    private Timestamp datePosted;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public Timestamp getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Timestamp datePosted) {
        this.datePosted = datePosted;
    }
}
