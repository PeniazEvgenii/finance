package by.it_academy.jd2.auditservice.service;

import by.it_academy.jd2.auditservice.repository.IAuditRepository;
import by.it_academy.jd2.auditservice.repository.entity.AuditEntity;
import by.it_academy.jd2.auditservice.service.api.IAuditService;
import by.it_academy.jd2.auditservice.service.dto.AuditCreateDto;
import by.it_academy.jd2.auditservice.service.dto.AuditReadDto;
import by.it_academy.jd2.auditservice.service.mapper.api.IAuditMapper;
import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.SaveException;
import by.it_academy.jd2.commonlib.page.PageOf;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;
import java.util.UUID;

@LoggingAspect
@Service
@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuditService implements IAuditService {

    private final IAuditRepository auditRepository;
    private final IAuditMapper auditMapper;

    @Override
    public PageOf<AuditReadDto> findAll(@Valid PageDto pageDto) {
        Sort sortAudit = Sort.sort(AuditEntity.class)
                .by(AuditEntity::getDtCreate)
                .descending();

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortAudit);

        Page<AuditReadDto> page = auditRepository.findAll(pageRequest)
                .map(auditMapper::mapRead);

        return PageOf.of(page);
    }

    @Override
    public AuditReadDto findById(UUID id) {
        return auditRepository.findById(id)
                .map(auditMapper::mapRead)
                .orElseThrow(IdNotFoundException::new);
    }

    @Transactional
    @Override
    public void create(AuditCreateDto createDto) {
        Optional.of(createDto)
                .map(auditMapper::mapCreate)
                .map(auditRepository::saveAndFlush)
                .orElseThrow(SaveException::new);
    }
}
