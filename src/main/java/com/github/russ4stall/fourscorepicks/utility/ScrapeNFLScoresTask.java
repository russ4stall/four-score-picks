package com.github.russ4stall.fourscorepicks.utility;

import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.game.WeekCalculator;
import com.github.russ4stall.fourscorepicks.game.dao.GameDao;
import com.github.russ4stall.fourscorepicks.game.dao.GameDaoImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Scrapes scores from nfl.com and updates the database
 *
 * Created by russ on 8/14/14.
 */
public class ScrapeNFLScoresTask implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        WeekCalculator weekCalculator = new WeekCalculator();
        GameDao gameDao = new GameDaoImpl();

        int weekNumber = weekCalculator.getWeekOfSeason();
        List<Game> games = gameDao.getGamesByWeek(weekNumber);







    }


}
