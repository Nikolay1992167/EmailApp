package com.solbeg.emailservice.service;

import com.solbeg.emailservice.emailfactory.EmailGenerator;
import com.solbeg.emailservice.emailfactory.EmailGeneratorFactory;
import com.solbeg.emailservice.model.EmailRequest;
import com.solbeg.emailservice.service.impl.EmailServiceImpl;
import com.solbeg.emailservice.util.EmailRequestTestData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.test.context.TestConstructor;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private EmailGeneratorFactory emailGeneratorFactory;

    @Mock
    private EmailGenerator emailGenerator;

    @Mock
    private MimeMessagePreparator mimeMessagePreparator;


    @Test
    void shouldSendEmailWithData() {
        // given
        EmailRequest emailRequest = EmailRequestTestData.builder()
                .build()
                .getEmailRequest();
        when(emailGeneratorFactory.getEmailGenerator(emailRequest.getEmailType()))
                .thenReturn(emailGenerator);
        when(emailGenerator.generateEmail(emailRequest))
                .thenReturn(mimeMessagePreparator);

        // when
        emailService.sendEmailWithData(emailRequest);

        // then
        verify(emailSender, times(1)).send(mimeMessagePreparator);
    }
}