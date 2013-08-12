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
        this.timeToTest = DateTime.now();
    }

    public WeekCalculator(DateTime timeToTest) {
        this.timeToTest = timeToTest;
    }

    //todo at start of season, change to return weekOfSeason
    public int getWeekOfSeason() {
        //rf: this is the actual start of the season
        //DateTime startOfSeasonDate = new DateTime(2013, 8, 27, 0, 0, 0, 0);

        //rf: this is for testing
        DateTime startOfSeasonDate = new DateTime(2013, 7,23, 0, 0, 0, 0);


        Instant startOfSeasonInstant = new Instant(startOfSeasonDate);
        Weeks weekOfSeason = Weeks.weeksBetween(startOfSeasonInstant, timeToTest);


        if(startOfSeasonDate.isAfterNow()){
            return 1;
        }

        return weekOfSeason.getWeeks();

    }

    public int getPreviousWeekOfSeason() {
        return getWeekOfSeason()-1;
    }


}
