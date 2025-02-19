package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.event.CodeCreatedEvent;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.IMailRepository;
import by.it_academy.jd2.repository.entity.EMailStatus;
import by.it_academy.jd2.repository.entity.MailEntity;
import by.it_academy.jd2.service.api.INotificationService;
import by.it_academy.jd2.service.dto.MailReadDto;
import by.it_academy.jd2.service.mapper.api.IMailMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService implements INotificationService {

    private final IMailRepository mailRepository;
    private final IMailMapper mailMapper;

    @Transactional
    @Override
    public void save(CodeCreatedEvent codeCreatedEvent, String messageKey) {
        MailEntity mailEntity = mailMapper.toEntity(codeCreatedEvent, messageKey);
        mailRepository.saveAndFlush(mailEntity);
    }

    @Override
    public Optional<MailEntity> findById(UUID id) {
        return mailRepository.findById(id);
    }

    @Override
    public List<MailEntity> findByStatus(EMailStatus status) {
        return mailRepository.findByStatus(status);
    }

    @Override
    public List<MailReadDto> findByStatusError(EMailStatus status) {
        return findByStatus(EMailStatus.ERROR)
                .stream()
                .map(mailMapper::toDto)
                .toList();
    }

    @Override
    public PageOf<MailReadDto> findAll(@Valid PageDto pageDto) {
        Sort sortMail = Sort.sort(MailEntity.class)
                .by(MailEntity::getDtCreate)
                .descending();

        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortMail);

        Page<MailReadDto> page = mailRepository.findAll(pageRequest)
                .map(mailMapper::toDto);

        return PageOf.of(page);
    }

    @Transactional
    @Override
    public void updateStatus(UUID id, EMailStatus status) {
        findById(id)
                .map(entity -> {
                    entity.setStatus(status);
                    return entity;
                })
                .map(mailRepository::saveAndFlush)
                .orElseThrow(IdNotFoundException::new);
    }
}
