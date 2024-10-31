package by.it_academy.jd2.service;

import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.dto.VerificationDto;

public interface IVerificationService {

    void sendCode(UserEntity user);

    boolean checkCode(VerificationDto verificationDto);
}
