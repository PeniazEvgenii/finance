package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.api.IAccountService;
import by.it_academy.jd2.service.dto.AccountCreateDto;
import by.it_academy.jd2.service.dto.AccountReadDto;
import by.it_academy.jd2.service.dto.AccountUpdateDto;
import by.it_academy.jd2.service.mapper.IAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;
    private final IAccountMapper accountMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody AccountCreateDto createDto) {

        accountService.create(createDto);
    }

    @GetMapping
    public PageOf<AccountReadDto> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "20") Integer size) {

        return accountService.findAll(new PageDto(page, size));              //непонятно про пользователя, пока хардкод в таблице
    }

    @GetMapping("/{uuid}")
    public AccountReadDto findById(@PathVariable("uuid") UUID id) {

        return accountService.findById(id)
                .orElseThrow(IdNotFoundException::new);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void update(@PathVariable("uuid") UUID id,
                       @PathVariable("dt_update") Instant dtUpdate,
                       @RequestBody AccountCreateDto createDto) {

        AccountUpdateDto updateDto = accountMapper.mapUpdateDto(createDto, id, dtUpdate);
        accountService.update(updateDto);
    }
}
