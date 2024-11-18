package by.it_academy.jd2.controller.feign;

import by.it_academy.jd2.commonlib.audit.AuditCreate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "audit", url = "${service.audit}")
public interface AuditClient {

    @PostMapping("/event")
    void sendEvent(@RequestBody AuditCreate auditCreate);
}
