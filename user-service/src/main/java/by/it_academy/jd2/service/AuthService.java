package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.api.IAuthService;
import by.it_academy.jd2.service.api.IUserHolder;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.dto.UserLoginDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserSecure;
import by.it_academy.jd2.service.exception.AccountStatusException;
import by.it_academy.jd2.service.exception.InvalidCredentialsException;
import by.it_academy.jd2.service.feign.api.IAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_USER_LOGIN;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenHandler jwtTokenHandler;
    private final IAuditService auditService;
    private final IUserService userService;
    private final IUserHolder userHolder;

    @Override
    public String login(UserLoginDto loginDto) {

        UserSecure userSecure = userService
                .findByMailWithPass(loginDto.getMail())
                .orElseThrow(IdNotFoundException::new);

        if (!passwordEncoder.matches(loginDto.getPassword(), userSecure.getPassword())) {
            throw new InvalidCredentialsException();
        }
        if (checkNoActiveStatus(userSecure.getStatus())) {
            throw new AccountStatusException("Учетная запись находится в статусе " + userSecure.getStatus());
        }

        String token = jwtTokenHandler.generateToken(userSecure);
        auditService.send(AUDIT_USER_LOGIN, userService.getReadDto(userSecure));

        return token;
    }

    @Override
    public UserReadDto me() {
        return userHolder.getUser();
    }

    private boolean checkNoActiveStatus(EUserStatus status) {
        return status.equals(EUserStatus.DEACTIVATED) ||
                status.equals(EUserStatus.WAITING_ACTIVATION);
    }
}
