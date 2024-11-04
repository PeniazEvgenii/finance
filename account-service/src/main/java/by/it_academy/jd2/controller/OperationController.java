package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationReadDto;
import by.it_academy.jd2.service.test.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account/{uuid}/operation")
public class OperationController {

    private final VerifyService verifyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void crete(@PathVariable("uuid") UUID accountId,
                      @RequestBody @Validated OperationCreateDto createDto) {           //валюта проверить запросом и категорию проверить запросом  !!!

        verifyService.verifyCurrencyExists(createDto.getCurrency());


    }

    @GetMapping
    public PageOf<OperationReadDto> findAll(@PathVariable("uuid") UUID accountId,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return null;
    }

    @PutMapping("/{uuid_operation}/dt_update/{dt_update}")
    public void update(@PathVariable("uuid") UUID accountId,
                       @PathVariable("uuid_operation") UUID operationId,
                       @PathVariable("dt_update") Instant dtUpdate,
                       @RequestBody @Validated OperationCreateDto createDto){

    }

    @DeleteMapping("/{uuid_operation}/dt_update/{dt_update}")
    public void delete(@PathVariable("uuid") UUID accountId,
                       @PathVariable("uuid_operation") UUID operationId,
                       @PathVariable("dt_update") Instant dtUpdate) {

    }
}
