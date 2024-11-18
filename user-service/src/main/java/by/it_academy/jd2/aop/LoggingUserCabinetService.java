package by.it_academy.jd2.aop;

import by.it_academy.jd2.service.dto.UserRegistrationDto;
import by.it_academy.jd2.service.dto.VerificationDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingUserCabinetService {

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isServiceLayer() && execution(public * registration(*))")
    public void isRegistrationMethod() {}

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isServiceLayer() && execution(public * verify(*))")
    public void isVerifyMethod() {}

    @Before(value = "isRegistrationMethod() " +
            "&& args(dto) " +
            "&& target(service)", argNames = "dto,service")
    public void addLogBeforeRegistrationMethod(UserRegistrationDto dto, Object service){
        log.info("Before - invoked registration() service method in class {} and RegistrationDto {}", service, dto);
    }

    @Before(value = "isVerifyMethod()" +
            "&& args(dto) " +
            "&& target(service)", argNames = "dto,service")
    public void addLogBeforeVerifyMethod(VerificationDto dto, Object service){
        log.info("Before - invoked verify() service method in class {} and VerificationDto {}", service, dto);
    }
}
