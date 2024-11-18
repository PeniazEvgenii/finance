package by.it_academy.jd2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingControllerAspect {

    @Pointcut("by.it_academy.jd2.commonlib.aop.CommonPointcut.isControllerLayer() && execution(public * update(..))")
    public void isDeleteControllerMethod() {}


    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdControllerLayer() " +
            "&& args(id) " +
            "&& target(controller)", argNames = "id,controller")
    public void addLogBeforeFindByIdMethod(Object id, Object controller) {
        log.info("Before - invoked findById() Controller method in class {}, with id {}", controller, id);
    }

    @AfterReturning(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdControllerLayer() " +
            "&& target(controller)",
            returning = "result",argNames = "result,controller")
    public void addLogAfterFindByIdMethod(Object result, Object controller) {
        log.info("AfterReturning - invoked findById() Controller method in class {}, with result {}", controller, result);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindAllControllerLayer() && target(controller)")
    public void addLogBeforeFindAll(Object controller) {
        log.info("Before - invoked findAll() Controller method in class {}", controller);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyCreateControllerLayer() " +
            "&& args(dto) " +
            "&& target(controller)", argNames = "dto,controller")
    public void addLogBeforeCreateMethod(Object dto, Object controller) {
        log.info("Before - invoked create() Controller method in class {}, with dto {}", controller, dto);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyCreateControllerLayer() " +
            "&& args(operationDto,accountId) " +
            "&& target(controller)", argNames = "operationDto,controller,accountId")
    public void addLogBeforeCreateMethod(Object operationDto, Object controller, Object accountId) {
        log.info("Before - invoked create() Controller method in class {}, with dto {} and accountId {}", controller, operationDto, accountId);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyUpdateControllerLayer() " +
            "&& target(controller)", argNames = "controller")
    public void addLogBeforeUpdateMethod(Object controller) {
        log.info("Before - invoked update() Controller method in class {}", controller);
    }

    @Before(value = "isDeleteControllerMethod() " +
            "&& args(accountId, operationId, dtUpdate) " +
            "&& target(controller)", argNames = "accountId,operationId,dtUpdate,controller")
    public void addLogBeforeDeleteMethod(Object accountId, Object operationId,
                                         Object dtUpdate, Object controller) {
        log.info("Before - invoked delete() Controller method in class {}, with accountId {}, operationId {} and dtUpdate {}",
                controller, accountId, operationId, dtUpdate);
    }
}
