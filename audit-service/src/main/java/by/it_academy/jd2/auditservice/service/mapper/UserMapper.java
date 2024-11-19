package by.it_academy.jd2.auditservice.service.mapper;

import by.it_academy.jd2.auditservice.repository.entity.UserEntity;
import by.it_academy.jd2.auditservice.service.dto.UserCreateDto;
import by.it_academy.jd2.auditservice.service.dto.UserReadDto;
import by.it_academy.jd2.auditservice.service.mapper.api.IUserMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IUserMapper {

    @Override
    public UserReadDto mapRead(UserEntity entity) {
        return UserReadDto.builder()
                .id(entity.getId())
                .fio(entity.getFio())
                .mail(entity.getMail())
                .role(entity.getRole())
                .build();
    }

    @Override
    public UserEntity mapCreate(UserCreateDto userCreate) {
        return UserEntity.builder()
                .mail(userCreate.getMail())
                .fio(userCreate.getFio())
                .role(userCreate.getRole())
                .id(userCreate.getId())
                .dtUpdate(userCreate.getDtUpdate())
                .build();
    }

    @Override
    public UserEntity updateIfNeed(UserEntity user, UserCreateDto userCreate) {
        if(!user.getDtUpdate().equals(userCreate.getDtUpdate())) {
            return buildEntity(user, userCreate);
        }
        return user;
    }


    private UserEntity buildEntity(UserEntity user, UserCreateDto dto) {
        user.setFio(dto.getFio());
        user.setMail(dto.getMail());
        user.setFio(dto.getFio());
        user.setDtUpdate(dto.getDtUpdate());
        return user;
    }



}
