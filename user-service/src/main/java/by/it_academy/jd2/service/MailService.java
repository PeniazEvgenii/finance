package by.it_academy.jd2.service;

import by.it_academy.jd2.service.exception.EmailSendException;
import by.it_academy.jd2.service.dto.MailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService implements IMailService{

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private final String fromEmail;

    public void send(MailDto mailDto) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(mailDto.getRecipient());
        simpleMailMessage.setSubject(mailDto.getTitle());
        simpleMailMessage.setText(mailDto.getBody());
        simpleMailMessage.setFrom(fromEmail);
        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailAuthenticationException exception) {
            log.error("проблемы аутентификации при попытке соединения с сервером отправки почты");
            throw new EmailSendException("Ошибка отправки сообщения", exception);
        } catch (MailParseException exception) {
            log.error("Ошибка при обработке почтового сообщения: {}", simpleMailMessage);
            throw new EmailSendException("Ошибка отправки сообщения", exception);
        } catch (MailSendException exception) {
            log.error("произошла ошибка при отправке письма{}", simpleMailMessage);
            throw new EmailSendException("Ошибка отправки сообщения", exception);
        }

    }
}
