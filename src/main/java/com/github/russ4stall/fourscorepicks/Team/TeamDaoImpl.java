package com.github.russ4stall.fourscorepicks.Team;

import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.utility.SqlUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by russ on 8/11/14.
 */
public class TeamDaoImpl implements TeamDao {
    @Override
    public List<Team> getAllTeams() {
        List<Team> teamList = new ArrayList<Team>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        SqlUtilities.jbdcUtil();
        String query = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            query = "SELECT * FROM team";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Team team = new Team();
                team.setId(resultSet.getInt("id"));
                team.setName(resultSet.getString("name"));
                team.setLocation(resultSet.getString("location"));

                teamList.add(team);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
            SqlUtilities.closeResultSet(resultSet);
        }

        return teamList;
    }
}
