package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.api.IAuthService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.dto.UserLoginDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserSecure;
import by.it_academy.jd2.service.exception.AccountStatusException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;
    private final UserHolder userHolder;
    private final JwtTokenHandler jwtTokenHandler;


    @Override
    public String login(UserLoginDto loginDto) {

        UserSecure userSecure = userService
                .findByMailWithPass(loginDto.getMail())
                .orElseThrow(IdNotFoundException::new);

        if (!passwordEncoder.matches(loginDto.getPassword(), userSecure.getPassword())) {
            throw new IllegalArgumentException("Логин или пароль неверный");
        }

        if (checkNoActiveStatus(userSecure.getStatus())) {
            throw new AccountStatusException("Учетная запись находится в статусе " + userSecure.getStatus());
        }

        return jwtTokenHandler.generateToken(userSecure);
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
