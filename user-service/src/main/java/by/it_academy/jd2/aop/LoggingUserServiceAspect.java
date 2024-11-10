package by.it_academy.jd2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingUserServiceAspect {

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isServiceLayer() " +
            "&& execution(public * findByStatusWithoutCode(*))")
    public void isFindByStatusWithoutCodeMethod(){}


    @Before(value = "isFindByStatusWithoutCodeMethod() && target(service) && args(status)",
            argNames = "status, service")
    public void addLogBeforeFindByStatusWithoutCodeMethod(Object status, Object service){
        log.info("Before - invoked findByStatusWithoutCode() service method in class {}, with status {}", service, status);
    }

    @AfterReturning(value = "isFindByStatusWithoutCodeMethod() && target(service)",
                     returning = "result", argNames = "result,service")
    public void addLogAfterFindByStatusWithoutCodeMethod(Object result, Object service){
        log.info("AfterReturning - findByStatusWithoutCode() service method in class {},  result {}", service, result);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyUpdateServiceMethod() " +
            "&& target(service) " +
            "&& args(dto)", argNames = "service, dto")
    public void addLogBeforeUpdateServiceMethod(Object service,Object dto){
        log.info("After - invoke update() service method in class {}, with dto {}", service, dto);
    }

    @AfterThrowing(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyUpdateServiceMethod() " +
            "&& target(service)",
            throwing = "ex")
    public void addLogAfterThrowingUpdateServiceMethod(Throwable ex, Object service) {
        log.error("AfterThrowing - invoked update() service method in class {}, exception {}: {} ", service, ex.getClass(), ex.getMessage());
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anySaveServiceMethod() " +
            "&& args(dto) " +
            "&& target(service)", argNames = "dto, service")
    public void addLogBeforeSaveServiceMethod(Object dto, Object service) {
        log.info("Before - invoked save() service method in class {}, with dto {}", service, dto);
    }

    @AfterThrowing(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anySaveServiceMethod() " +
            "&& target(service)",
            throwing = "ex")
    public void addLogAfterThrowingSaveServiceMethod(Throwable ex, Object service) {
        log.error("AfterThrowing - invoked save() service method in class {}, exception {}: {} ", service, ex.getClass(), ex.getMessage());
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdServiceMethod() " +
            "&& args(id) " +
            "&& target(service)", argNames = "id,service")
    public void addLogBeforeFindByIdServiceMethod(Object id, Object service) {
        log.info("Before - invoked findById() service method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdServiceMethod() && target(service)",
            returning = "result",
            argNames = "result,service")
    public void addLogAfterFindByIdServiceMethod(Object result, Object service) {
        log.info("After - invoked findById() service method in class {}, result {}", service, result);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindAllServiceMethod() && target(service)", argNames = "service")
    public void addLogBeforeFindAllServiceMethod(Object service) {
        log.info("invoked findAll() service method in class {}", service);
    }

}
