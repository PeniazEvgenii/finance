package by.it_academy.jd2.schedulerservice.service.api;

import by.it_academy.jd2.commonlib.dto.UserToken;

import java.util.UUID;

public interface IUserHolder {

    UserToken getUser();

    UUID getUserId();
}
