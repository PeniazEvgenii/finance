package by.it_academy.jd2.service;

import by.it_academy.jd2.repository.entity.EUserStatus;
import by.it_academy.jd2.service.api.ICabinetService;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.api.IVerificationService;
import by.it_academy.jd2.service.dto.UserCreateDto;
import by.it_academy.jd2.service.dto.UserReadDto;
import by.it_academy.jd2.service.dto.UserRegistrationDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import by.it_academy.jd2.service.feign.api.IAuditService;
import by.it_academy.jd2.service.mapper.api.IUserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import static by.it_academy.jd2.service.feign.Actions.USER_VERIFY;

@Validated
@Service
@RequiredArgsConstructor
public class CabinetService implements ICabinetService {

    private final IVerificationService verificationService;
    private final IAuditService auditService;
    private final IUserService userService;
    private final IUserMapper userMapper;

    public void registration(@Valid UserRegistrationDto userRegistrationDto) {
        UserCreateDto userCreateDto = userMapper.mapCreateDto(userRegistrationDto);
        userService.create(userCreateDto);
    }

    public void verify(@Valid VerificationDto verificationDto) {
        verificationService.checkCode(verificationDto);
        UserReadDto user = userService.updateStatus(verificationDto.getMail(),
                                                    EUserStatus.ACTIVATED);

        auditService.send(USER_VERIFY, user);
    }
}
