package by.it_academy.jd2.service.api;

import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.event.CodeCreatedEvent;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.entity.EMailStatus;
import by.it_academy.jd2.repository.entity.MailEntity;
import by.it_academy.jd2.service.dto.MailReadDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface INotificationService {

    void save(CodeCreatedEvent codeCreatedEvent, String messageKey);

    Optional<MailEntity> findById(UUID id);

    List<MailEntity> findByStatus(EMailStatus status);

    public List<MailReadDto> findByStatusError(EMailStatus status);

    public PageOf<MailReadDto> findAll(@Valid PageDto pageDto);

    void updateStatus(UUID id, EMailStatus status);
}
