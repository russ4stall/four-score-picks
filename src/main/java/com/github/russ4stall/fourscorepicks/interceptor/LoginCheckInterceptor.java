package com.github.russ4stall.fourscorepicks.interceptor;

import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.*;


/**
 * Date: 7/26/13
 * Time: 1:30 PM
 *
 * @author Russ Forstall
 */
public class LoginCheckInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        User user = (User) session.get("user");


        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);

        if (user != null){
            return invocation.invoke();
        }else {
            Cookie[] cookies = request.getCookies();
            if (cookies!=null){
                for (Cookie cookie : cookies){
                    if (cookie.getName().equals("remember-me")){
                        Map<String, String> loginParameters = Splitter.on('&').withKeyValueSeparator('=').split(cookie.getValue());
                        UserDao userDao = new UserDaoImpl();
                        if (!userDao.emailExistsCheck(loginParameters.get("email"))){
                            return "login";
                        }
                        user = userDao.loginUser(loginParameters.get("email"), loginParameters.get("password"));
                        if(!loginParameters.get("password").equals(user.getPassword())){
                            return "login";
                        } else {
                            session.put("user", user);
                            request.setAttribute("rememberMe", "true");
                            return invocation.invoke();
                        }
                    }
                }
            }
            return "login";
        }
    }
}
