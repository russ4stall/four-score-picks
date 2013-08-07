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

        PickDao pickDao = new PickDaoImpl();
        List<GameAndPick> gameAndPickList = pickDao.getGameAndPickByWeek(userId, weekNum);
        List<Pick> pickList = pickDao.getPicksByWeek(userId, weekNum);

        if (!pickList.isEmpty()){

            //System.out.println("size" + pickList.size());

            for (GameAndPick gameAndPick : gameAndPickList) {

            if(gameAndPick.getPick() != null){
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
            }else if(gameAndPick.getGame().isHotGame()){
                weekScore++;
            }
            }
        }
        return weekScore;
    }

    @Override
    public int getSeasonScore(int userId) {

        WeekCalculator weekCalculator = new WeekCalculator();
        int weekOfSeason = weekCalculator.getWeekOfSeason();
        PickDao pickDao = new PickDaoImpl();
        List<GameAndPick> gameAndPickList = pickDao.getGameAndPickBySeason(userId);

        int seasonScore = 0;

        for (int i=1;i<weekOfSeason ;i++){

            int weekScore = getWeekScore(userId, i);
            seasonScore=seasonScore + weekScore;
            System.out.println(userId);
            System.out.println(weekScore);
            System.out.println(seasonScore);
            System.out.println("---------------------");
        }



        /*
        for (GameAndPick gameAndPick : gameAndPickList) {
            if(gameAndPick.getPick() != null){
                if (!gameAndPick.getGame().isHotGame()) {
                    if (gameAndPick.getGame().getWinningTeam().getId() == gameAndPick.getPick().getPickTeamId()) {
                        seasonScore++;
                    }
                } else {
                    if (gameAndPick.getPick() != null) {
                        if (gameAndPick.getGame().getWinningTeam().getId() == gameAndPick.getPick().getPickTeamId()) {
                            seasonScore = seasonScore + 2;
                        } else if (gameAndPick.getPick() == null) {
                            seasonScore++;
                        }
                    }
                }
            }else if(gameAndPick.getGame().isHotGame()){
                seasonScore++;
            }
        }
        }
*/


        return seasonScore;
    }
}
