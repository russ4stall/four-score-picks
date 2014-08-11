package com.github.russ4stall.fourscorepicks.game;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Weeks;

/**
 * Date: 7/24/13
 * Time: 2:08 PM
 *
 * @author Russ Forstall
 */
public class WeekCalculator {
    Calendar calendar = new GregorianCalendar();
    int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
    private DateTime timeToTest;

    public WeekCalculator() {
      //this.timeToTest = new DateTime(2013, 9,10,0,0,0,0);
        this.timeToTest = DateTime.now();
    }

    public WeekCalculator(DateTime timeToTest) {
        this.timeToTest = timeToTest;
    }

    //todo at start of season, change to return weekOfSeason
    public int getWeekOfSeason() {
        //rf: this is the actual start of the season
        //DateTime startOfSeasonDate = new DateTime(2014, 8, 27, 0, 0, 0, 0);
        DateTime startOfSeasonDate = new DateTime(2014, 7, 27, 0, 0, 0, 0);

        Instant startOfSeasonInstant = new Instant(startOfSeasonDate);
        Weeks weekOfSeason = Weeks.weeksBetween(startOfSeasonInstant, timeToTest);

       //DateTime weekOneEnd = new DateTime(2014, 9,10,0,0,0,0);
       DateTime weekOneEnd = new DateTime(2014, 8,11,0,0,0,0);

        if(weekOneEnd.isAfter(timeToTest)){
            return 1;
        }

        return weekOfSeason.getWeeks();

    }

    public int getPreviousWeekOfSeason() {
        return getWeekOfSeason()-1;
    }


}
