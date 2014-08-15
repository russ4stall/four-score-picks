package com.github.russ4stall.fourscorepicks.utility;

import com.github.russ4stall.fourscorepicks.score.ScoreDao;
import com.github.russ4stall.fourscorepicks.score.ScoreDaoImpl;
import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.UserScoreCalculator;
import com.github.russ4stall.fourscorepicks.user.UserScoreCalculatorImpl;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Created by russellf on 8/11/2014.
 */
public class PersistUserWeekScoreTask implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        UserScoreCalculator userScoreCalculator = new UserScoreCalculatorImpl();
        WeekCalculator weekCalculator = new WeekCalculator();
        ScoreDao scoreDao = new ScoreDaoImpl();
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList();

        int week = weekCalculator.getWeekOfSeason();

        for (User user : users) {
            int score = userScoreCalculator.getWeekScore(user.getId(), weekCalculator.getWeekOfSeason());
            scoreDao.addUserWeeklyScore(user.getId(), week, score);

            System.out.println("PersistUserWeekScoreTask ran.");
        }
    }
}
