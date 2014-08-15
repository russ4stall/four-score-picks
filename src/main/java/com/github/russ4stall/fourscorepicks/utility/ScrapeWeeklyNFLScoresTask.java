package com.github.russ4stall.fourscorepicks.utility;

import com.github.russ4stall.fourscorepicks.game.Game;
import com.github.russ4stall.fourscorepicks.game.dao.GameDao;
import com.github.russ4stall.fourscorepicks.game.dao.GameDaoImpl;
import com.github.russ4stall.fourscorepicks.team.Team;
import com.github.russ4stall.fourscorepicks.team.TeamEnum;
import com.github.russ4stall.fourscorepicks.tools.RawScrapedGame;
import com.github.russ4stall.fourscorepicks.tools.ScoreScraper;

import java.util.List;

/**
 * Scrapes scores from nfl.com and updates the database
 *
 * Created by russ on 8/14/14.
 */
public class ScrapeWeeklyNFLScoresTask {
    private Integer weekNumber = null;

    public void execute() {
        if (weekNumber == null) {
            WeekCalculator weekCalculator = new WeekCalculator();
            weekNumber = weekCalculator.getWeekOfSeason();
        }

        GameDao gameDao = new GameDaoImpl();
        List<Game> games = gameDao.getGamesByWeek(weekNumber);

        ScoreScraper scoreScraper = new ScoreScraper(weekNumber);
        List<RawScrapedGame> rawScrapedGames =  scoreScraper.scrapeWeekScores();


        for (RawScrapedGame scrapedGame : rawScrapedGames) {
            Game candidate = new Game();
            candidate.setAwayTeam(Team.valueOf(scrapedGame.getAwayTeam()));
            candidate.setHomeTeam(Team.valueOf(scrapedGame.getHomeTeam()));

            System.out.println(scrapedGame);
        }


    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public static void main(String[] args) {
        ScrapeWeeklyNFLScoresTask scrapeTask = new ScrapeWeeklyNFLScoresTask();
        scrapeTask.setWeekNumber(1);
        scrapeTask.execute();
    }

}
