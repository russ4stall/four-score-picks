package com.github.russ4stall.fourscorepicks.utility;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Weeks;

/**
 * Date: 7/24/13
 * Time: 2:08 PM
 *
 * This calculates and returns the current week of the season.
 *
 * To test how the app will behave at different points in time, change the value
 * of 'timeToTest' to the date you want to test.
 *
 * If the current date is anytime before the end of week 1, this will return 1.
 *
 * @author Russ Forstall
 */
public class WeekCalculator {
    Calendar calendar = new GregorianCalendar();
    int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
    private DateTime timeToTest;

    public WeekCalculator() {
        //This value represents the date the app thinks it is.
        //FOR TESTING PURPOSES: set this value to any date and the app will behave like it is that date
        //todo: FOR PRODUCTION: this value should be set to (DateTime.now())
        //this.timeToTest = new DateTime(2014, 9,9,0,0,0,0);
        this.timeToTest = DateTime.now();
    }

    public WeekCalculator(DateTime timeToTest) {
        this.timeToTest = timeToTest;
    }


    public int getWeekOfSeason() {
        //rf: this is the tuesday of the week before the start of the season
        DateTime startOfSeasonDate = new DateTime(2014, 8, 26, 0, 0, 0, 0);

        Instant startOfSeasonInstant = new Instant(startOfSeasonDate);
        Weeks weekOfSeason = Weeks.weeksBetween(startOfSeasonInstant, timeToTest);

       DateTime weekOneEnd = new DateTime(2014, 9,9,0,0,0,0);

        //Anytime before week 2 starts, return 1
        if(weekOneEnd.isAfter(timeToTest)){
            return 1;
        }

        return weekOfSeason.getWeeks();

    }

    public int getYear() {
        return 2014;
    }

    public int getPreviousWeekOfSeason() {
        return getWeekOfSeason()-1;
    }


}
