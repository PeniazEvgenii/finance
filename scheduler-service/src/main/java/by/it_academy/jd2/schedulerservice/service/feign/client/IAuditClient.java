package by.it_academy.jd2.schedulerservice.service.feign.client;

import by.it_academy.jd2.commonlib.audit.AuditCreate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "audit", url = "${service.audit}")
public interface IAuditClient {

    @PostMapping("/event")
    ResponseEntity<Void> sendEvent(@RequestBody AuditCreate auditCreate);
}
