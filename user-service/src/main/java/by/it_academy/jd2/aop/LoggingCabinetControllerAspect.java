package by.it_academy.jd2.aop;

import by.it_academy.jd2.service.dto.UserLoginDto;
import by.it_academy.jd2.service.dto.UserRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingCabinetControllerAspect {

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isControllerLayer() && execution(public * registration(*))")
    public void isRegistrationMethod() {}

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isControllerLayer() && execution(public * login(*))")
    public void isLoginMethod() {}

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isControllerLayer() && execution(public * verify(..))")
    public void isVerificationMethod() {}

    @Before(value = "isRegistrationMethod() && target(controller) && args(dto)",
            argNames = "controller,dto")
    public void addLogBeforeRegistration(Object controller, UserRegistrationDto dto) {
        log.info("Before - invoked registration() controller method in class {}, with user mail {}", controller, dto.getMail());
    }

    @Before(value = "isRegistrationMethod() && target(controller) && args(dto)",
            argNames = "controller,dto")
    public void addLogBeforeLogin(Object controller, UserLoginDto dto) {
        log.info("Before - invoked login() controller method in class {}, with user mail {}", controller, dto.getMail());
    }

}
