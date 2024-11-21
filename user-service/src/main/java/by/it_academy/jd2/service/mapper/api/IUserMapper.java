package by.it_academy.jd2.service.mapper.api;

import by.it_academy.jd2.commonlib.dto.UserToken;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.*;

public interface IUserMapper {

    UserReadDto mapRead(UserEntity object);

    UserEntity mapCreate(UserCreateDto object);

    UserEntity mapEntityUpdate(UserCreateDto createDto, UserEntity entity);

    UserCreateDto mapCreateDto(UserRegistrationDto registrationDto);

    UserSecure mapSecure(UserEntity entity);

    UserToken mapToken(UserSecure user);

    UserReadDto mapReadFromSecure(UserSecure userSecure);

    UserToken mapToken(UserReadDto userReadDto);
}
