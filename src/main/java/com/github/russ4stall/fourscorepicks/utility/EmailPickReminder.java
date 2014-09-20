package com.github.russ4stall.fourscorepicks.utility;

import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

/**
 * This is a quartz job that sends an email reminder
 * every Thursday afternoon.
 *
 * This implements mandrill as the SMTP server
 *
 * @author Russ Forstall
 */
public class EmailPickReminder implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList();
        for (User recipients : users){
            sendEmailReminder(recipients.getEmail(), recipients.getName());
        }
    }

    private void sendEmailReminder(String userEmail, String userName){
        Properties props = new Properties();

        WeekCalculator weekCalculator = new WeekCalculator();

        final String host = "smtp.mandrillapp.com";
        final String smtpUsername = "russ4stall@gmail.com";
        final String password = "ACCGYGlUROzLTI7oVQJBsQ";

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", smtpUsername );
        props.put("mail.smtp.password", password);

        Session session = Session.getDefaultInstance(props, null);

        session.setPasswordAuthentication(new URLName("smtp", host, -1, null, smtpUsername, null),
                new PasswordAuthentication(smtpUsername, password));

        String footer = "\n \n \n \n*This is an automated email. Do not respond to this address";

        String msgBody = "Hey " + userName + "!<br><br>Hope you're having a great week!  If you haven't already done so, head over to <a href='http://fourscorepicks.com'>FourScorePicks</a> and make your picks for week "
                + weekCalculator.getWeekOfSeason() + ".  The first game is tonight!<br><br>" + footer;

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("no-reply@fourscorepicks.com", "Four Score Picks"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(userEmail));
            msg.setSubject("Four Score Picks: Make Your Picks for Week " + weekCalculator.getWeekOfSeason());
            //msg.setText(msgBody);
            msg.setContent(msgBody, "text/html");
            Transport.send(msg);
        } catch (AddressException e) {
            System.out.println(e);
        } catch (MessagingException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
}
