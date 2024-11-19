package by.it_academy.jd2.auditservice.controller;

import by.it_academy.jd2.auditservice.service.api.IAuditService;
import by.it_academy.jd2.auditservice.service.dto.AuditReadDto;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/audit")
public class AuditController {

    private final IAuditService auditService;

    @GetMapping
    public PageOf<AuditReadDto> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return auditService.findAll(new PageDto(page, size));
    }


    @GetMapping("/{uuid}")
    public AuditReadDto findById(@PathVariable("uuid") UUID id) {

        return auditService.findById(id);
    }
}
