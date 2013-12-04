package com.github.russ4stall.fourscorepicks.user.action;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Date: 7/18/13
 * Time: 4:22 PM
 *
 * @author Russ Forstall
 */
public class LogoutAction implements SessionAware, ServletResponseAware {
    private Map<String, Object> session;
    private HttpServletResponse response;

    public String execute(){
        session.remove("user");
        Cookie cookie = new Cookie("remember-me", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "success";
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }
}


