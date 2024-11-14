package by.it_academy.jd2.service;

import by.it_academy.jd2.commonlib.exception.IdNotFoundException;
import by.it_academy.jd2.repository.IUserRepository;
import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.api.ICabinetService;
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
@Transactional(readOnly = true)
public class CabinetService implements ICabinetService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;
    private final VerificationService verificationService;

    @Transactional
    public void registration(@Valid UserRegistrationDto userRegistrationDto) {
        UserEntity userEntity = Optional.of(userRegistrationDto)
                .map(userMapper::mapRegistration)
                .map(userRepository::saveAndFlush)
                .orElseThrow();

        // verificationService.sendCode(userEntity);               //если без шедуллера
    }

    @Transactional
    public void verify(@Valid VerificationDto verificationDto) {
        verificationService.checkCode(verificationDto);                       //могу и ENTITY передать, -1 запрос НО передаю с

        UserEntity userEntity = userRepository
                .findByMailIgnoreCase(verificationDto.getMail())
                .orElseThrow(IdNotFoundException::new);
        userEntity.setStatus(EUserStatus.ACTIVATED);
        Optional.of(userEntity)
                .map(userRepository::saveAndFlush)                                  //будем аудит отправлять
                .orElseThrow();

    }
}
