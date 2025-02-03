package by.it_academy.jd2.service;

import by.it_academy.jd2.service.dto.MailDto;
import by.it_academy.jd2.service.exception.MailSendException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;
    @InjectMocks
    private MailService mailService;

    private MailDto mailDto;

    @BeforeEach
    void setUp() {
        mailDto = new MailDto("test@example.com", "Test Body", "Test Subject");
    }

    @Test
    void sendShouldSendMailSuccessfully() {
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));

        assertDoesNotThrow(() -> mailService.send(mailDto));
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void sendShouldThrowMailSendExceptionOnAuthenticationError() {
        doThrow(new MailAuthenticationException("Auth Error")).when(javaMailSender).send(any(SimpleMailMessage.class));

        assertThrows(MailSendException.class, () -> mailService.send(mailDto));
    }

    @Test
    void sendShouldThrowMailSendExceptionOnParseError() {
        doThrow(new MailParseException("Parse Error")).when(javaMailSender).send(any(SimpleMailMessage.class));

        assertThrows(MailSendException.class, () -> mailService.send(mailDto));
    }

    @Test
    void sendShouldThrowMailSendExceptionOnSendError() {
        doThrow(new org.springframework.mail.MailSendException("Send Error")).when(javaMailSender).send(any(SimpleMailMessage.class));

        assertThrows(MailSendException.class, () -> mailService.send(mailDto));
    }


}