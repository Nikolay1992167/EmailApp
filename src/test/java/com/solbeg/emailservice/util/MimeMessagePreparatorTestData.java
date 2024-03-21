package com.solbeg.emailservice.util;

import com.solbeg.emailservice.model.EmailRequest;
import lombok.Data;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static com.solbeg.emailservice.util.Constants.MESSAGE_ACTIVATION;
import static com.solbeg.emailservice.util.Constants.MESSAGE_TOKEN_EXPIRATION;
import static com.solbeg.emailservice.util.Constants.MESSAGE_WELCOME;
import static com.solbeg.emailservice.util.Constants.SUBJECT_ACTIVATION;
import static com.solbeg.emailservice.util.Constants.SUBJECT_EXPIRATION;
import static com.solbeg.emailservice.util.Constants.SUBJECT_WELCOME;
import static com.solbeg.emailservice.util.Constants.USER_ADMIN;
import static com.solbeg.emailservice.util.Constants.USER_FROM_SEND;

@Data
public class MimeMessagePreparatorTestData {

    public static MimeMessagePreparator getMimeMessagePreparatorForActivation() {
        EmailRequest emailRequest = EmailRequestTestData.builder()
                .build()
                .getEmailRequest();
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(USER_FROM_SEND);
            message.setTo(USER_ADMIN);
            message.setSubject(SUBJECT_ACTIVATION);
            String text = String.format(MESSAGE_ACTIVATION, emailRequest.getData().get("firstName"),
                    emailRequest.getData().get("lastName"),
                    emailRequest.getData().get("activationLink"));
            message.setText(text, true);
        };
    }

    public static MimeMessagePreparator getMimeMessagePreparatorForExpirationUserTokenMessage() {
        EmailRequest emailRequest = EmailRequestTestData.builder()
                .build()
                .getEmailRequest();
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(USER_FROM_SEND);
            message.setTo(emailRequest.getToEmail());
            message.setSubject(SUBJECT_EXPIRATION);
            message.setText(MESSAGE_TOKEN_EXPIRATION, true);
        };
    }

    public static MimeMessagePreparator getMimeMessagePreparatorForWelcomeMessage() {
        EmailRequest emailRequest = EmailRequestTestData.builder()
                .build()
                .getEmailRequest();
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(USER_FROM_SEND);
            message.setTo(emailRequest.getToEmail());
            message.setSubject(SUBJECT_WELCOME);
            String text = String.format(MESSAGE_WELCOME, emailRequest.getData().get("firstName"),
                    emailRequest.getData().get("lastName"));
            message.setText(text, true);
        };
    }
}