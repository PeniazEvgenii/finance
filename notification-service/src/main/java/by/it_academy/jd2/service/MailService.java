package by.it_academy.jd2.service;


import by.it_academy.jd2.commonlib.event.CodeCreatedEvent;
import by.it_academy.jd2.service.api.IMailService;
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
public class MailService implements IMailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public void send(String mail, String title, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(mail);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom(fromMail);

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailAuthenticationException exception) {
            log.error("authentication problems when trying to connect to the mail sending server");
            throw new MailSendException("Error sending message", exception);
        } catch (MailParseException exception) {
            log.error("error processing mail message: {}", simpleMailMessage);
            throw new MailSendException("Error sending message", exception);
        } catch (org.springframework.mail.MailSendException exception) {
            log.error("an error occurred while sending the message: {}", simpleMailMessage);
            throw new MailSendException("Error sending message", exception);
        }
    }
}
