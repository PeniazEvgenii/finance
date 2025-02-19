package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.event.RegisterCompletedEvent;
import by.it_academy.jd2.commonlib.exception.NonRetryableException;
import by.it_academy.jd2.commonlib.exception.RetryableException;
import by.it_academy.jd2.service.api.IMailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSendException;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@KafkaListener(
        topics = "${topic.name.register-completed-topic}",
        containerFactory = "kafkaListener2")
@Component
@RequiredArgsConstructor
public class RegisterCompletedHandler {

    private static final String REGISTER = ", Вы успешно прошли регистрацию!";
    private static final String TITLE = "Регистрация";
    private final IMailService mailService;

    @KafkaHandler
    public void handle(@Payload RegisterCompletedEvent completedEvent) {
        String body = completedEvent.getFio() + REGISTER;

        try {
            mailService.send(completedEvent.getMail(), TITLE, body);
        } catch (MailSendException e) {
            log.error(e.getMessage());
            throw new RetryableException(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NonRetryableException(e);
        }
    }
}
