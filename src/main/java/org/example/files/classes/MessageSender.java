package org.example.files.classes;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

public class MessageSender {

    private static String email = "ahmetyusufyildirimm@gmail.com";
    public static void sendDelayedEmail(String to, String from, String host, String subject, String text, Date endTime) {
        Timer timer = new Timer();


        long delay =  endTime.getTime() - System.currentTimeMillis();

        // Schedule the email sending task after the delay
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    // Get system properties
                    Properties properties = System.getProperties();

                    // Setup mail server
                    properties.setProperty("mail.smtp.host", host);

                    // Get the default Session object.
                    Session session = Session.getDefaultInstance(properties);

                    // Create a default MimeMessage object.
                    MimeMessage message = new MimeMessage(session);

                    // Set From: header field of the header.
                    message.setFrom(new InternetAddress(from));

                    // Set To: header field of the header.
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                    // Set Subject: header field
                    message.setSubject(subject);

                    // Set the actual message
                    message.setText(text);

                    // Send message
                    Transport.send(message);
                    System.out.println("Email sent successfully at " + endTime);
                } catch (MessagingException mex) {
                    mex.printStackTrace();
                }
            }
        }, delay);
    }

    public static void main(String[] args) {
        // Example usage
        String to = email;
        String from = email;
        String host = "smtp.example.com";
        String subject = "Delayed Email";
        String text = "This is a delayed email sent at " + new Date();

        // Set the end time when you want the email to be sent (e.g., 4 hours later)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        Date endTime = calendar.getTime();

        sendDelayedEmail(to, from, host, subject, text, endTime);
    }
}
