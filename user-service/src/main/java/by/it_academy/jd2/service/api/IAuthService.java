package by.it_academy.jd2.service.api;

import by.it_academy.jd2.service.dto.UserLoginDto;
import by.it_academy.jd2.service.dto.UserReadDto;

public interface IAuthService {

    String login(UserLoginDto loginDto);

    UserReadDto me();
}
