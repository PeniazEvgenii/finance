package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.exception.SaveException;
import by.it_academy.jd2.repository.IUserRepository;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.api.ICabinetService;
import by.it_academy.jd2.service.api.IVerificationService;
import by.it_academy.jd2.service.dto.UserRegistrationDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@Service
@RequiredArgsConstructor
@Transactional
public class CabinetService implements ICabinetService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final IVerificationService verificationService;

    public void registration(@Valid UserRegistrationDto userRegistrationDto) {

        UserEntity userEntity = Optional.of(userRegistrationDto)       //посмотрю может не возвращать либо в аудит через аоп
                .map(userMapper::mapRegistration)
                .map(userRepository::saveAndFlush)
                .orElseThrow(SaveException::new);

    }

    public void verify(@Valid VerificationDto verificationDto) {
        verificationService.checkCode(verificationDto);

        userRepository.findByMailIgnoreCase(verificationDto.getMail())
                .map(entity -> {
                    entity.setStatus(EUserStatus.ACTIVATED);
                    return entity;
                })
                .map(userRepository::saveAndFlush);
    }
}
