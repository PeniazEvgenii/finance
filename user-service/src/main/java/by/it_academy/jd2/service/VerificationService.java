package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.event.CodeCreatedEvent;
import by.it_academy.jd2.configuration.properties.KafkaTopicNameProperties;
import by.it_academy.jd2.repository.ICodeRepository;
import by.it_academy.jd2.repository.entity.CodeEntity;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.api.IMailService;
import by.it_academy.jd2.service.api.IVerificationService;
import by.it_academy.jd2.service.dto.MailDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import by.it_academy.jd2.service.exception.VerificationException;
import by.it_academy.jd2.service.util.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class VerificationService implements IVerificationService {

    private static final String MAIL_TITLE = "Код для верификации";

    private final ICodeRepository codeRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate2;
    private final KafkaTopicNameProperties topicNames;

    @Transactional
    public void sendCode(UserEntity user) {
        String verifyCode = CodeGenerator.generateNumericCode();
        CodeEntity codeEntity = CodeEntity.builder()
                .code(verifyCode)
                .user(user)
                .build();

        CodeCreatedEvent codeCreatedEvent = CodeCreatedEvent.builder()
                .userId(user.getId())
                .mail(user.getMail())
                .fio(user.getFio())
                .code(verifyCode)
                .title(MAIL_TITLE)
                .build();

        codeRepository.saveAndFlush(codeEntity);

        kafkaTemplate2.send(
                topicNames.getCodeCreatedTopic(),
                UUID.randomUUID().toString(),
                codeCreatedEvent);
    }

    @Transactional
    public void checkCode(VerificationDto verificationDto) {
        CodeEntity codeEntity = codeRepository
                .findByMail(verificationDto.getMail())
                .filter(entity -> verificationDto.getCode().equals(entity.getCode()))
                .orElseThrow(VerificationException::new);

        delete(codeEntity);
    }

    private void delete(CodeEntity codeEntity) {
        codeRepository.delete(codeEntity);
        codeRepository.flush();
    }
}
