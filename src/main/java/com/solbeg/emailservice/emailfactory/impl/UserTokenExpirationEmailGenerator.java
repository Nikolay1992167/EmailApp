package com.solbeg.emailservice.emailfactory.impl;

import com.solbeg.emailservice.emailfactory.EmailGenerator;
import com.solbeg.emailservice.model.EmailRequest;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static com.solbeg.emailservice.util.Constants.MESSAGE_TOKEN_EXPIRATION;
import static com.solbeg.emailservice.util.Constants.SUBJECT_EXPIRATION;
import static com.solbeg.emailservice.util.Constants.USER_FROM_SEND;

public class UserTokenExpirationEmailGenerator implements EmailGenerator {

    @Override
    public MimeMessagePreparator generateEmail(EmailRequest request) {
        return mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            message.setFrom(USER_FROM_SEND);
            message.setTo(request.getToEmail());
            message.setSubject(SUBJECT_EXPIRATION);
            message.setText(MESSAGE_TOKEN_EXPIRATION, true);
        };
    }
}
