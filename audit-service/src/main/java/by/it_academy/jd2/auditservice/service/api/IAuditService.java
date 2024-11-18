package by.it_academy.jd2.auditservice.service.api;

import by.it_academy.jd2.auditservice.service.dto.AuditCreateDto;
import by.it_academy.jd2.auditservice.service.dto.AuditReadDto;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import jakarta.validation.Valid;

import java.util.Optional;
import java.util.UUID;

public interface IAuditService {

    PageOf<AuditReadDto> findAll(@Valid PageDto pageDto);

    Optional<AuditReadDto> findById(UUID id);

    void create(AuditCreateDto auditReadDto);
}
