package by.it_academy.jd2.service.feign;

import by.it_academy.jd2.commonlib.audit.AuditCreate;
import by.it_academy.jd2.commonlib.audit.EEssenceType;
import by.it_academy.jd2.commonlib.dto.UserToken;
import by.it_academy.jd2.commonlib.exception.AuditSaveException;
import by.it_academy.jd2.commonlib.exception.ConnectionException;
import by.it_academy.jd2.service.api.IUserHolder;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.feign.client.IAuditClient;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService implements IAuditService {

    private final IAuditClient auditClient;
    private final IUserHolder userHolder;
    private final IUserMapper userMapper;

    @Override
    public void send(String text, UserReadDto userRead) {

        UserReadDto currentUser = userHolder.getUser();
        currentUser = (currentUser == null) ? userRead : currentUser;

        UserToken userToken = userMapper.mapToken(currentUser);

        AuditCreate audit = AuditCreate.builder()
                .user(userToken)
                .text(text)
                .type(EEssenceType.USER)
                .essenceId(userRead.getId().toString())
                .build();

        try {
            Optional.of(audit)
                    .map(auditClient::sendEvent)
                    .map(ResponseEntity::getStatusCode)
                    .filter(HttpStatus.CREATED::equals)
                    .orElseThrow(AuditSaveException::new);
            log.info("Audit saved to {}", currentUser);
        } catch (FeignException e) {
            log.error("Error sending audit event: {}", e.getMessage());
//            throw new ConnectionException();
        } catch (AuditSaveException e) {
            log.error("Error save audit event");
        }
    }
}
