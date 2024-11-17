package by.it_academy.jd2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingServiceAspect {

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isServiceLayer() && execution(public * findEntityByIdAndUserId(*))")
    public void findEntityByIdAndUserIdMethod() {}

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isServiceLayer() && execution(public * findByIdAndAccountId(..))")
    public void findByIdAndAccountIdMethod() {}

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isServiceLayer() && execution(public * create(*, *))")
    public void createOperationServiceMethod() {}

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isServiceLayer() && execution(public * delete(*))")
    public void deleteServiceMethod() {}


    //advices
    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindAllServiceMethod() && target(service)", argNames = "service")
    public void addLogBeforeFindAllMethod(Object service) {
        log.info("Before - invoked findAll() service method in class {}", service);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdServiceMethod() " +
            "&& target(service) " +
            "&& args(id)", argNames = "service,id")
    public void addLogBeforeFindByIdMethod(Object service, Object id) {
        log.info("Before - invoked findById() service method in class {}, with id {}", service, id);
    }

    @AfterReturning(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdServiceMethod() && target(service)",
            returning = "result",
            argNames = "result,service")
    public void addLogAfterFindByIdMethod(Object result, Object service) {
        log.info("After - invoked findById() service method in class {}, result {}", service, result);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyUpdateServiceMethod() " +
            "&& target(service) " +
            "&& args(createDto, updateDto)", argNames = "service, createDto, updateDto")
    public void addLogBeforeUpdateMethod(Object service,Object createDto, Object updateDto){
        log.info("Before - invoke update() service method in class {}, with createDto {} and updateDto {}", service, createDto, updateDto);
    }

    @AfterThrowing(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyUpdateServiceMethod() && target(service)",
            throwing = "ex")
    public void addLogAfterThrowingUpdateMethod(Throwable ex, Object service) {
        log.error("After - invoked update() Service Method in class {}, exception {}: {} ", service, ex.getClass(), ex.getMessage());
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyCreateServiceMethod() " +
            "&& args(createDto) " +
            "&& target(service)", argNames = "createDto,service")
    public void addLogBeforeCreateServiceMethod(Object createDto, Object service) {
        log.info("Before - invoked create() Service Method in class {}, with dto {}", service, createDto);
    }

    @AfterThrowing(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyCreateServiceMethod() && target(service)",
            throwing = "ex")
    public void addLogAfterThrowingCreateMethod(Throwable ex, Object service) {
        log.error("After - invoked create() Service Method in class {}, exception {}: {} ", service, ex.getClass(), ex.getMessage());
    }

    @Before(value = "createOperationServiceMethod() " +
            "&& args(createDto, accountId) " +
            "&& target(service)", argNames = "createDto,accountId,service")
    public void addLogBeforeCreateOperationMethod(Object createDto, Object accountId, Object service) {
        log.info("Before - invoked create() ServiceOperation Method in class {}, with dto {} and account_id {}", service, createDto, accountId);
    }

    @AfterThrowing(value = "createOperationServiceMethod() " +
            "&& target(service)", throwing = "ex")
    public void addLogAfterThrowingCreateOperationMethod(Throwable ex, Object service) {
        log.error("After - invoked create() ServiceOperation Method in class {}, exception {}: {} ", service, ex.getClass(), ex.getMessage());
    }

    @Before(value = "deleteServiceMethod() && args(deleteDto) " +
            "&& target(service)", argNames = "deleteDto,service")
    public void AddLogBeforeDeleteMethod(Object deleteDto, Object service) {
        log.info("Before - invoked delete() ServiceOperation Method in class {}, with dto {}", service, deleteDto);
    }

}
