package by.it_academy.jd2.integration.repository;

import by.it_academy.jd2.integration.IntegrationTestBase;
import by.it_academy.jd2.integration.annotation.IT;
import by.it_academy.jd2.repository.IUserRepository;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.UserStatus;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class IUserRepositoryTest extends IntegrationTestBase {

    private static final String USER_ID = "ebf13772-c7b2-47dd-983b-a95989372e47";
    private static final String USER_MAIL = "string@mail";
    private static final String USER_MAIL_UPPER_CASE = "STRING@MAIL";

    private final IUserRepository userRepository;

    @Test
    void findByMailIgnoreCase() {
        Optional<UserEntity> user = userRepository.findByMailIgnoreCase(USER_MAIL);
        assertTrue(user.isPresent());
        user.ifPresent(usr -> assertEquals(UUID.fromString(USER_ID), usr.getId()));

        Optional<UserEntity> userUpper = userRepository.findByMailIgnoreCase(USER_MAIL_UPPER_CASE);
        assertTrue(userUpper.isPresent());
        user.ifPresent(usr -> assertEquals(UUID.fromString(USER_ID), usr.getId()));
    }

    @Test
    void findByStatusWithoutCode() {
        List<UserEntity> users = userRepository.findByStatusWithoutCode(UserStatus.WAITING_ACTIVATION);
        assertThat(users).hasSize(2);
    }
}