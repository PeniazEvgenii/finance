package by.it_academy.jd2.integration.service;

import by.it_academy.jd2.commonlib.dto.EUserRole;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.integration.IntegrationTestBase;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class UserServiceTest extends IntegrationTestBase {

    private static final String USER_ID = "0ecf84a4-7c70-4b36-b3a0-f0280be8ed0c";
    private static final String USER_MAIL = "user@example1233334.com";

    private final IUserService userService;

    @Test
    void findAll() {
        PageOf<UserReadDto> users = userService.findAll(new PageDto(0, 3));
        assertEquals(0, users.getNumber());
        assertEquals(3, users.getSize());
        assertTrue(users.isFirst());
        assertFalse(users.isLast());
    }

    @Test
    void findById() {
        Optional<UserReadDto> user = userService.findById(UUID.fromString(USER_ID));
        assertTrue(user.isPresent());
        user.ifPresent(usr -> assertEquals(USER_MAIL, usr.getMail()));
    }

    @Test
    void create() {
        UserCreateDto userCreateDto = new UserCreateDto("test@mail.ru", "test", EUserRole.ADMIN, EUserStatus.ACTIVATED, "test");
        userService.create(userCreateDto);
        Optional<UserReadDto> expectUser = userService.findByMail("test@mail.ru");
        assertTrue(expectUser.isPresent());
    }

    @Test
    void update() {
        Optional<UserReadDto> user = userService.findById(UUID.fromString(USER_ID));
        UserReadDto userReadDto = user.get();

        UserUpdateDto updateDto = new UserUpdateDto(userReadDto.getUuid(), userReadDto.getDtUpdate());

        UserCreateDto createDto = UserCreateDto.builder()
                .mail(userReadDto.getMail())
                .role(EUserRole.ADMIN)
                .status(EUserStatus.ACTIVATED)
                .fio("update")
                .password("update")
                .build();
        userService.update(createDto, updateDto);
        Optional<UserReadDto> userUpdate = userService.findById(UUID.fromString(USER_ID));
        assertTrue(userUpdate.isPresent());
        userUpdate.ifPresent(u -> assertEquals("update", u.getFio()));
    }

    @Test
    void findByMail() {
        Optional<UserReadDto> user = userService.findByMail(USER_MAIL);
        assertTrue(user.isPresent());
        user.ifPresent(usr -> assertEquals(UUID.fromString(USER_ID), usr.getUuid()));
    }

}