package com.github.russ4stall.fourscorepicks.patch.dao;

import com.github.russ4stall.fourscorepicks.utility.SqlUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by russ on 8/14/14.
 */
public class PatchDaoImpl implements PatchDao {
    @Override
    public List<String> getCompletedPatches() {

        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<String> completedPatches = new ArrayList<String>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            preparedStatement = connection.prepareStatement("SELECT * from patches");


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                completedPatches.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeResultSet(resultSet);
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }

        return completedPatches;
    }

    @Override
    public void addCompletedPatch(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        SqlUtilities.jbdcUtil();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");
            String query = "INSERT INTO patches (name, completedOn) VALUES (?, now())";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }
}
