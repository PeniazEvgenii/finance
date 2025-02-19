package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.dto.EUserRole;
import by.it_academy.jd2.configuration.properties.KafkaTopicNameProperties;
import by.it_academy.jd2.repository.ICodeRepository;
import by.it_academy.jd2.repository.entity.CodeEntity;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.api.IMailService;
import by.it_academy.jd2.service.dto.MailDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import by.it_academy.jd2.service.exception.VerificationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VerificationServiceTest {

    @Mock
    private ICodeRepository codeRepository;
    @Mock
    private KafkaTopicNameProperties topicNames;
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Mock
    private IMailService mailService;
    @InjectMocks
    VerificationService verificationService;

    @Test
    void sendCode() {
        when(topicNames.getCodeCreatedTopic()).thenReturn("code-created-topic");

        verificationService.sendCode(getUser());

//        verify(mailService).send(any(MailDto.class));
        verify(codeRepository).saveAndFlush(any(CodeEntity.class));
    }

    @Test
    void checkCodeShouldDeleteCodeWhenValid() {
        VerificationDto verificationDto = new VerificationDto("123456", "test@example.com");
        CodeEntity codeEntity = CodeEntity.builder()
                .code("123456")
                .user(getUser())
                .build();
        when(codeRepository.findByMail("test@example.com")).thenReturn(Optional.of(codeEntity));

        verificationService.checkCode(verificationDto);

        verify(codeRepository).delete(codeEntity);
        verify(codeRepository).flush();
    }

    @Test
    void checkCode_ShouldThrowException_WhenCodeInvalid() {
        CodeEntity codeEntity = CodeEntity.builder()
                .code("123456")
                .user(getUser())
                .build();
        when(codeRepository.findByMail("test@example.com")).thenReturn(Optional.of(codeEntity));

        VerificationDto invalidDto = new VerificationDto("000000", "test@example.com");

        assertThatThrownBy(() -> verificationService.checkCode(invalidDto))
                .isInstanceOf(VerificationException.class);
    }

    @Test
    void checkCode_ShouldThrowException_WhenCodeNotFound() {
        VerificationDto verificationDto = new VerificationDto("123456", "test@example.com");
        when(codeRepository.findByMail("test@example.com"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> verificationService.checkCode(verificationDto))
                .isInstanceOf(VerificationException.class);
    }

    private UserEntity getUser() {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .mail("test@example.com")
                .fio("Fio")
                .status(EUserStatus.ACTIVATED)
                .password("pass")
                .role(EUserRole.USER)
                .build();
    }
}