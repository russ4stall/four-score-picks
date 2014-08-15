package com.github.russ4stall.fourscorepicks.utility;

import com.github.russ4stall.fourscorepicks.user.User;
import com.github.russ4stall.fourscorepicks.user.dao.UserDao;
import com.github.russ4stall.fourscorepicks.user.dao.UserDaoImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

/**
 * Date: 9/30/13
 * Time: 1:57 PM
 *
 * @author Russ Forstall
 */
public class EmailPickReminder implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.getUserList();
        //System.out.println("Send email to the following:");
        for (User recipients : users){
            //System.out.println(recipients.getEmail() + "  " + recipients.getName());
            sendEmailReminder(recipients.getEmail(), recipients.getName());
        }
        //sendEmailReminder("russ4stall@gmail.com", "Russtopher");
    }


    private void sendEmailReminder(String userEmail, String userName){

        WeekCalculator weekCalculator = new WeekCalculator();

        Properties props = new Properties();


        //props.put("mail.smtp.host", "0.0.0.0");
        props.put("mail.smtp.host", "192.168.2.41");
        props.put("mail.smtp.port", "25");

        Session session = Session.getDefaultInstance(props, null);

        String footer = "\n \n \n \nThis is an automated email. Do not respond to this address";

        String msgBody = "Hey " + userName + "!<br><br>This email was sent as a reminder.  Head over to <a href='http://forstall.isd-testnet.com/fourscorepicks'>FourScorePicks</a> to make your picks for week "
                + weekCalculator.getWeekOfSeason() + ".  The first game is tonight!<br><br>" + footer;

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("noreply@forstall.isd-testnet.com", "Four Score Picks"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(userEmail));
            msg.setSubject("FourScorePicks: Make Your Picks for Week " + weekCalculator.getWeekOfSeason());
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
