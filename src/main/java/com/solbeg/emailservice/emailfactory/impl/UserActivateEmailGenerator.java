package com.solbeg.emailservice.emailfactory.impl;

import com.solbeg.emailservice.emailfactory.EmailGenerator;
import com.solbeg.emailservice.model.EmailRequest;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static com.solbeg.emailservice.util.Constants.MESSAGE_ACTIVATION;
import static com.solbeg.emailservice.util.Constants.SUBJECT_ACTIVATION;
import static com.solbeg.emailservice.util.Constants.USER_ADMIN;
import static com.solbeg.emailservice.util.Constants.USER_FROM_SEND;

public class UserActivateEmailGenerator implements EmailGenerator {

    @Override
    public MimeMessagePreparator generateEmail(EmailRequest request) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(USER_FROM_SEND);
            message.setTo(USER_ADMIN);
            message.setSubject(SUBJECT_ACTIVATION);
            String activationLink = request.getData().get("activationLink");
            String text = String.format(MESSAGE_ACTIVATION, request.getData().get("firstName"), request.getData().get("lastName"), activationLink);
            message.setText(text, true);
        };
    }
}
