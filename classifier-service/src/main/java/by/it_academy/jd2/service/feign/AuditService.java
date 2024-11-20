package by.it_academy.jd2.service.feign;

import by.it_academy.jd2.commonlib.audit.AuditCreate;
import by.it_academy.jd2.commonlib.audit.EEssenceType;
import by.it_academy.jd2.commonlib.exception.AuditSaveException;
import by.it_academy.jd2.commonlib.exception.ConnectionException;
import by.it_academy.jd2.service.api.IUserHolder;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.feign.client.IAuditClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditService implements IAuditService {

    private final IAuditClient auditClient;
    private final IUserHolder userHolder;

    @Override
    public void send(String text, UUID id) {
        AuditCreate audit = AuditCreate.builder()
                .user(userHolder.getUser())
                .text(text)
                .type(EEssenceType.REPORT)
                .essenceId(id.toString())
                .build();

        try {
            Optional.of(audit)
                    .map(auditClient::sendEvent)
                    .map(ResponseEntity::getStatusCode)
                    .filter(HttpStatus.CREATED::equals)
                    .orElseThrow(AuditSaveException::new);
        } catch (FeignException e) {
            throw new ConnectionException();
        }
    }
}
