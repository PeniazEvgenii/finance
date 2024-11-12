package by.it_academy.jd2.controller;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void create(@RequestBody @Validated(CreateAction.class) UserCreateDto userCreateDto) {
        userService.create(userCreateDto);
    }

    @GetMapping
    public PageOf<UserReadDto> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return userService.findAll(new PageDto(page, size));
    }

    @GetMapping("/{uuid}")
    public UserReadDto findById(@PathVariable(name = "uuid") UUID id) {
        return userService.findById(id)
                .orElseThrow(IdNotFoundException::new);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    @ResponseStatus(value = HttpStatus.OK)
    public void update(@PathVariable("uuid") UUID id,
                       @PathVariable("dt_update") Instant dtUpdate,
                       @RequestBody @Validated(UpdateAction.class) UserCreateDto userCreateDto) {

        UserUpdateDto userUpdateDto = UserUpdateDto.builder()
                .fio(userCreateDto.getFio())
                .role(userCreateDto.getRole())
                .status(userCreateDto.getStatus())
                .mail(userCreateDto.getMail())
                .password(userCreateDto.getPassword())
                .id(id)
                .dtUpdate(dtUpdate)
                .build();

        userService.update(userUpdateDto);

    }
}
