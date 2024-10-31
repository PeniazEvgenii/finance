package by.it_academy.jd2.service.run;

import by.it_academy.jd2.service.IUserService;
import by.it_academy.jd2.service.IVerificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SchedulerVerifyService {

    private final ScheduledExecutorService executorService;
    private final IUserService userService;
    private final IVerificationService verificationService;

    public SchedulerVerifyService(IUserService userService,
                                  IVerificationService verificationService,
                                  @Value("${scheduler.core.pool}") Integer corePool,
                                  @Value("${scheduler.init.delay}") Integer initDelay,
                                  @Value("${scheduler.period}") Integer period) {

        this.userService = userService;
        this.verificationService = verificationService;
        this.executorService = Executors.newScheduledThreadPool(corePool);
        executorService.scheduleAtFixedRate(new SendVerifyCodeJob(userService, verificationService), initDelay, period, TimeUnit.MINUTES);
    }

}
