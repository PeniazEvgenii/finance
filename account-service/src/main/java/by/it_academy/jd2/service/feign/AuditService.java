package by.it_academy.jd2.service.feign;

import by.it_academy.jd2.commonlib.audit.AuditCreate;
import by.it_academy.jd2.commonlib.audit.EEssenceType;
import by.it_academy.jd2.commonlib.exception.AuditSaveException;
import by.it_academy.jd2.service.api.IUserHolderService;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.feign.client.IAuditClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService implements IAuditService {

    private final IAuditClient auditClient;
    private final IUserHolderService userHolder;

    @Async
    @Override
    public void send(String text, UUID id) {
        try {
            Thread.sleep(10000L);
            System.out.println("Thread.currentThread() = " + Thread.currentThread());
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        AuditCreate audit = getAudit(text, id);

        try {
            Optional.of(audit)
                    .map(auditClient::sendEvent)
                    .map(ResponseEntity::getStatusCode)
                    .filter(HttpStatus.CREATED::equals)
                    .orElseThrow(AuditSaveException::new);
            log.info("Audit saved to {}", id);
        } catch (FeignException e) {
            log.error("Error sending audit event: {}", e.getMessage());
        } catch (AuditSaveException e) {
            log.error("Error save audit event");
        }
    }

    private AuditCreate getAudit(String text, UUID id) {
        return AuditCreate.builder()
                .user(userHolder.getUser())
                .text(text)
                .type(EEssenceType.REPORT)
                .essenceId(id)
                .build();
    }
}