package com.github.russ4stall.fourscorepicks.game;

import com.google.common.collect.ImmutableList;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 7/29/13
 * Time: 3:56 PM
 *
 * @author Russ Forstall
 */
public class WeekCalcTest {


    public static void main(String[] args) {

        List<DateTime> timeToTestList = ImmutableList.of(
                new DateTime(2013, 8, 7, 0,0,0,0),
                new DateTime(2013, 9, 5, 0,0,0,0),
                new DateTime(2013, 9, 9, 0,0,0,0),
                new DateTime(2013, 9, 11, 0,0,0,0),
                new DateTime(2013, 9, 16, 0,0,0,0),
                new DateTime(2013, 9, 18, 0,0,0,0),
                new DateTime(2013, 11, 28, 0,0,0,0)
        );

        for (DateTime dateTime : timeToTestList) {
            WeekCalculator weekCalculator = new WeekCalculator(dateTime);

            System.out.println(weekCalculator.getWeekOfSeason());
        }


    }

}
