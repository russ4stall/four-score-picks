package com.github.russ4stall.fourscorepicks.tools;

import au.com.bytecode.opencsv.CSVReader;
import com.github.russ4stall.fourscorepicks.Team.Team;
import com.github.russ4stall.fourscorepicks.Team.TeamDao;
import com.github.russ4stall.fourscorepicks.Team.TeamDaoImpl;
import com.github.russ4stall.fourscorepicks.game.dao.GameDao;
import com.github.russ4stall.fourscorepicks.game.dao.GameDaoImpl;
import org.apache.struts2.util.ServletContextAware;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.servlet.ServletContext;
import java.io.*;

import java.util.*;

/**
 * Created by russ on 8/11/14.
 */
public class ScheduleImporterTask {
    private ServletContext servletContext;


    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void execute() throws IOException{
        GameDao gameDao = new GameDaoImpl();
        TeamDao teamDao = new TeamDaoImpl();
        List<Team> teams = teamDao.getAllTeams();
        Map<String, Team> teamMap = new HashMap<String, Team>();

        for (Team team : teams) {
            teamMap.put(team.getLocation() + " " + team.getName(), team);
        }

       /* PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();*/

        InputStreamReader inputStreamReader = new InputStreamReader(servletContext.getResourceAsStream("WEB-INF/classes/data/years_2014__games_left_formatted.csv"));

    //    CSVReader reader = new CSVReader(new FileReader(servletContext.getResource("WEB-INF/classes/data/years_2014__games_left_formatted.csv").getPath()));
        CSVReader reader = new CSVReader(inputStreamReader);
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line

            if (teamMap.containsKey(nextLine[3])) {
                Team awayTeam = teamMap.get(nextLine[3]);
                Team homeTeam = teamMap.get(nextLine[5]);

                DateTimeFormatter formatter = DateTimeFormat.forPattern("MMMM d yyyy h:mm a");
                DateTime dateTime = DateTime.parse(nextLine[2] + " 2014 " + nextLine[6], formatter);
                DateTimeFormatter timestampFormatter = DateTimeFormat.forPattern("yyyy-M-d HH:mm:ss");

                gameDao.addGame(awayTeam.getId(), homeTeam.getId(), Integer.valueOf(nextLine[0]), dateTime.toString(timestampFormatter));
                System.out.println(awayTeam.getId() + " " + homeTeam.getId() + " " + Integer.valueOf(nextLine[0]) + " " + dateTime.toString(timestampFormatter) + " was added to the database");
            } else {
                System.out.println("Error : String (Team Location+Name)from CSV doesn't have a match from the map of teams!");
            }

        }
        System.out.println("*****IMPORT COMPLETE*****");
    }


}
