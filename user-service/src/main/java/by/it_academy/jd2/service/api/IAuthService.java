package by.it_academy.jd2.service.api;

import by.it_academy.jd2.service.dto.UserLoginDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserSecure;

public interface IAuthService {

    String login(UserLoginDto loginDto);

    UserReadDto me();
}
