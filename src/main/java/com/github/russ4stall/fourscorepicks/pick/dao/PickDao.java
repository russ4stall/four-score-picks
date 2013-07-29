package com.github.russ4stall.fourscorepicks.pick.dao;

import com.github.russ4stall.fourscorepicks.pick.GameAndPick;
import com.github.russ4stall.fourscorepicks.pick.Pick;

import java.util.List;

/**
 * Date: 7/22/13
 * Time: 9:58 AM
 *
 * @author Russ Forstall
 */
public interface PickDao {

    void deletePick(int userId, int gameId);

    void setPicks(List<Pick> pickList);

    List<Pick> getPicksByWeek(int userId, int weekNum);

    List<GameAndPick> getGameAndPickByWeek(int userId, int weekNum);

    List<GameAndPick> getGameAndPickBySeason(int userId);

}
