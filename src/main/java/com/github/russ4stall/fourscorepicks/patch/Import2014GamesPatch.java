package com.github.russ4stall.fourscorepicks.patch;

import com.github.russ4stall.fourscorepicks.tools.ScheduleImporterTask;

import javax.servlet.ServletContext;
import java.io.IOException;

/**
 * Created by russ on 8/14/14.
 */
public class Import2014GamesPatch implements Patch {
    ServletContext servletContext;

    @Override
    public boolean run() {
        ScheduleImporterTask scheduleImporterTask = new ScheduleImporterTask();
        scheduleImporterTask.setServletContext(servletContext);
        try {
            scheduleImporterTask.execute();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
        System.out.println(this.getClass().getName() + " completed!");
        return true;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
