package by.it_academy.jd2.service.run;

import by.it_academy.jd2.repository.entity.UserEntity;
import by.it_academy.jd2.service.api.IUserService;
import by.it_academy.jd2.service.api.IVerificationService;
import by.it_academy.jd2.service.dto.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class SendVerifyCodeJob{

    private final IUserService userService;
    private final IVerificationService verificationService;

    @Scheduled(fixedRateString = "${scheduler.period}", timeUnit = TimeUnit.MINUTES)
    public void run() {
        List<UserEntity> users = userService.findByStatusWithoutCode(UserStatus.WAITING_ACTIVATION);

        for (UserEntity user : users) {
            verificationService.sendCode(user);
        }
    }
}
