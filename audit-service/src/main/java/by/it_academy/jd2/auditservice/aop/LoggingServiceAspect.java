package by.it_academy.jd2.auditservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingServiceAspect {

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyCreateServiceMethod() " +
            "&& args(dto) " +
            "&& target(service)", argNames = "dto, service")
    public void addLogBeforeSaveServiceMethod(Object dto, Object service) {
        log.info("Before - invoked save Service Method in class {}, with dto {}", service, dto);
    }

    @AfterThrowing(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyCreateServiceMethod() && target(service)",
                  throwing = "ex")
    public void addLogAfterThrowingSaveServiceMethod(Throwable ex, Object service) {
        log.error("After - invoked save Service Method in class {}, exception {}: {} ", service, ex.getClass(), ex.getMessage());
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdServiceMethod() " +
            "&& args(id) " +
            "&& target(service)", argNames = "id,service")
    public void addLogBeforeFindByIdServiceMethod(Object id, Object service) {
        log.info("Before - invoked findById Service Method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdServiceMethod() && target(service)",
            returning = "result",
            argNames = "result,service")
    public void addLogAfterFindByIdServiceMethod(Object result, Object service) {
        log.info("After - invoked findById Service Method in class {}, result {}", service, result);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindAllServiceMethod() && target(service)", argNames = "service")
    public void addLogBeforeFindAllServiceMethod(Object service) {
        log.info("invoked findAll Service Method in class {}", service);
    }
}
