package com.github.russ4stall.fourscorepicks.content;

import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.game.Team;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.utility.SqlUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 7/29/13
 * Time: 1:39 PM
 *
 * @author Russ Forstall
 */
public class ContentDaoImpl implements ContentDao {

    @Override
    public void addNews(String news, User user) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");


            String query = "INSERT INTO news (news, user_id, date_posted) " +
                    "VALUES (?, ?, now())";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, news);
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }

    }


    @Override
    public void editNews(int id, String newsText) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");


            String query = "UPDATE news " +
                    "SET news=?" +
                    "WHERE id=?";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newsText);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }

    }

    @Override
    public List<News> getNewsList() {
        List<News> newsList = new ArrayList<News>();
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            preparedStatement = connection.prepareStatement("SELECT * FROM news ORDER BY date_posted DESC");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                News news = new News();
                news.setDatePosted(resultSet.getTimestamp("date_posted"));
                news.setNewsText(resultSet.getString("news"));
                news.setId(resultSet.getInt("id"));
                newsList.add(news);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
            SqlUtilities.closeConnection(connection);
        }




        return newsList;
    }

    @Override
    public News getNewsById(int id) {
        News news = new News();
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            preparedStatement = connection.prepareStatement("SELECT * FROM news WHERE id=?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            news.setDatePosted(resultSet.getTimestamp("date_posted"));
            news.setNewsText(resultSet.getString("news"));
            news.setId(resultSet.getInt("id"));



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
            SqlUtilities.closeConnection(connection);
        }



        return news;
    }

    @Override
    public void deleteNews(int id) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            String query = "DELETE FROM news WHERE id=?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }

    @Override
    public void addComment(String comment, User user) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteComment(int id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
