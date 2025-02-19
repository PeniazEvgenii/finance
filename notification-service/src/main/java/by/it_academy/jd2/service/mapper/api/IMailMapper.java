package by.it_academy.jd2.service.mapper.api;

import by.it_academy.jd2.commonlib.event.CodeCreatedEvent;
import by.it_academy.jd2.repository.entity.MailEntity;
import by.it_academy.jd2.service.dto.MailReadDto;

public interface IMailMapper {

    MailEntity toEntity(CodeCreatedEvent codeCreatedEvent, String messageKey);

    MailReadDto toDto(MailEntity entity);
}
