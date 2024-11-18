package by.it_academy.jd2.auditservice.service.mapper;

import by.it_academy.jd2.auditservice.repository.entity.AuditEntity;
import by.it_academy.jd2.auditservice.repository.entity.UserEntity;
import by.it_academy.jd2.auditservice.service.api.IUserService;
import by.it_academy.jd2.auditservice.service.dto.AuditCreateDto;
import by.it_academy.jd2.auditservice.service.dto.AuditReadDto;
import by.it_academy.jd2.auditservice.service.dto.UserCreateDto;
import by.it_academy.jd2.auditservice.service.mapper.api.IAuditMapper;
import by.it_academy.jd2.auditservice.service.mapper.api.IUserMapper;
import by.it_academy.jd2.commonlib.audit.AuditCreate;
import by.it_academy.jd2.commonlib.dto.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditMapper implements IAuditMapper {

    private final IUserMapper userMapper;
    private final IUserService userService;

    @Override
    public AuditReadDto mapRead(AuditEntity entity) {
        return AuditReadDto.builder()
                .id(entity.getId())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .text(entity.getText())
                .type(entity.getType())
                .essenceId(entity.getEssenceId())
                .user(userMapper.mapRead(entity.getUser()))
                .build();
    }

    @Override
    public AuditEntity mapCreate(AuditCreateDto createDto) {
        UserCreateDto userCreate = createDto.getUser();

        UserEntity userEntity = userService
                .findById(userCreate.getId())
                .orElseGet(() -> {
            UserEntity entity = userMapper.mapCreate(userCreate);
            return userService.create(entity);
        });

        return AuditEntity.builder()
                .text(createDto.getText())
                .type(createDto.getType())
                .essenceId(createDto.getEssenceId())
                .user(userEntity)
                .build();
    }

}
