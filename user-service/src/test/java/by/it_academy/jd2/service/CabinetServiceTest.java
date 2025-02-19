package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.dto.EUserRole;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.api.IVerificationService;
import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserRegistrationDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_USER_VERIFY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CabinetServiceTest {
    @Mock
    private IVerificationService verificationService;
    @Mock
    private IAuditService auditService;
    @Mock
    private IUserService userService;
    @Mock
    private IUserMapper userMapper;
    @InjectMocks
    private CabinetService cabinetService;

    @Test
    void testRegistration() {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto("test@example.com", "test", "test");
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .mail(userRegistrationDto.getMail())
                .fio(userRegistrationDto.getFio())
                .password(userRegistrationDto.getPassword())
                .role(EUserRole.USER)
                .status(EUserStatus.ACTIVATED)
                .build();
        doReturn(userCreateDto).when(userMapper).mapCreateDto(userRegistrationDto);

        cabinetService.registration(userRegistrationDto);

        verify(userService, times(1)).create(userCreateDto);
    }

    @Test
    @Disabled("flaky, need to see")
    void testVerify() {
        VerificationDto verificationDto = new VerificationDto("123", "test@example.com");
        UserReadDto userReadDto = UserReadDto.builder()
                .id(UUID.randomUUID())
                .mail("test@example.com")
                .fio("test")
                .role(EUserRole.USER)
                .status(EUserStatus.ACTIVATED)
                .build();
        doReturn(userReadDto).when(userService).updateStatus(verificationDto.getMail(), EUserStatus.ACTIVATED);

        cabinetService.verify(verificationDto);

        verify(verificationService).checkCode(verificationDto);
        verify(auditService).send(AUDIT_USER_VERIFY, userReadDto);
    }
}