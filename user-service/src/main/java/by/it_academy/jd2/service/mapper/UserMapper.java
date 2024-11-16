package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.commonlib.dto.EUserRole;
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
    public UserEntity mapEntityUpdate(UserCreateDto createDto, UserEntity user) {
        user.setMail(createDto.getMail());
        user.setFio(createDto.getFio());
        user.setRole(createDto.getRole());
        user.setStatus(createDto.getStatus());
        user.setPassword(passwordEncoder.encode(createDto.getPassword()));
        return user;
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

}
