package by.it_academy.jd2.service.api;

import by.it_academy.jd2.service.dto.UserRegistrationDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import jakarta.validation.Valid;

public interface ICabinetService {
    void registration(@Valid UserRegistrationDto userRegistrationDto);

    void verify(@Valid VerificationDto verificationDto);
}
