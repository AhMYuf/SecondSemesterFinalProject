package org.example.files.classes;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class MessageSender {

    private static final String EMAIL_FROM = "hacksmithinacoderverse@gmail.com";
    private static final String APP_PASSWORD = "lipu zxdq vcum obgk";

    /**
     * No instance can be made from this class
     */
    private MessageSender() {}

    /**
     * Send email to a recipient at a later time
     * @param recipient the one receiving the email
     * @param message the message or content of the email
     * @param subject the subject of the email
     * @param endDate the date when the email should be sent (format: "yyyy-MM-dd")
     * @param endTime the time when the email should be sent (format: "HH:mm:ss")
     */
    public static void sendEmailAt(String recipient, String message, String subject, String endDate, String endTime) throws MessagingException {
        String dateTime = endDate + " " + endTime;
        LocalDateTime sendDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Date date = Date.from(sendDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    MimeMessage mimeMessage = new MimeMessage(getEmailSession());
                    mimeMessage.setFrom(new InternetAddress(EMAIL_FROM));
                    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
                    mimeMessage.setSubject(subject);
                    mimeMessage.setText(message);
                    Transport.send(mimeMessage);
                    System.out.println("Email sent successfully at " + dateTime);
                } catch (MessagingException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, date);
    }

    /**
     * Get the email properties, for better structure
     * @return the email properties
     */
    private static Properties getGmailProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        return prop;
    }

    /**
     * Get the email session
     * @return the email session
     */
    private static Session getEmailSession() {
        return Session.getInstance(getGmailProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
            }
        });
    }

    public static void main(String[] args) {
        try {
            // Schedule email to be sent at a specific date and time
            sendEmailAt("recipient@example.com", "This is a scheduled email.", "Scheduled Email", "2024-05-21", "18:00:00");
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
