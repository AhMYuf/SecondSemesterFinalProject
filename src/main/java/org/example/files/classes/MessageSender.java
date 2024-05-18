package org.example.files.classes;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class MessageSender {

    // Find your Account Sid and Token at console.twilio.com
    public static final String ACCOUNT_SID = "AC84a3516aa5da5c8a1f82e7f921335eb3";
    public static final String AUTH_TOKEN = "9af55d6df091484453ec2df1f6fb371f";
    public static String phoneNumber = "+15146911882";

    public class Example {
        // Find your Account SID and Auth Token at twilio.com/console
        // and set the environment variables. See http://twil.io/secure
        public static void main(String[] args) {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                            new com.twilio.type.PhoneNumber(phoneNumber),
                            new com.twilio.type.PhoneNumber(phoneNumber),
                            "It's me Ahmet")
                    .create();

            System.out.println(message.getSid());
        }
    }
    public void sendMessage() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(
                        new PhoneNumber(getPhoneNumber()),
                        new PhoneNumber(getPhoneNumber()),
                        "This is the ship that made the Kessel Run in fourteen parsecs?"
                )
                .create();

        System.out.println(message.getSid());
    }


    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        MessageSender.phoneNumber = phoneNumber;
    }
}