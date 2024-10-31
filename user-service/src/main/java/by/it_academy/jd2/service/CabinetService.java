package by.it_academy.jd2.service;

import by.it_academy.jd2.repository.IUserRepository;
import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.UserRegistrationDto;
import by.it_academy.jd2.service.dto.UserStatus;
import by.it_academy.jd2.service.dto.VerificationDto;
import by.it_academy.jd2.service.exception.IdNotFoundException;
import by.it_academy.jd2.service.exception.VerificationException;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
         if(verificationService.checkCode(verificationDto)) {                             //могу и ENTITY передать, -1 запрос НО передаю с
             UserEntity userEntity = userRepository.findByMail(verificationDto.getMail())
                     .orElseThrow(IdNotFoundException::new);
             userEntity.setStatus(UserStatus.ACTIVATED);
             Optional.of(userEntity)
                     .map(userRepository::saveAndFlush)
                     .orElseThrow();
         } else {
             throw new VerificationException();
         }
    }
}
