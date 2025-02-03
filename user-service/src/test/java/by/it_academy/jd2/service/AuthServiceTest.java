package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.dto.EUserRole;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.api.IUserHolder;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.dto.UserLoginDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserSecure;
import by.it_academy.jd2.service.exception.AccountStatusException;
import by.it_academy.jd2.service.exception.InvalidCredentialsException;
import by.it_academy.jd2.service.feign.api.IAuditService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_USER_LOGIN;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenHandler jwtTokenHandler;
    @Mock
    private IAuditService auditService;
    @Mock
    private IUserService userService;
    @Mock
    private IUserHolder userHolder;
    @InjectMocks
    AuthService authService;

    @Test
    void loginSuccess() {
        String token = "token";
        UserSecure userSecure = getUserSecure(EUserStatus.ACTIVATED);
        UserReadDto userReadDto = getUserReadDto();
        UserLoginDto userLoginDto = new UserLoginDto(userSecure.getMail(), userSecure.getPassword());
        doReturn(Optional.of(userSecure)).when(userService).findByMailWithPass(userSecure.getMail());
        doReturn(true).when(passwordEncoder).matches(userLoginDto.getPassword(), userSecure.getPassword());
        doReturn(token).when(jwtTokenHandler).generateToken(userSecure);
        doReturn(userReadDto).when(userService).getReadDto(userSecure);

        String result = authService.login(userLoginDto);

        assertThat(result).isEqualTo(token);
        verify(auditService, Mockito.times(1)).send(eq(AUDIT_USER_LOGIN), eq(userReadDto));
    }



    @Test
    void loginShouldThrowExceptionInvalidCredential() {
        UserLoginDto userLoginDto = new UserLoginDto("dummy", "123");
        UserSecure userSecure = getUserSecure(EUserStatus.ACTIVATED);
        doReturn(Optional.of(userSecure)).when(userService).findByMailWithPass(userLoginDto.getMail());
        doReturn(false).when(passwordEncoder).matches(userLoginDto.getPassword(), userSecure.getPassword());

        assertThrows(InvalidCredentialsException.class, () -> authService.login(userLoginDto));
        verifyNoInteractions(jwtTokenHandler);
        verifyNoInteractions(auditService);

    }

    @Test
    void loginShouldThrowExceptionUserNotFound() {
        UserLoginDto userLoginDto = new UserLoginDto("dummy", "123");
        doReturn(Optional.empty()).when(userService).findByMailWithPass(userLoginDto.getMail());

        assertThrows(IdNotFoundException.class, () -> authService.login(userLoginDto));
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(jwtTokenHandler);
        verifyNoInteractions(auditService);
    }


    @Test
    void loginShouldThrowExceptionAccountNotActive() {
        UserSecure userSecure = getUserSecure(EUserStatus.DEACTIVATED);
        UserLoginDto userLoginDto = new UserLoginDto(userSecure.getMail(), userSecure.getPassword());
        doReturn(Optional.of(userSecure)).when(userService).findByMailWithPass(userSecure.getMail());
        doReturn(true).when(passwordEncoder).matches(userLoginDto.getPassword(), userSecure.getPassword());

        assertThrows(AccountStatusException.class, () -> authService.login(userLoginDto));
        verifyNoInteractions(jwtTokenHandler);
        verifyNoInteractions(auditService);
    }



    private static UserSecure getUserSecure(EUserStatus status) {
        return UserSecure.builder()
                .id(UUID.randomUUID())
                .mail("test@test.com")
                .fio("fio")
                .status(status)
                .role(EUserRole.USER)
                .build();
    }

    @Test
    void me() {
        UserReadDto userReadDto = getUserReadDto();
        doReturn(userReadDto).when(userHolder).getUser();

        UserReadDto result = authService.me();

        assertThat(result).isEqualTo(userReadDto);
    }

    private UserReadDto getUserReadDto() {
        return UserReadDto.builder()
                .id(UUID.randomUUID())
                .mail("test@test.com")
                .fio("fio")
                .status(EUserStatus.ACTIVATED)
                .role(EUserRole.USER)
                .build();
    }
}