package com.github.russ4stall.fourscorepicks.content.dao;

import com.github.russ4stall.fourscorepicks.content.Comment;
import com.github.russ4stall.fourscorepicks.content.News;
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
    public List<Comment> getCommentList() {
        List<Comment> commentList = new ArrayList<Comment>();
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            preparedStatement = connection.prepareStatement("SELECT * FROM comments c JOIN user u ON u.id = c.user_id ORDER BY date_posted DESC");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Comment comment = new Comment();
                comment.setDatePosted(resultSet.getTimestamp("date_posted"));
                comment.setCommentText(resultSet.getString("comment_text"));
                comment.setId(resultSet.getInt("id"));
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                comment.setUser(user);
                commentList.add(comment);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
            SqlUtilities.closeConnection(connection);
        }

        return commentList;
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
    public int addComment(String comment, int userId) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");


            String query = "INSERT INTO comments (comment_text, user_id, date_posted) " +
                    "VALUES (?, ?, now())";


            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, comment);
            preparedStatement.setInt(2, userId);

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
        return id;
    }

    @Override
    public void deleteComment(int id) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            String query = "DELETE FROM comments WHERE id=?";

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
    public Comment getComment(int id) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Comment comment = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");


            String query = "SELECT * FROM comments c JOIN user u on c.user_id = u.id WHERE c.id = ?";


            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            comment = new Comment();
            comment.setDatePosted(resultSet.getTimestamp("date_posted"));
            comment.setCommentText(resultSet.getString("comment_text"));
            comment.setId(id);
            User user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            comment.setUser(user);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }



        return comment;
    }
}
