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
 * <p/>
 * Created by russ on 8/14/14.
 */
public class ScrapeWeeklyNFLScoresTask {
    private Integer weekNumber = null;
    private Integer year = null;

    public void execute() {
        WeekCalculator weekCalculator = new WeekCalculator();
        if (weekNumber == null) {
            weekNumber = weekCalculator.getWeekOfSeason();
        }

        if (year == null) {
            year = weekCalculator.getYear();
        }

        GameDao gameDao = new GameDaoImpl();
        List<Game> games = gameDao.getGamesByWeek(weekNumber);

        ScoreScraper scoreScraper = new ScoreScraper(weekNumber, year);
        List<RawScrapedGame> rawScrapedGames = scoreScraper.scrapeWeekScores();


        for (RawScrapedGame scrapedGame : rawScrapedGames) {
            for (Game game : games) {
                if (scrapedGame.matches(game)) {
                    Game g = game;
                    boolean hasScores = true;
                    try {
                        g.setAwayTeamScore(Integer.valueOf(scrapedGame.getAwayTeamScore()));
                        g.setHomeTeamScore(Integer.valueOf(scrapedGame.getHomeTeamScore()));
                    } catch (Exception e) {
                        System.out.println(e.getLocalizedMessage());
                        hasScores = false;
                    }

                    if (hasScores) {
                        //calculate winner and set the winning team
                        if (g.getHomeTeamScore() > g.getAwayTeamScore()) {
                            g.setWinningTeam(g.getHomeTeam());
                        } else if (g.getHomeTeamScore() < g.getAwayTeamScore()) {
                            g.setWinningTeam(g.getAwayTeam());
                        }
                        gameDao.setResult(g);
                        System.out.println("Imported scores for game: " + g.getAwayTeam().getName() + " vs " + g.getHomeTeam().getName());
                    } else {
                        System.out.println("Scraper Warning: Scores aren't available for game: (wk" + weekNumber +") " + g.getAwayTeam().getName() + " vs " + g.getHomeTeam().getName());
                    }
                    //System.out.println(g);

                    break;
                }
            }
        }
    }

    public void setWeekNumber(Integer weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public static void main(String[] args) {
        ScrapeWeeklyNFLScoresTask scrapeTask = new ScrapeWeeklyNFLScoresTask();
        scrapeTask.setWeekNumber(1);
        scrapeTask.execute();
    }

}
