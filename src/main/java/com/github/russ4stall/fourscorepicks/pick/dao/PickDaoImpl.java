package com.github.russ4stall.fourscorepicks.pick.dao;

import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.game.Team;
import com.github.russ4stall.fourscorepicks.pick.GameAndPick;
import com.github.russ4stall.fourscorepicks.pick.Pick;
import com.github.russ4stall.fourscorepicks.utility.SqlUtilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Date: 7/22/13
 * Time: 10:02 AM
 *
 * @author Russ Forstall
 */
public class PickDaoImpl implements PickDao {

    @Override
    public void deletePick(int userId, int gameId) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

                String query = "DELETE FROM pick WHERE user_id=? AND game_id=?";

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, userId);
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
    public void setPicks(List<Pick> pickList) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            for (Pick pick : pickList){

            String query = "INSERT INTO pick (user_id, game_id, pick) " +
                    "VALUES (?, ?, ?)" +
                    "ON DUPLICATE KEY UPDATE user_id=?, game_id=?, pick=?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pick.getUserId());
            preparedStatement.setInt(2, pick.getGameId());
            preparedStatement.setInt(3, pick.getPickTeamId());
            preparedStatement.setInt(4, pick.getUserId());
            preparedStatement.setInt(5, pick.getGameId());
            preparedStatement.setInt(6, pick.getPickTeamId());

            preparedStatement.executeUpdate();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeConnection(connection);
        }

    }

    @Override
    public List<Pick> getPicksByWeek(int userId, int weekNum) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Pick> pickList = new ArrayList<Pick>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");

            preparedStatement = connection.prepareStatement("SELECT p.*, g.game_time, t.name " +
                    "FROM pick p " +
                    "JOIN game g ON g.id=p.game_id " +
                    "JOIN team t ON t.id=p.pick " +
                    "WHERE week=? AND p.user_id=? " +
                    "ORDER BY g.game_time, p.game_id ASC");

            preparedStatement.setInt(1, weekNum);
            preparedStatement.setInt(2, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Pick pick = new Pick();
                pick.setGameId(resultSet.getInt("p.game_id"));
                pick.setUserId(resultSet.getInt("p.user_id"));
                pick.setPickTeamId(resultSet.getInt("p.pick"));
                pick.setPickTeamName(resultSet.getString("t.name"));
                pickList.add(pick);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closeResultSet(resultSet);
            SqlUtilities.closePreparedStatement(preparedStatement);

            SqlUtilities.closeConnection(connection);
        }



        return pickList;
    }

    @Override
    public List<GameAndPick> getGameAndPickByWeek(int userId, int weekNum) {
        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        List<GameAndPick> gameAndPickList = new ArrayList<GameAndPick>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");


            preparedStatement = connection.prepareStatement("SELECT g.*, p.*, hg.game_id AS hot_game, t1.name AS `away_team`, t2.name AS `home_team`, " +
                    "t3.name AS `pick_name`, r.*, t4.name AS `winner_name` " +
                    "FROM game g " +
                    "LEFT OUTER JOIN result r ON r.game_id=g.id " +
                    "LEFT OUTER JOIN team t4 ON t4.id=r.winner_id " +
                    "JOIN team t1 ON t1.id=g.away_team " +
                    "JOIN team t2 ON t2.id=g.home_team " +
                    "LEFT OUTER JOIN pick p ON p.game_id=g.id AND p.user_id=? " +
                    "LEFT OUTER JOIN team t3 ON t3.id=p.pick " +
                    "LEFT OUTER JOIN hot_game hg ON hg.game_id=g.id " +
                    "WHERE g.week=? ORDER BY g.game_time, g.id ASC");
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, weekNum);
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
                game.setGameTime(resultSet.getTimestamp("g.game_time"));
                game.setWeek(resultSet.getInt("week"));
                resultSet.getInt("hot_game");
                if(!resultSet.wasNull()){
                    game.setHotGame(true);
                }
                String pickTeamName = resultSet.getString("pick_name");
                Pick pick = null;
                if(!isEmpty(pickTeamName)){
                    pick = new Pick();
                    pick.setGameId(resultSet.getInt("p.user_id"));
                    pick.setUserId(userId);
                    pick.setPickTeamId(resultSet.getInt("p.pick"));
                    pick.setPickTeamName(pickTeamName);
                }
                gameAndPickList.add(new GameAndPick(game, pick));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
            SqlUtilities.closeConnection(connection);
        }



        return gameAndPickList;
    }


    @Override
    public List<GameAndPick> getGameAndPickBySeason(int userId) {

        SqlUtilities.jbdcUtil();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        List<GameAndPick> gameAndPickList = new ArrayList<GameAndPick>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fourscorepicks", "fourscorepicks", "fourscorepicks");


            preparedStatement = connection.prepareStatement("SELECT g.*, p.*, hg.game_id AS hot_game, t1.name AS `away_team`, t2.name AS `home_team`, " +
                    "t3.name AS `pick_name`, r.*, t4.name AS `winner_name` " +
                    "FROM game g " +
                    "LEFT OUTER JOIN result r ON r.game_id=g.id " +
                    "LEFT OUTER JOIN team t4 ON t4.id=r.winner_id " +
                    "JOIN team t1 ON t1.id=g.away_team " +
                    "JOIN team t2 ON t2.id=g.home_team " +
                    "LEFT OUTER JOIN pick p ON p.game_id=g.id AND p.user_id=? " +
                    "LEFT OUTER JOIN team t3 ON t3.id=p.pick " +
                    "LEFT OUTER JOIN hot_game hg ON hg.game_id=g.id " +
                    "ORDER BY g.game_time, g.id ASC");
            preparedStatement.setInt(1, userId);
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
                game.setGameTime(resultSet.getTimestamp("g.game_time"));
                game.setWeek(resultSet.getInt("week"));
                resultSet.getInt("hot_game");
                if(!resultSet.wasNull()){
                    game.setHotGame(true);
                }
                String pickTeamName = resultSet.getString("pick_name");
                Pick pick = null;
                if(!isEmpty(pickTeamName)){
                    pick = new Pick();
                    pick.setGameId(resultSet.getInt("p.user_id"));
                    pick.setUserId(userId);
                    pick.setPickTeamId(resultSet.getInt("p.pick"));
                    pick.setPickTeamName(pickTeamName);
                }
                gameAndPickList.add(new GameAndPick(game, pick));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            SqlUtilities.closePreparedStatement(preparedStatement);
            SqlUtilities.closeResultSet(resultSet);
            SqlUtilities.closeConnection(connection);
        }



        return gameAndPickList;



    }
}
