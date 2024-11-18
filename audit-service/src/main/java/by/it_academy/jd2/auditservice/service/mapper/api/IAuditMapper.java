package by.it_academy.jd2.auditservice.service.mapper.api;

import by.it_academy.jd2.auditservice.repository.entity.AuditEntity;
import by.it_academy.jd2.auditservice.service.dto.AuditCreateDto;
import by.it_academy.jd2.auditservice.service.dto.AuditReadDto;
import by.it_academy.jd2.commonlib.audit.AuditCreate;

public interface IAuditMapper {

    AuditReadDto mapRead(AuditEntity entity);

    AuditEntity mapCreate(AuditCreateDto createDto);
}
