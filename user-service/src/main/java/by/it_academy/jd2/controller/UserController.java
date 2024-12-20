package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.dto.*;
import by.it_academy.jd2.service.validation.group.CreateAction;
import by.it_academy.jd2.service.validation.group.UpdateAction;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@LoggingAspect
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody @Validated(CreateAction.class) UserCreateDto createDto) {

        userService.create(createDto);
    }

    @GetMapping
    public PageOf<UserReadDto> findAll(@RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "20") Integer size) {

        return userService.findAll(new PageDto(page, size));
    }

    @GetMapping("/{uuid}")
    public UserReadDto findById(@PathVariable(name = "uuid") UUID id) {

        return userService.findById(id);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public void update(@PathVariable("uuid") UUID id,
                       @PathVariable("dt_update") Instant dtUpdate,
                       @RequestBody @Validated(UpdateAction.class) UserCreateDto createDto) {

        userService.update(createDto, new UserUpdateDto(id, dtUpdate));
    }
}
