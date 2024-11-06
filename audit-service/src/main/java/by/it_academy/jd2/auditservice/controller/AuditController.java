package by.it_academy.jd2.auditservice.controller;

import by.it_academy.jd2.auditservice.repository.entity.AuditEntity;
import by.it_academy.jd2.auditservice.service.AuditService;
import by.it_academy.jd2.auditservice.service.dto.AuditReadDto;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.page.PageOf;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/audit")
public class AuditController {

    private final AuditService auditService;

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public PageOf<AuditReadDto> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return auditService.findAll(new PageDto(page, size));
    }


    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{uuid}")
    public AuditReadDto findById(@PathVariable("uuid") UUID id) {

        return auditService.findById(id)
                .orElseThrow(IdNotFoundException::new);
    }
}
