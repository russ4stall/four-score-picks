package com.github.russ4stall.fourscorepicks.user.dao;

import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.utility.SqlUtilities;
import com.google.common.base.Objects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 7/16/13
 * Time: 9:53 AM
 *
 * @author Russ Forstall
 */
public class UserDaoImpl implements UserDao{

    @Override
    public boolean emailExistsCheck(String email) {
        boolean emailExists = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SqlUtilities.jbdcUtil();

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            preparedStatement = connection.prepareStatement("SELECT count(1) FROM user WHERE email=?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            emailExists = resultSet.getInt("count(1)") == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
            SqlUtilities.closeConnection(connection);
        }
        return emailExists;
    }

    @Override
    public void addUser(String name, String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SqlUtilities.jbdcUtil();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");
            String query = "INSERT INTO user (name, email, password, registered_on, last_login_on) " +
                    "VALUES (?, ?, ?, now(), now())";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }

    }

    @Override
    public User loginUser(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = new User();
        SqlUtilities.jbdcUtil();
        String query = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            query = "SELECT password, id, name, email, is_admin FROM user WHERE email=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setAdmin(resultSet.getBoolean("is_admin"));


            if (Objects.equal(password, user.getPassword())){
                query = "UPDATE user SET last_login_on = now() WHERE email=?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, email);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closeResultSet(resultSet);
        }

        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SqlUtilities.jbdcUtil();
        String query = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            query = "SELECT * FROM user";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setAdmin(resultSet.getBoolean("is_admin"));
                userList.add(user);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closeResultSet(resultSet);
        }

        return userList;
    }
}



