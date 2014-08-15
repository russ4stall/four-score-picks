package com.github.russ4stall.fourscorepicks.user.action;

import com.github.russ4stall.fourscorepicks.utility.WeekCalculator;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Date: 7/12/13
 * Time: 3:24 PM
 *
 * @author Russ Forstall
 */
public class LoginAction extends ActionSupport implements SessionAware, ServletResponseAware {
    private User user;
    private String email;
    private String password;
    private Map<String, Object> session;
    private HttpServletResponse response;
    private boolean rememberMe;

    public String input() {
        WeekCalculator week = new WeekCalculator();

        return INPUT;
    }

    @Override
    public void validate() {
        if (isEmpty(getEmail())) {
            addFieldError("email", "Email is a required field.");
        }

        if (isEmpty(getPassword())) {
            addFieldError("password", "Password is a required field.");
        }
        if (hasErrors()){
            return;
        }
        UserDao userDao = new UserDaoImpl();
        if (!userDao.emailExistsCheck(getEmail())) {
            addFieldError("email", "Email does not exist");
        } else {
            user = userDao.loginUser(getEmail(), getPassword());
            if (!BCrypt.checkpw(getPassword(), user.getPassword())) {
                addFieldError("password", "Password doesn't match");
            }
        }
    }

    public String execute() {
        session.put("user", user);

        if (rememberMe){
            Cookie cookie = new Cookie("remember-me", "email=" + email + "&password=" + user.getPassword());
            cookie.setMaxAge(604800);
            response.addCookie(cookie);
        }

        return SUCCESS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
