package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.api.IOperationService;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationReadDto;
import by.it_academy.jd2.service.dto.OperationUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@LoggingAspect
@RestController
@RequiredArgsConstructor
@RequestMapping("/account/{uuid}/operation")
public class OperationController {

    private final IOperationService operationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("uuid") UUID accountId,
                      @RequestBody OperationCreateDto createDto) {

        operationService.create(createDto, accountId);
    }

    @GetMapping
    public PageOf<OperationReadDto> findAll(@PathVariable("uuid") UUID accountId,
                                            @RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "20") Integer size) {

        return operationService.findAll(new PageDto(page, size), accountId);
    }



    @PutMapping("/{uuid_operation}/dt_update/{dt_update}")
    public void update(@PathVariable("uuid") UUID accountId,
                       @PathVariable("uuid_operation") UUID operationId,
                       @PathVariable("dt_update") Instant dtUpdate,
                       @RequestBody OperationCreateDto createDto) {

        OperationUpdateDto updateDto = mapUpdate(accountId, operationId, dtUpdate);
        operationService.update(createDto, updateDto);

    }

    @DeleteMapping("/{uuid_operation}/dt_update/{dt_update}")
    public void delete(@PathVariable("uuid") UUID accountId,
                       @PathVariable("uuid_operation") UUID operationId,
                       @PathVariable("dt_update") Instant dtUpdate) {

        OperationUpdateDto updateDto = mapUpdate(accountId, operationId, dtUpdate);
        operationService.delete(updateDto);
    }

    private OperationUpdateDto mapUpdate(UUID accountId, UUID operationId, Instant dtUpdate) {
        return OperationUpdateDto.builder()
                .operationId(operationId)
                .accountId(accountId)
                .dtUpdate(dtUpdate)
                .build();
    }
}
