package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.aop.LoggingAspect;
import by.it_academy.jd2.commonlib.dto.PageDto;
import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.IUserRepository;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.*;
import by.it_academy.jd2.service.exception.MailNotUniqueException;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_USER_CREATE;
import static by.it_academy.jd2.commonlib.constant.Actions.AUDIT_USER_UPDATE;

@LoggingAspect
@Service
@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IAuditService auditService;
    private final IUserMapper userMapper;

    @Override
    public PageOf<UserReadDto> findAll(@Valid PageDto pageDto) {
        Sort sortUsers = Sort.sort(UserEntity.class)
                .by(UserEntity::getDtCreate)
                .descending();
        PageRequest pageRequest = PageRequest.of(
                pageDto.getPage(),
                pageDto.getSize(),
                sortUsers);
        Page<UserReadDto> page = userRepository.findAll(pageRequest)
                .map(userMapper::mapRead);
        return PageOf.of(page);
    }

    @Override
    public UserReadDto findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::mapRead)
                .orElseThrow(IdNotFoundException::new);
    }

    @Override
    @Transactional
    public void create(UserCreateDto createDto) {
        Optional.of(createDto)
                .map(userMapper::mapCreate)
                .map(userRepository::saveAndFlush)
                .map(userMapper::mapRead)
                .ifPresent(user -> auditService.send(AUDIT_USER_CREATE, user));
    }

    @Override
    @Transactional
    public void update(UserCreateDto createDto, UserUpdateDto updateDto) {
        UserEntity userEntity = userRepository
                .findById(updateDto.getId())
                .orElseThrow(IdNotFoundException::new);

        validateUpdate(createDto, updateDto, userEntity);

        Optional.of(userEntity)
                .map(entity -> userMapper.mapEntityUpdate(createDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userMapper::mapRead)
                .ifPresent(user -> auditService.send(AUDIT_USER_UPDATE, user));
    }

    @Override
    @Transactional
    public UserReadDto updateStatus(String mail, EUserStatus status) {
        return this.findEntityByMail(mail)
                .map(entity -> {
                    entity.setStatus(status);
                    return entity;
                })
                .map(userRepository::saveAndFlush)
                .map(userMapper::mapRead)
                .orElseThrow(IdNotFoundException::new);                              //TODO что-то может поменять,
    }

    @Override
    public Optional<UserReadDto> findByMail(String mail) {
        return this.findEntityByMail(mail)
                .map(userMapper::mapRead);
    }

    @Override
    public Optional<UserSecure> findByMailWithPass(String mail) {
        return this.findEntityByMail(mail)
                .map(userMapper::mapSecure);
    }

    @Override
    public List<UserEntity> findByStatusWithoutCode(EUserStatus status) {
        return userRepository.findByStatusWithoutCode(status);
    }

    @Override
    public UserReadDto getReadDto(UserSecure userSecure) {
        return userMapper.mapReadFromSecure(userSecure);
    }

    private Optional<UserEntity> findEntityByMail(String mail) {
        return userRepository.findByMailIgnoreCase(mail);
    }


    private void validateUpdate(UserCreateDto createDto,
                                UserUpdateDto updateDto,
                                UserEntity userEntity) {

        this.findByMail(createDto.getMail())
                .map(UserReadDto::getId)
                .filter(id -> !id.equals(userEntity.getId()))
                .ifPresent(id -> {
                    throw new MailNotUniqueException();
                });

        if (!updateDto.getDtUpdate().equals(userEntity.getDtUpdate())) {
            throw new UpdateTimeMismatchException();
        }
    }
}
