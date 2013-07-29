package com.github.russ4stall.fourscorepicks.user.action;

import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Date: 7/18/13
 * Time: 2:47 PM
 *
 * @author Russ Forstall
 */
public class RegisterAction extends ActionSupport implements SessionAware {
    private User user;
    private String name;
    private String email;
    private String email2;
    private String password;
    private String password2;
    private Map<String, Object> session;

    public String input() {
        return INPUT;
    }

    @Override
    public void validate() {
        if (isEmpty(getName())) {
            addFieldError("name", "Name is a required field");
        }
        if (isEmpty(getEmail()) || isEmpty(getEmail2())) {
            addFieldError("email", "Email is a required field.");
        }
        if (isEmpty(getPassword()) || isEmpty(getPassword2())) {
            addFieldError("password", "Password is a required field.");
        }
        if (hasErrors()) {
            return;
        }
        UserDao userDao = new UserDaoImpl();
        if (userDao.emailExistsCheck(getEmail())) {
            addFieldError("email", "Email already exists");
        } else {
            if (!getPassword().equals(getPassword2())) {
                addFieldError("password", "Passwords don't match");
            }
            if (!getEmail().equals(getEmail2())) {
                addFieldError("email", "Emails don't match");
            }
        }

    }

    public String execute() {
        user = new User();
        user.setEmail(getEmail());
        user.setPassword(BCrypt.hashpw(getPassword(), BCrypt.gensalt()));

        user.setName(getName());


        UserDao userDao = new UserDaoImpl();
        userDao.addUser(getName(), getEmail(), user.getPassword());

        session.put("user", user);

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

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }


}
