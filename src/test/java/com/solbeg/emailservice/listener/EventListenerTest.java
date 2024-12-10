package com.solbeg.emailservice.listener;

import com.solbeg.emailservice.emailfactory.EmailGenerator;
import com.solbeg.emailservice.emailfactory.EmailGeneratorFactory;
import com.solbeg.emailservice.model.EmailRequest;
import com.solbeg.emailservice.util.EmailRequestTestData;
import com.solbeg.emailservice.util.MimeMessagePreparatorTestData;
import com.solbeg.emailservice.validation.EmailRequestValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventListenerTest {

    @InjectMocks
    private EventListener eventListener;

    @Mock
    private EmailRequestValidator emailRequestValidator;

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private EmailGeneratorFactory emailGeneratorFactory;

    @Mock
    private EmailGenerator emailGenerator;

    @Test
    void shouldCheckProcessMyQueueActivation() {
        // given
        EmailRequest request = EmailRequestTestData.getEmailRequest();
        MimeMessagePreparator message = MimeMessagePreparatorTestData.getMimeMessagePreparatorForWelcomeMessage();

        when(emailGeneratorFactory.getEmailGenerator(any())).thenReturn(emailGenerator);
        when(emailGenerator.generateEmail(any())).thenReturn(message);

        // when
        eventListener.processMyQueueActivation(request);

        // then
        verify(emailRequestValidator, times(1)).validateRequest(request);
        verify(emailSender, times(1)).send(message);
    }

    @Test
    void shouldCheckProcessMyQueueInformation() {
        // given
        EmailRequest request = EmailRequestTestData.getEmailRequest();
        MimeMessagePreparator message = MimeMessagePreparatorTestData.getMimeMessagePreparatorForWelcomeMessage();

        when(emailGeneratorFactory.getEmailGenerator(any())).thenReturn(emailGenerator);
        when(emailGenerator.generateEmail(any())).thenReturn(message);

        // when
        eventListener.processMyQueueInformation(request);

        // then
        verify(emailRequestValidator, times(1)).validateRequest(request);
        verify(emailSender, times(1)).send(message);
    }
}