package com.github.russ4stall.fourscorepicks.interceptor;

import com.github.russ4stall.fourscorepicks.user.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;


/**
 * Date: 7/26/13
 * Time: 1:30 PM
 *
 * @author Russ Forstall
 */
public class LoginCheckInterceptor extends AbstractInterceptor  {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        User user = (User) session.get("user");


        if (user != null){
            return invocation.invoke();
        }else {
            return "login";
        }
    }

}
