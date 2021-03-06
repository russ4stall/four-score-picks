package com.github.russ4stall.fourscorepicks.utility;

import org.quartz.*;
import org.quartz.TriggerBuilder.*;
import org.quartz.JobBuilder.*;
import org.quartz.SimpleScheduleBuilder.*;

import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static org.quartz.CronScheduleBuilder.weeklyOnDayAndHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Date: 9/30/13
 * Time: 1:31 PM
 *
 * @author Russ Forstall
 */
public class WebAppContextListener implements ServletContextListener {
    private Scheduler scheduler;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();


            JobDetail emailJobDetail = newJob(EmailPickReminder.class)
                    .withIdentity("job1", "group1")
                    .build();

            Trigger emailTrigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    //.withSchedule(weeklyOnDayAndHourAndMinute(DateBuilder.MONDAY, 15, 52))
                    .withSchedule(weeklyOnDayAndHourAndMinute(DateBuilder.THURSDAY, 15, 0))
                    .build();

            scheduler.scheduleJob(emailJobDetail, emailTrigger);
            scheduler.start();
        } catch (SchedulerException e) {
            throw new RuntimeException("Unable to start quartz", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (scheduler != null) {
                scheduler.shutdown();
            }
        } catch (SchedulerException e) {
            throw new RuntimeException("Unable to stop quartz", e);
        }
    }
}
