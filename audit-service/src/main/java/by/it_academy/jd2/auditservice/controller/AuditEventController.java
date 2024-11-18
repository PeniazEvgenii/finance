package by.it_academy.jd2.auditservice.controller;

import by.it_academy.jd2.auditservice.service.api.IAuditService;
import by.it_academy.jd2.auditservice.service.dto.AuditCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class AuditEventController {

    private final IAuditService auditService;

    @PostMapping
    public void create(@RequestBody AuditCreateDto auditCreateDto) {    //надо смотреть что будет передаваться
        auditService.create(auditCreateDto);
    }
}
