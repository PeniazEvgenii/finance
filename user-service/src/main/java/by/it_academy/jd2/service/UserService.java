package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.commonlib.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.commonlib.page.PageOf;
import by.it_academy.jd2.repository.IUserRepository;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.*;
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

@Validated
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public PageOf<UserReadDto> findAll(@Valid PageDto pageDto) {
        Sort sortUsers = Sort.sort(UserReadDto.class)
                .by(UserReadDto::getDtCreate)
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
    public Optional<UserReadDto> findById(UUID id) {
        return userRepository.findById(id)
                .map(userMapper::mapRead);
    }

    @Override
    @Transactional
    public void create(UserCreateDto userCreateDto) {
        Optional.of(userCreateDto)
                .map(userMapper::mapCreate)
                .map(userRepository::save)
                .orElseThrow();                                  //можно свое исключение добавить и будем отлавливать что не создалось
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
                .orElseThrow();
    }


    @Override
    public Optional<UserReadDto> findByMail(String mail) {
        return userRepository.findByMailIgnoreCase(mail)
                .map(userMapper::mapRead);
    }

    @Override
    public List<UserEntity> findByStatusWithoutCode(EUserStatus status) {
        return userRepository.findByStatusWithoutCode(status);
    }

    @Override
    public Optional<UserSecure> findByMailWithPass(String mail) {
        return userRepository.findByMailIgnoreCase(mail)
                .map(userMapper::mapSecure);
    }

    private void validateUpdate(UserCreateDto createDto,
                                UserUpdateDto updateDto,
                                UserEntity userEntity) {

        this.findByMail(createDto.getMail())
                .map(UserReadDto::getUuid)
                .filter(uuid -> uuid.equals(userEntity.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с таким email уже существует"));

        if (!updateDto.getDtUpdate().equals(userEntity.getDtUpdate())) {
            throw new UpdateTimeMismatchException();
        }
    }
}
