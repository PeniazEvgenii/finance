package by.it_academy.jd2.commonlib.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class  CommonPointcut {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceLayer() {}

    @Pointcut("isServiceLayer() && execution(public * findById(*))")
    public void anyFindByIdServiceMethod() {}

    @Pointcut("isServiceLayer() && execution(public * findAll(..))")
    public void anyFindAllServiceMethod() {}

    @Pointcut("isServiceLayer() && execution(public * create(..))")
    public void anyCreateServiceMethod() {}

    @Pointcut("isServiceLayer() && execution(public * update(..))")
    public void anyUpdateServiceMethod() {}

    @Pointcut("isServiceLayer() && execution(public * findByMail(*))")
    public void isFindByMailServiceMethod(){};



    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void isControllerLayer() {}

    @Pointcut("isControllerLayer() && execution(public * findById(*))")
    public void anyFindByIdControllerLayer(){}

    @Pointcut("isControllerLayer() && execution(public * findAll(..))")
    public void anyFindAllControllerLayer() {}

    @Pointcut("isControllerLayer() && execution(public * create(..))")
    public void anyCreateControllerLayer() {}

    @Pointcut("isControllerLayer() && execution(public * update(..))")
    public void anyUpdateControllerLayer() {}

}
