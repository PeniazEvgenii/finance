package by.it_academy.jd2.service;

import by.it_academy.jd2.exception.IdNotFoundException;
import by.it_academy.jd2.exception.UpdateTimeMismatchException;
import by.it_academy.jd2.page.PageOf;
import by.it_academy.jd2.repository.IUserRepository;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.*;
import by.it_academy.jd2.service.mapper.IUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Validated()
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public PageOf<UserReadDto> findAll(@Valid PageDto pageDto) {
        Sort.TypedSort<Instant> sortUsers = Sort.sort(UserReadDto.class).by(UserReadDto::getDtCreate);
        PageRequest pageRequest = PageRequest.of(pageDto.getPage(), pageDto.getSize(), sortUsers);

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
    public void create(@Valid UserCreateDto userCreateDto) {
         Optional.of(userCreateDto)
                .map(userMapper::mapCreate)
                .map(userRepository::save)
                .orElseThrow();                                  //можно свое исключение добавить и будем отлавливать что не создалось
    }

    @Override
    @Transactional
    public void update(@Valid UserUpdateDto userUpdateDto) {
        UserEntity userEntity = userRepository.findById(userUpdateDto.getId())
                .orElseThrow(IdNotFoundException::new);

        if(!userUpdateDto.getDtUpdate().equals(userEntity.getDtUpdate())) {
            throw new UpdateTimeMismatchException();
        }
        Optional.of(userEntity)
                .map(entity -> userMapper.mapEntityUpdate(userUpdateDto, entity))
                .map(userRepository::saveAndFlush)
                .orElseThrow();
    }

    public Optional<UserReadDto> findByMail(String mail) {
         return userRepository.findByMail(mail)
                .map(userMapper::mapRead);
    }
}
