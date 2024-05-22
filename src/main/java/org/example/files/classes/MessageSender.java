package org.example.files.classes;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class responsible for sending emails at a scheduled time.
 */
public class MessageSender {

    public static void main(String[] args) {
        MessageSender messageSender = new MessageSender();

        // Define test email details
        String subject = "Test Email";
        String message = "This is a test email sent at a scheduled time.";
        String endDate = "23-05-2024"; // Tomorrow's date
        String endTime = "01:55:00";   // Noon

        try {
            // Send the email
            messageSender.sendEmailAt(subject, message, endDate, endTime);

            // Sleep for a while to allow the email to be sent (adjust the time if needed)
            Thread.sleep(60000); // Sleep for 1 minute

        } catch (MessagingException | InterruptedException e) {
            // Catch any exceptions and fail the test if an exception occurs
            e.printStackTrace();
            org.junit.Assert.fail("Exception occurred while sending the email.");
        }

        // If the test reaches this point without throwing any exceptions, the email should have been sent successfully.
        // You might add additional assertions or manual verification steps if needed.
    }

    private static final String EMAIL_FROM = "hacksmithinacoderverse@gmail.com";
    private String Email_To = "hacksmithinacoderverse@gmail.com";
    private static final String APP_PASSWORD = "lipu zxdq vcum obgk";

    /**
     * Prevents the instantiation of this class.
     */
    public MessageSender() {}

    /**
     * Sends an email to a recipient at a specified date and time.
     *
     * @param subject  The subject of the email.
     * @param message  The content of the email.
     * @param endDate  The date when the email should be sent (format: "dd-MM-yyyy").
     * @param endTime  The time when the email should be sent (format: "HH:mm:ss").
     * @throws MessagingException if there is an issue with sending the email.
     */
    public void sendEmailAt(String subject, String message, String endDate, String endTime) throws MessagingException {
        String dateTime = endDate + " " + endTime;
        LocalDateTime sendDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        Date date = Date.from(sendDateTime.atZone(ZoneId.systemDefault()).toInstant());

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    MimeMessage mimeMessage = new MimeMessage(getEmailSession());
                    mimeMessage.setFrom(new InternetAddress(EMAIL_FROM));
                    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Email_To));
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
     * Gets the properties required for sending emails via Gmail.
     *
     * @return the email properties.
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
     * Gets the email session required for sending emails.
     *
     * @return the email session.
     */
    private static Session getEmailSession() {
        return Session.getInstance(getGmailProperties(), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_FROM, APP_PASSWORD);
            }
        });
    }

    /**
     * Sets the recipient email address.
     *
     * @param email_To the recipient email address.
     */
    public void setEmail_To(String email_To) {
        Email_To = email_To;
    }
}
