package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.api.IOperationService;
import by.it_academy.jd2.service.dto.OperationDto;
import by.it_academy.jd2.service.dto.OperationReadDto;
import by.it_academy.jd2.service.dto.OperationUpdateDto;
import by.it_academy.jd2.service.mapper.IOperationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account/{uuid}/operation")
public class OperationController {

    private final IOperationService operationService;
    private final IOperationMapper operationMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("uuid") UUID accountId,
                      @RequestBody OperationDto dto) {

        operationService.create(operationMapper.mapCreateDto(dto, accountId));
    }

    @GetMapping
    public PageOf<OperationReadDto> findAll(@PathVariable("uuid") UUID accountId,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return operationService.findAll(new PageDto(page, size), accountId);
    }



    @PutMapping("/{uuid_operation}/dt_update/{dt_update}")
    public void update(@PathVariable("uuid") UUID accountId,
                       @PathVariable("uuid_operation") UUID operationId,
                       @PathVariable("dt_update") Instant dtUpdate,
                       @RequestBody OperationDto dto) {

        OperationUpdateDto operationUpdateDto = mapUpdate(accountId, operationId, dtUpdate);
        operationService.update(dto, operationUpdateDto);

    }

    @DeleteMapping("/{uuid_operation}/dt_update/{dt_update}")
    public void delete(@PathVariable("uuid") UUID accountId,
                       @PathVariable("uuid_operation") UUID operationId,
                       @PathVariable("dt_update") Instant dtUpdate) {

        OperationUpdateDto dto = mapUpdate(accountId, operationId, dtUpdate);
        operationService.delete(dto);
    }

    private OperationUpdateDto mapUpdate(UUID accountId, UUID operationId, Instant dtUpdate) {
        return OperationUpdateDto.builder()
                .operationId(operationId)
                .accountId(accountId)
                .dtUpdate(dtUpdate)
                .build();
    }
}
