package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserRegistrationDto;
import by.it_academy.jd2.service.dto.UserUpdateDto;

public interface IUserMapper {

    UserReadDto mapRead(UserEntity object);

    UserEntity mapCreate(UserCreateDto object);

    UserEntity mapEntityUpdate(UserUpdateDto fromObject, UserEntity toObject);

    UserEntity mapRegistration(UserRegistrationDto object);
}
