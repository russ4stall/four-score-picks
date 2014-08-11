package com.github.russ4stall.fourscorepicks.score;

import com.github.russ4stall.fourscorepicks.utility.SqlUtilities;

import java.sql.*;

/**
 * Created by russellf on 8/11/2014.
 */
public class ScoreDaoImpl implements ScoreDao {
    @Override
    public void addUserWeeklyScore(int userId, int week, int score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        SqlUtilities.jbdcUtil();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");
            String query = "INSERT INTO weekly_scores (user_id, week, score) " +
                    "VALUES (?, ?, ?)";

            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, week);
            preparedStatement.setInt(3, score);

            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }
}
