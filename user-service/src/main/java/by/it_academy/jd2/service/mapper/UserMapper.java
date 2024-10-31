package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IUserMapper {

    public UserReadDto mapRead(UserEntity object) {
        return UserReadDto.builder()
                .uuid(object.getId())
                .dtCreate(object.getDtCreate())
                .dtUpdate(object.getDtUpdate())
                .mail(object.getMail())
                .fio(object.getFio())
                .role(object.getRole())
                .status(object.getStatus())
                .build();
    }

    @Override
    public UserEntity mapCreate(UserCreateDto object) {
        return UserEntity.builder()
                .mail(object.getMail())
                .fio(object.getFio())
                .role(object.getRole())
                .status(object.getStatus())
                .password(object.getPassword())
                .build();
    }

    @Override
    public UserEntity mapEntityUpdate(UserUpdateDto fromObject, UserEntity toObject) {
        copy(fromObject, toObject);
        return toObject;
    }


    private void copy(UserUpdateDto userDto, UserEntity user) {
        user.setMail(userDto.getMail());
        user.setFio(userDto.getFio());
        user.setRole(userDto.getRole());
        user.setStatus(userDto.getStatus());
        user.setPassword(userDto.getPassword());
    }

}