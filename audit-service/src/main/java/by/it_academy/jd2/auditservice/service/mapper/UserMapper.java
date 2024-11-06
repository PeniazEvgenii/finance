package by.it_academy.jd2.auditservice.service.mapper;

import by.it_academy.jd2.auditservice.repository.entity.UserEntity;
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
}
