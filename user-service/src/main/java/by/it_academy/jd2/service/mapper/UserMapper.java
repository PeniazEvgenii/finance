package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.repository.entity.EUserRole;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.dto.*;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements IUserMapper {

    private final PasswordEncoder passwordEncoder;

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
                .mail(object.getMail().toLowerCase())
                .fio(object.getFio())
                .role(object.getRole())
                .status(object.getStatus())
                .password(passwordEncoder.encode(object.getPassword()))
                .build();
    }

    @Override
    public UserEntity mapEntityUpdate(UserUpdateDto fromObject, UserEntity toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    public UserEntity mapRegistration(UserRegistrationDto object) {
        return UserEntity.builder()
                .mail(object.getMail().toLowerCase())
                .fio(object.getFio())
                .role(EUserRole.USER)
                .status(EUserStatus.WAITING_ACTIVATION)
                .password(passwordEncoder.encode(object.getPassword()))
                .build();
    }

    public UserSecure mapSecure(UserEntity entity) {
        return UserSecure.builder()
                .id(entity.getId())
                .mail(entity.getMail())
                .fio(entity.getFio())
                .role(entity.getRole())
                .status(entity.getStatus())
                .password(entity.getPassword())
                .dtUpdate(entity.getDtUpdate())           // даты уберем наверное
                .dtCreate(entity.getDtCreate())
                .build();
    }

    private void copy(UserUpdateDto userDto, UserEntity user) {
        user.setMail(userDto.getMail());
        user.setFio(userDto.getFio());
        user.setRole(userDto.getRole());
        user.setStatus(userDto.getStatus());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    }

}
