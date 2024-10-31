package by.it_academy.jd2.service.run;

import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.IUserService;
import by.it_academy.jd2.service.IVerificationService;
import by.it_academy.jd2.service.dto.UserStatus;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SendVerifyCodeJob implements Runnable{
    private final IUserService userService;
    private final IVerificationService verificationService;

    @Override
    public void run() {
        List<UserEntity> users = userService.findByStatusWithoutCode(UserStatus.WAITING_ACTIVATION);

        for (UserEntity user : users) {
            verificationService.sendCode(user);
        }
    }
}
