package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.event.RegisterCompletedEvent;
import by.it_academy.jd2.configuration.properties.KafkaTopicNameProperties;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.api.ICabinetService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.api.IVerificationService;
import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserRegistrationDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.ExecutionException;

import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_USER_VERIFY;

@LoggingAspect
@Validated
@Service
@RequiredArgsConstructor
public class CabinetService implements ICabinetService {

    private final IVerificationService verificationService;
    private final IAuditService auditService;
    private final IUserService userService;
    private final IUserMapper userMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate2;
    private final KafkaTopicNameProperties topicNames;


    public void registration(@Valid UserRegistrationDto userRegistrationDto) {
        UserCreateDto userCreateDto = userMapper.mapCreateDto(userRegistrationDto);
        userService.create(userCreateDto);
    }

    @Transactional
    public void verify(@Valid VerificationDto verificationDto) {
        verificationService.checkCode(verificationDto);
        UserReadDto user = userService.updateStatus(verificationDto.getMail(),
                                                    EUserStatus.ACTIVATED);

        kafkaTemplate2.send(
                topicNames.getRegisterCompletedTopic(),
                user.getId().toString(),
                new RegisterCompletedEvent(user.getId(), user.getMail(), user.getFio()));

        auditService.send(AUDIT_USER_VERIFY, user);
    }
}
