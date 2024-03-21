package com.solbeg.emailservice.emailfactory;

import com.solbeg.emailservice.emailfactory.impl.UserActivateEmailGenerator;
import com.solbeg.emailservice.emailfactory.impl.UserTokenExpirationEmailGenerator;
import com.solbeg.emailservice.emailfactory.impl.UserWelcomeEmailGenerator;
import com.solbeg.emailservice.enums.EmailType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EmailGeneratorFactoryTest {

    @Autowired
    private EmailGeneratorFactory emailGeneratorFactory;

    @ParameterizedTest
    @MethodSource("provideEmailTypes")
    void testGetEmailGenerator(EmailType type, Class<? extends EmailGenerator> expectedClass) {
        EmailGenerator generator = emailGeneratorFactory.getEmailGenerator(type);
        assertTrue(expectedClass.isInstance(generator));
    }

    private static Stream<Arguments> provideEmailTypes() {
        return Stream.of(
                Arguments.of(EmailType.USER_ACTIVATE, UserActivateEmailGenerator.class),
                Arguments.of(EmailType.USER_WELCOME_EMAIL, UserWelcomeEmailGenerator.class),
                Arguments.of(EmailType.USER_TOKEN_EXPIRATION, UserTokenExpirationEmailGenerator.class)
        );
    }
}