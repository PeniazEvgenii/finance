package by.it_academy.jd2.auditservice.controller;

import by.it_academy.jd2.auditservice.service.api.IAuditService;
import by.it_academy.jd2.auditservice.service.dto.AuditCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class AuditEventController {

    private final IAuditService auditService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AuditCreateDto auditCreate) {

        auditService.create(auditCreate);
    }
}
