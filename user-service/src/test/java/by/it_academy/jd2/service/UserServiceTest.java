package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.dto.EUserRole;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.IUserRepository;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserSecure;
import by.it_academy.jd2.service.dto.UserUpdateDto;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_USER_CREATE;
import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_USER_UPDATE;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final String MAIL = "test@test.com";

    @Mock
    private IUserRepository userRepository;
    @Mock
    private IAuditService auditService;
    @Mock
    private IUserMapper userMapper;
    @InjectMocks
    private UserService userService;

    private UUID userId;

    @BeforeEach
    void init() {
        userId = UUID.randomUUID();
    }

    @Test
    void findAll() {
        UserEntity userEntity = getUser();
        UserReadDto userReadDto = getUserReadDto();
        PageDto pageDto = new PageDto(0, 10);
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("dtCreate").descending());
        Page<UserEntity> pageUser = new PageImpl<>(List.of(userEntity));
        doReturn(pageUser).when(userRepository).findAll(pageRequest);
        doReturn(userReadDto).when(userMapper).mapRead(userEntity);

        PageOf<UserReadDto> result = userService.findAll(pageDto);

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getTotalElements()).isEqualTo(1L);
        assertThat(result.getContent().get(0).getId()).isEqualTo(userId);
    }

    @Test
    void findByIdSuccessReturnUser() {
        UserEntity userEntity = getUser();
        UserReadDto userReadDto = getUserReadDto();
        doReturn(Optional.of(userEntity)).when(userRepository).findById(userId);
        doReturn(userReadDto).when(userMapper).mapRead(userEntity);

        UserReadDto result = userService.findById(userId);

        assertThat(result).isEqualTo(userReadDto);
    }

    @Test
    void findByIdShouldThrowWhenUserNotFound() {

        doReturn(Optional.empty()).when(userRepository).findById(userId);

        assertThrows(IdNotFoundException.class, () -> userService.findById(userId));
        verifyNoInteractions(userMapper);
        verify(userRepository, times(1)).findById(any());
    }

    @Test
    void createSuccess() {
        UserCreateDto userCreateDto = getUserCreateDto();
        UserEntity userEntity = getUser();
        UserReadDto userReadDto = getUserReadDto();
        doReturn(userEntity).when(userMapper).mapCreate(userCreateDto);
        doReturn(userEntity).when(userRepository).saveAndFlush(userEntity);
        doReturn(userReadDto).when(userMapper).mapRead(userEntity);

        userService.create(userCreateDto);

        verify(userRepository).saveAndFlush(userEntity);
        verify(auditService).send(eq(AUDIT_USER_CREATE), eq(userReadDto));
    }

    @Test
    void findByMailSuccess() {
        UserEntity user = getUser();
        UserReadDto userReadDto = getUserReadDto();
        doReturn(Optional.of(user)).when(userRepository).findByMailIgnoreCase(MAIL);
        doReturn(userReadDto).when(userMapper).mapRead(user);

        Optional<UserReadDto> result = userService.findByMail(MAIL);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(user.getId());
    }

    @Test
    void findByMailNotFoundUser() {
        doReturn(Optional.empty()).when(userRepository).findByMailIgnoreCase(MAIL);

        Optional<UserReadDto> result = userService.findByMail(MAIL);

        assertThat(result).isEmpty();
        verifyNoInteractions(userMapper);
    }

    @Test
    void findByMailWithPassSuccess() {
        UserSecure userSecure = getUserSecure();
        UserEntity user = getUser();
        doReturn(Optional.of(user)).when(userRepository).findByMailIgnoreCase(MAIL);
        doReturn(userSecure).when(userMapper).mapSecure(user);

        Optional<UserSecure> result = userService.findByMailWithPass(MAIL);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(user.getId());
    }

    @Test
    void findByMailWithPassNotFoundUser() {
        doReturn(Optional.empty()).when(userRepository).findByMailIgnoreCase(MAIL);

        Optional<UserSecure> result = userService.findByMailWithPass(MAIL);

        assertThat(result).isEmpty();
        verifyNoInteractions(userMapper);
    }

    @Test
    void findByStatusWithoutCode() {
        UserEntity user = getUser();
        List<UserEntity> listUser = List.of(user);
        doReturn(listUser).when(userRepository).findByStatusWithoutCode(any());

        List<UserEntity> result = userService.findByStatusWithoutCode(EUserStatus.ACTIVATED);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo(user.getId());
    }

    @Test
    void updateStatusShouldUpdateStatusWhenUserExists() {
        UserEntity user = getUser();
        UserReadDto userReadDto = getUserReadDto();
        doReturn(Optional.of(user)).when(userRepository).findByMailIgnoreCase(MAIL);
        doReturn(userReadDto).when(userMapper).mapRead(user);
        doReturn(user).when(userRepository).saveAndFlush(user);

        UserReadDto result = userService.updateStatus(MAIL, EUserStatus.DEACTIVATED);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(userReadDto);
        assertThat(user.getStatus()).isEqualTo(EUserStatus.DEACTIVATED);
        verify(userRepository).saveAndFlush(user);
    }

    @Test
    void updateStatusShouldThrowExceptionWhenUserNotFound() {
        when(userRepository.findByMailIgnoreCase(MAIL))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.updateStatus(MAIL, EUserStatus.ACTIVATED))
                .isInstanceOf(IdNotFoundException.class);
        verifyNoInteractions(userMapper);
        verify(userRepository, never()).saveAndFlush(any(UserEntity.class));
    }

    @Test
    void updateStatusShouldUpdateUserStatusWhenUserExists() {
        UserEntity user = getUser();
        UserReadDto userReadDto = getUserReadDto();
        when(userRepository.findByMailIgnoreCase(MAIL))
                .thenReturn(Optional.of(user));
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(userMapper.mapRead(user)).thenReturn(userReadDto);

        UserReadDto result = userService.updateStatus(MAIL, EUserStatus.DEACTIVATED);

        assertThat(result).isEqualTo(userReadDto);
        assertThat(user.getStatus()).isEqualTo(EUserStatus.DEACTIVATED);
    }

    @Test
    void updateShouldUpdateUserAndSendAudit() {
        UserEntity user = getUser();
        Instant dtUpdate = Instant.now();
        user.setDtUpdate(dtUpdate);
        UserReadDto userReadDto = getUserReadDto();
        UserCreateDto createDto = getUserCreateDto();
        UserUpdateDto updateDto = new UserUpdateDto(userId, dtUpdate);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.mapEntityUpdate(createDto, user)).thenReturn(user);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(userMapper.mapRead(user)).thenReturn(userReadDto);

        userService.update(createDto, updateDto);

        verify(userRepository).saveAndFlush(user);
        verify(auditService).send(eq(AUDIT_USER_UPDATE), eq(userReadDto));
    }

    @Test
    void updateShouldThrowExceptionWhenUserNotFound() {
        UserCreateDto createDto = getUserCreateDto();
        UserEntity user = getUser();
        UserUpdateDto updateDto = new UserUpdateDto(userId, user.getDtUpdate());

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.update(createDto, updateDto))
                .isInstanceOf(IdNotFoundException.class);
    }


    private UserReadDto getUserReadDto() {
        return UserReadDto.builder()
                .id(userId)
                .mail(MAIL)
                .fio("Fio")
                .status(EUserStatus.ACTIVATED)
                .role(EUserRole.USER)
                .build();
    }

    private UserEntity getUser() {
        return UserEntity.builder()
                .id(userId)
                .mail(MAIL)
                .fio("Fio")
                .status(EUserStatus.ACTIVATED)
                .password("pass")
                .role(EUserRole.USER)
                .build();
    }

    private UserSecure getUserSecure() {
        return UserSecure.builder()
                .id(userId)
                .mail(MAIL)
                .fio("Fio")
                .status(EUserStatus.ACTIVATED)
                .password("pass")
                .role(EUserRole.USER)
                .build();
    }

    private UserCreateDto getUserCreateDto() {
        return UserCreateDto.builder()
                .mail("test@test.com")
                .fio("Fio")
                .status(EUserStatus.ACTIVATED)
                .password("pass")
                .role(EUserRole.USER)
                .build();
    }
}