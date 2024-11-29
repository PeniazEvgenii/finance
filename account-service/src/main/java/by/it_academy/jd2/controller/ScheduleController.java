package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.dto.OperationFeignDto;
import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.service.api.IOperationService;
import by.it_academy.jd2.service.dto.AccountReadDto;
import by.it_academy.jd2.service.dto.OperationCreateDto;
import by.it_academy.jd2.service.dto.OperationReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping()
public class ScheduleController {

    private final IOperationService operationService;
    private final IAccountService accountService;

    @GetMapping("/info/account/{uuid}/user/{uuid_user}")
    ResponseEntity<AccountReadDto> findAccountByIdAndUserId(@PathVariable("uuid") UUID id,
                                                            @PathVariable("uuid_user") UUID userId) {
        return accountService.findByIdAndUserId(id, userId)
                .map(account -> ResponseEntity.ok().body(account))
                .orElseGet(() -> ResponseEntity.ok().body(null));
    }


    @PostMapping("info/account/operation")
    ResponseEntity<UUID> createOperation(@RequestBody OperationFeignDto operationDto) {

        OperationCreateDto createDto = new OperationCreateDto(operationDto.getDate(),
                operationDto.getDescription(),
                operationDto.getCategoryId(),
                operationDto.getValue(),
                operationDto.getCurrencyId());

        return operationService.saveSchedule(createDto, operationDto.getAccountId())
                .map(OperationReadDto::getId)
                .map(id -> ResponseEntity.ok().body(id))
                .orElseGet(() -> ResponseEntity.ok().body(null));
    }
}
