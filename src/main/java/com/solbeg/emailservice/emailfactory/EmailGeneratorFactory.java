package com.solbeg.emailservice.emailfactory;

import com.solbeg.emailservice.emailfactory.impl.UserActivateEmailGenerator;
import com.solbeg.emailservice.emailfactory.impl.UserTokenExpirationEmailGenerator;
import com.solbeg.emailservice.emailfactory.impl.UserWelcomeEmailGenerator;
import com.solbeg.emailservice.enums.EmailType;
import org.springframework.stereotype.Service;

@Service
public class EmailGeneratorFactory {

    public EmailGenerator getEmailGenerator(EmailType type) {
        return switch (type) {
            case USER_ACTIVATE -> new UserActivateEmailGenerator();
            case USER_WELCOME_EMAIL -> new UserWelcomeEmailGenerator();
            case USER_TOKEN_EXPIRATION -> new UserTokenExpirationEmailGenerator();
        };
    }
}
