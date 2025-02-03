package by.it_academy.jd2.service.mapper;

import by.it_academy.jd2.commonlib.dto.EUserRole;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserRegistrationDto;
import by.it_academy.jd2.service.dto.UserSecure;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserMapper userMapper;

    private static final String RAW_PASSWORD = "password123";
    private static final String ENCODED_PASSWORD = "encodedPassword";
    private static final UUID USER_ID = UUID.randomUUID();

    @Test
    void mapUserEntityToUserReadDto() {
        UserEntity userEntity = getUserEntity();

        UserReadDto actualResult = userMapper.mapRead(userEntity);

        UserReadDto expectedResult = getUserReadDto();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void mapUserCreateDtoToUserEntity() {
        UserCreateDto userCreateDto = getUserCreateDto();
        Mockito.doReturn(ENCODED_PASSWORD).when(passwordEncoder).encode(RAW_PASSWORD);

        UserEntity actualResult = userMapper.mapCreate(userCreateDto);

        UserEntity expectedResult = getUserEntity();
        assertAll(
                () -> assertEquals(expectedResult.getPassword(), actualResult.getPassword()),
                () -> assertEquals(expectedResult.getMail(), actualResult.getMail()),
                () -> assertEquals(expectedResult.getRole(), actualResult.getRole()),
                () -> assertEquals(expectedResult.getFio(), actualResult.getFio()),
                () -> assertEquals(expectedResult.getStatus(), actualResult.getStatus())
        );
    }

    @Test
    void mapEntityUpdate() {
        when(passwordEncoder.encode(RAW_PASSWORD)).thenReturn(ENCODED_PASSWORD);
        UserCreateDto userCreateDto = getUserCreateDto();
        UserEntity userEntity = getUserEntity();

        UserEntity updatedUser = userMapper.mapEntityUpdate(userCreateDto, userEntity);
        assertAll(
                () -> assertEquals(userCreateDto.getMail(), updatedUser.getMail()),
                () -> assertEquals(userCreateDto.getFio(), updatedUser.getFio()),
                () -> assertEquals(userCreateDto.getRole(), updatedUser.getRole()),
                () -> assertEquals(userCreateDto.getStatus(), updatedUser.getStatus()),
                () -> assertEquals(ENCODED_PASSWORD, updatedUser.getPassword())
        );
    }

    @Test
    void mapUserRegistrationDtoToUserCreateDto() {
        UserRegistrationDto registrationDto = getRegistrationDto();

        UserCreateDto actualResult = userMapper.mapCreateDto(registrationDto);

        UserCreateDto expectedResult = getUserCreateDto();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void mapSecure() {
        UserEntity userEntity = getUserEntity();

        UserSecure actualResult = userMapper.mapSecure(userEntity);

        UserSecure expectedResult = getUserSecure();
        assertEquals(expectedResult, actualResult);
    }

    private UserEntity getUserEntity() {
        return UserEntity.builder()
                .id(USER_ID)
                .mail("test@example.com")
                .fio("Test User")
                .role(EUserRole.USER)
                .status(EUserStatus.WAITING_ACTIVATION)
                .password(ENCODED_PASSWORD)
                .build();
    }

    private UserReadDto getUserReadDto() {
        return UserReadDto.builder()
                .id(USER_ID)
                .mail("test@example.com")
                .fio("Test User")
                .role(EUserRole.USER)
                .status(EUserStatus.WAITING_ACTIVATION)
                .build();
    }

    private UserSecure getUserSecure() {
        return UserSecure.builder()
                .id(USER_ID)
                .mail("test@example.com")
                .fio("Test User")
                .role(EUserRole.USER)
                .status(EUserStatus.WAITING_ACTIVATION)
                .password(ENCODED_PASSWORD)
                .build();
    }

    private UserRegistrationDto getRegistrationDto() {
        return new UserRegistrationDto(
                "test@example.com",
                "Test User",
                RAW_PASSWORD);
    }

    private UserCreateDto getUserCreateDto() {
        return UserCreateDto.builder()
                .mail("test@example.com")
                .fio("Test User")
                .role(EUserRole.USER)
                .status(EUserStatus.WAITING_ACTIVATION)
                .password(RAW_PASSWORD)
                .build();
    }
}