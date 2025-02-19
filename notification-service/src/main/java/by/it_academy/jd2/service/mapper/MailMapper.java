package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.commonlib.event.CodeCreatedEvent;
import by.it_academy.jd2.repository.entity.EMailStatus;
import by.it_academy.jd2.repository.entity.MailEntity;
import by.it_academy.jd2.service.dto.MailReadDto;
import by.it_academy.jd2.service.mapper.api.IMailMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MailMapper implements IMailMapper {

    @Override
    public MailEntity toEntity(CodeCreatedEvent codeCreatedEvent, String messageKey) {
        return MailEntity.builder()
                .id(UUID.fromString(messageKey))
                .code(codeCreatedEvent.getCode())
                .mail(codeCreatedEvent.getMail())
                .fio(codeCreatedEvent.getFio())
                .title(codeCreatedEvent.getTitle())
                .status(EMailStatus.WAITED)
                .build();
    }

    @Override
    public MailReadDto toDto(MailEntity entity) {
        return MailReadDto.builder()
                .id(entity.getId())
                .mail(entity.getMail())
                .code(entity.getCode())
                .title(entity.getTitle())
                .fio(entity.getFio())
                .status(entity.getStatus())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .build();
    }
}
