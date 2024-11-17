package by.it_academy.jd2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class LoggingUserControllerAspect {

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdControllerLayer() " +
            "&& args(dto) " +
            "&& target(controller)", argNames = "dto,controller")
    public void addLogBeforeFindById(Object dto, Object controller) {
        log.info("Before - invoked findById() controller method in class {}, with dto {}", controller, dto);
    }

    @AfterReturning(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindByIdControllerLayer() " +
            "&& target(controller)",
            returning = "result",argNames = "result,controller")
    public void addLogAfterFindById(Object result, Object controller) {
        log.info("AfterReturning - invoked findById() controller method in class {}, with result {}", controller, result);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyFindAllControllerLayer() && target(controller)")
    public void addLogBeforeFindAll(Object controller) {
        log.info("Before - invoked findAll() controller method in class {}", controller);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyCreateControllerLayer() " +
            "&& args(dto) " +
            "&& target(controller)", argNames = "dto,controller")
    public void addLogBeforeSave(Object dto, Object controller) {
        log.info("Before - invoked create() controller method in class {}, with dto {}", controller, dto);
    }

    @Before(value = "by.it_academy.jd2.commonlib.aop.CommonPointcut.anyUpdateControllerLayer() " +
            "&& args(dto) " +
            "&& target(controller)", argNames = "dto,controller")
    public void addLogBeforeUpdate(Object dto, Object controller) {
        log.info("Before - invoked update() controller method in class {}, with dto {}", controller, dto);
    }


}
