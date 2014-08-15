package com.github.russ4stall.fourscorepicks.tools;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This tool scrapes the nfl website for the scores of a given week
 *
 * Created by russ on 8/14/14.
 */
public class ScoreScraper {

    private int year = 2014;
    private int weekNumber;
    private SeasonSection seasonSection = SeasonSection.REG;

    public List<RawScrapedGame> getScores() {

        String url = "http://www.nfl.com/scores/" + year + "/" + seasonSection + weekNumber;

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

        Elements scoreboxWrappers = document.getElementsByClass("scorebox-wrapper");

        List<RawScrapedGame> rawScrapedGames = new ArrayList<RawScrapedGame>();
        for (Element scoreboxWrapper : scoreboxWrappers) {
            RawScrapedGame rawScrapedGame = new RawScrapedGame();
            rawScrapedGame.setDate(scoreboxWrapper.getElementsByClass("date").html());

            rawScrapedGame.setAwayTeam(scoreboxWrapper.getElementsByClass("away-team").first().getElementsByClass("team-name").first().text());
            rawScrapedGame.setAwayTeamScore(scoreboxWrapper.getElementsByClass("away-team").first().getElementsByClass("total-score").first().text());
            rawScrapedGame.setHomeTeam(scoreboxWrapper.getElementsByClass("home-team").first().getElementsByClass("team-name").first().text());
            rawScrapedGame.setHomeTeamScore(scoreboxWrapper.getElementsByClass("home-team").first().getElementsByClass("total-score").first().text());

            rawScrapedGames.add(rawScrapedGame);
        }




        return rawScrapedGames;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSeasonSection(SeasonSection seasonSection) {
        this.seasonSection = seasonSection;
    }

    //test
    public static void main(String[] args) {
        ScoreScraper scoreScraper = new ScoreScraper();
        scoreScraper.setWeekNumber(1);

        for (RawScrapedGame game : scoreScraper.getScores()) {
            System.out.println(game);
        }
    }
}
