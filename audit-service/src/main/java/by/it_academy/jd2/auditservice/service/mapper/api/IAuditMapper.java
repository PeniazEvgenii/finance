package by.it_academy.jd2.auditservice.service.mapper.api;

import by.it_academy.jd2.auditservice.repository.entity.AuditEntity;
import by.it_academy.jd2.auditservice.service.dto.AuditReadDto;

public interface IAuditMapper {

    AuditReadDto mapRead(AuditEntity entity);
}
