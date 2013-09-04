package com.github.russ4stall.fourscorepicks.user.dao;

import com.github.russ4stall.fourscorepicks.user.User;

import java.util.List;

/**
 * Date: 7/16/13
 * Time: 9:41 AM
 *
 * @author Russ Forstall
 */
public interface UserDao {
    /**
     *
     * adds user to user table in database upon registration
     *
     * @param name users name obtained from registration page
     * @param email users email obtained from registration page
     * @param password users password obtained from registration page
     */
    int addUser(String name, String email, String password);

    /**
     *
     * checks if user is in database and authenticates password
     * returns a the user object
     *
     * @param email users email obtained from login page
     * @param password users password obtained from login page
     * @return User
     */
    User loginUser(String email, String password);

    /**
     *
     * checks to see if email already exists in database
     *
     * @param email users email obtained from registration page
     * @return boolean emailExists
     */
    boolean emailExistsCheck(String email);

    List<User> getUserList();
}
