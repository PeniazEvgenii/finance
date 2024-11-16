package by.it_academy.jd2.service.mapper.api;

import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.*;

public interface IUserMapper {

    UserReadDto mapRead(UserEntity object);

    UserEntity mapCreate(UserCreateDto object);

    UserEntity mapEntityUpdate(UserCreateDto fromObject, UserEntity toObject);

    UserEntity mapRegistration(UserRegistrationDto object);

    UserSecure mapSecure(UserEntity entity);
}
