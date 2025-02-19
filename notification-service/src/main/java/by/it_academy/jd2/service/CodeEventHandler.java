package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.event.CodeCreatedEvent;
import by.it_academy.jd2.commonlib.exception.NonRetryableException;
import by.it_academy.jd2.commonlib.exception.RetryableException;
import by.it_academy.jd2.repository.IMailRepository;
import by.it_academy.jd2.repository.entity.MailEntity;
import by.it_academy.jd2.service.api.IMailService;
import by.it_academy.jd2.service.mapper.api.IMailMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.mail.MailSendException;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
@KafkaListener(
        topics = "code-created-topic",
        containerFactory = "kafkaListener2")
public class CodeEventHandler {

    private final IMailMapper mailMapper;
    private final IMailRepository mailRepository;

    @Transactional
    @KafkaHandler
    public void handle(@Payload CodeCreatedEvent codeCreatedEvent,
                       @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {

        try {
            log.info("Got event codeCreatedEvent: {}", codeCreatedEvent);
            MailEntity mailEntity = mailMapper.toEntity(codeCreatedEvent, messageKey);
            mailRepository.saveAndFlush(mailEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NonRetryableException(e);
        }
    }
}
