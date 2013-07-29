package com.github.russ4stall.fourscorepicks.user.action;

import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Date: 7/18/13
 * Time: 4:22 PM
 *
 * @author Russ Forstall
 */
public class LogoutAction implements SessionAware {

    private Map<String, Object> session;
    public String execute(){

        session.remove("user");
        return "success";
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
