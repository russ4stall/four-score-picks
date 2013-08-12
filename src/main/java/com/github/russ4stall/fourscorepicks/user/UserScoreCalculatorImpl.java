package com.github.russ4stall.fourscorepicks.user;

import com.github.russ4stall.fourscorepicks.game.WeekCalculator;
import com.github.russ4stall.fourscorepicks.pick.GameAndPick;
import com.github.russ4stall.fourscorepicks.pick.Pick;
import com.github.russ4stall.fourscorepicks.pick.dao.PickDao;
import com.github.russ4stall.fourscorepicks.pick.dao.PickDaoImpl;

import java.util.List;

/**
 * Date: 7/25/13
 * Time: 9:17 AM
 *
 * @author Russ Forstall
 */
public class UserScoreCalculatorImpl implements UserScoreCalculator {

    @Override
    public int getWeekScore(int userId, int weekNum) {
        int weekScore = 0;
        int pointsPossible = 0;

        PickDao pickDao = new PickDaoImpl();
        List<GameAndPick> gameAndPickList = pickDao.getGameAndPickByWeek(userId, weekNum);
        List<Pick> pickList = pickDao.getPicksByWeek(userId, weekNum);


        for (GameAndPick gameAndPick : gameAndPickList) {
            if (gameAndPick.getGame().isHotGame()){
                pointsPossible = pointsPossible + 2;
            } else {
                pointsPossible++;
            }


            if (!pickList.isEmpty()) {

                if (gameAndPick.getPick() != null) {
                    if (!gameAndPick.getGame().isHotGame()) {
                        if (gameAndPick.getGame().getWinningTeam().getId() == gameAndPick.getPick().getPickTeamId()) {
                            weekScore++;
                        }
                    } else {
                        if (gameAndPick.getPick() != null) {
                            if (gameAndPick.getGame().getWinningTeam().getId() == gameAndPick.getPick().getPickTeamId()) {
                                weekScore = weekScore + 2;
                            } else if (gameAndPick.getPick() == null) {
                                weekScore++;
                            }
                        }
                    }
                } else if (gameAndPick.getGame().isHotGame()) {
                    weekScore++;
                }
            }
        }

        //if user picks all correct, add 5
        if (weekScore == pointsPossible){
        weekScore = weekScore + 5;
        }

        return weekScore;
    }

    @Override
    public int getSeasonScore(int userId) {

        WeekCalculator weekCalculator = new WeekCalculator();
        int weekOfSeason = weekCalculator.getWeekOfSeason();
        int seasonScore = 0;

        for (int i = 1; i < weekOfSeason; i++) {

            int weekScore = getWeekScore(userId, i);
            seasonScore = seasonScore + weekScore;
        }

        return seasonScore;
    }
}
