package com.github.russ4stall.fourscorepicks.game.dao;

import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.game.Team;
import com.github.russ4stall.fourscorepicks.utility.SqlUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Date: 7/19/13
 * Time: 9:55 AM
 *
 * @author Russ Forstall
 */
public class GameDaoImpl implements GameDao {

    @Override
    public void addGame(int awayTeamId, int homeTeamId, int weekNum, String gameTime) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");


            String query = "INSERT INTO game (away_team, home_team, week, date_time ) " +
                    "VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, awayTeamId);
            preparedStatement.setInt(2, homeTeamId);
            preparedStatement.setInt(3, weekNum);
            preparedStatement.setString(4, gameTime);

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }

    @Override
    public List<Game> getGamesByWeek(int weekNum) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Game> gameList = new ArrayList<Game>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            preparedStatement = connection.prepareStatement("SELECT g.*, hg.game_id AS hot_game, " +
                    "t1.name AS `away_team`, t2.name AS `home_team`, r.*, t3.name AS `winner_name` " +
                    "FROM game g " +
                    "LEFT OUTER JOIN result r ON r.game_id=g.id " +
                    "JOIN team t1 ON t1.id=g.away_team " +
                    "JOIN team t2 ON t2.id=g.home_team " +
                    "LEFT OUTER JOIN team t3 ON t3.id=r.winner_id " +
                    "LEFT OUTER JOIN hot_game hg ON hg.game_id=g.id " +
                    "WHERE g.week=? " +
                    "ORDER BY g.date_time, g.id ASC");

            preparedStatement.setInt(1, weekNum);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
            Game game = new Game();
            Team awayTeam = new Team();
            Team homeTeam = new Team();
            Team winningTeam = new Team();

            awayTeam.setName(resultSet.getString("t1.away_team"));
            awayTeam.setId(resultSet.getInt("g.away_team"));
            homeTeam.setName(resultSet.getString("t2.home_team"));
            homeTeam.setId(resultSet.getInt("g.home_team"));
            winningTeam.setId(resultSet.getInt("winner_id"));
            winningTeam.setName(resultSet.getString("winner_name"));
            game.setAwayTeam(awayTeam);
            game.setHomeTeam(homeTeam);
            game.setWinningTeam(winningTeam);
            game.setId(resultSet.getInt("g.id"));
            game.setWeek(resultSet.getInt("week"));
            game.setGameTime(resultSet.getTimestamp("g.date_time"));
            resultSet.getInt("hot_game");
            if(!resultSet.wasNull()){
                game.setHotGame(true);
            }

            gameList.add(game);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
            SqlUtilities.closeConnection(connection);
        }


        return gameList;
    }

    @Override
    public void addHotGame(int weekNum, int gameId) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            String query = "INSERT INTO hot_game (week, game_id) " +
                    "VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, weekNum);
            preparedStatement.setInt(2, gameId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }

    @Override
    public void removeHotGame(int weekNum, int gameId) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            String query = "DELETE FROM hot_game WHERE week=? AND game_id=?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, weekNum);
            preparedStatement.setInt(2, gameId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }
    }

    @Override
    public void setResult(Game game) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
