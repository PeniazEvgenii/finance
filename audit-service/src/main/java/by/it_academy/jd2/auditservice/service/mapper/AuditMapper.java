package by.it_academy.jd2.auditservice.service.mapper;

import by.it_academy.jd2.auditservice.repository.entity.AuditEntity;
import by.it_academy.jd2.auditservice.service.dto.AuditReadDto;
import by.it_academy.jd2.auditservice.service.mapper.api.IAuditMapper;
import by.it_academy.jd2.auditservice.service.mapper.api.IUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditMapper implements IAuditMapper {

    private final IUserMapper userMapper;

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
}
