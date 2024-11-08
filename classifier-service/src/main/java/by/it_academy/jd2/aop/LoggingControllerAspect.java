package by.it_academy.jd2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingControllerAspect {

    @Pointcut("execution(public * by.it_academy.jd2.controller.ClassifierController.createCurrency(*))")
    public void isCreateCurrencyControllerMethod(){}

    @Pointcut("execution(public * by.it_academy.jd2.controller.ClassifierController.findAllCurrency(..))")
    public void isFindAllCurrencyControllerMethod(){}

    @Pointcut("execution(public * by.it_academy.jd2.controller.ClassifierController.createOperationCategory(*))")
    public void isCreateOperationCategoryControllerMethod(){}

    @Pointcut("execution(public * by.it_academy.jd2.controller.ClassifierController.findAllOperationCategory(..))")
    public void isFindAllOperationCategoryControllerMethod(){}


    @Before(value = "isCreateCurrencyControllerMethod() && args(dto) && target(controller)",
            argNames = "dto,controller")
    public void addLogBeforeCreateCurrencyMethod(Object dto, Object controller) {
        log.info("Before - invoked createCurrency() ControllerMethod in class {}, with parameter: {}", controller, dto);
    }

    @Before(value = "isFindAllCurrencyControllerMethod() && target(controller)",
            argNames = "controller")
    public void addLogBeforeCreateCurrencyMethod(Object controller) {
        log.info("Before - invoked FindAllCurrency() ControllerMethod in class {}", controller);
    }


    @Before(value = "isCreateOperationCategoryControllerMethod() && args(dto) && target(controller)",
            argNames = "dto,controller")
    public void addLogBeforeCreateOperationCategoryMethod(Object dto, Object controller) {
        log.info("Before - invoked createOperationCategory() ControllerMethod in class {}, with parameter: {}", controller, dto);
    }

    @Before(value = "isFindAllOperationCategoryControllerMethod() && target(controller)",
            argNames = "controller")
    public void addLogBeforeFindAllOperationCategoryMethod(Object controller) {
        log.info("Before - invoked OperationCategory() ControllerMethod in class {}", controller);
    }

}
