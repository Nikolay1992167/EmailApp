package com.solbeg.emailservice.emailfactory.impl;

import com.solbeg.emailservice.model.EmailRequest;
import com.solbeg.emailservice.util.EmailRequestTestData;
import com.solbeg.emailservice.util.MimeMessagePreparatorTestData;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserActivateEmailGeneratorTest {

    @Autowired
    private JavaMailSender javaMailSender;

    private final UserActivateEmailGenerator userActivateEmailGenerator = new UserActivateEmailGenerator();

    @Test
    void shouldReturnExpectedMimeMessagePreparatorForActivationUser() throws Exception {
        // given
        EmailRequest emailRequest = EmailRequestTestData.builder()
                .build()
                .getEmailRequest();
        MimeMessagePreparator expected = MimeMessagePreparatorTestData.getMimeMessagePreparatorForActivation();
        MimeMessage expectedMessage = javaMailSender.createMimeMessage();
        expected.prepare(expectedMessage);

        // when
        MimeMessagePreparator actual = userActivateEmailGenerator.generateEmail(emailRequest);
        MimeMessage actualMessage = javaMailSender.createMimeMessage();
        actual.prepare(actualMessage);

        // then
        assertThat(actualMessage.getSubject()).isEqualTo(expectedMessage.getSubject());
        assertThat(actualMessage.getFrom()).isEqualTo(expectedMessage.getFrom());
        assertThat(actualMessage.getAllRecipients()).isEqualTo(expectedMessage.getAllRecipients());
    }
}