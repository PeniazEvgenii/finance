package by.it_academy.jd2.service.api;

import by.it_academy.jd2.commonlib.dto.UserToken;

import java.util.UUID;

public interface IUserHolderService {

    UserToken getUser();

    UUID getUserId();
}
