package by.it_academy.jd2.commonlib.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonPointcut {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    public void isServiceLayer() {}

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {}

    @Pointcut("isServiceLayer() && execution(public * findById(*))")
    public void anyFindByIdServiceMethod() {}

    @Pointcut("isServiceLayer() && execution(public * findAll(*))")
    public void anyFindAllServiceMethod() {}

    @Pointcut("isServiceLayer() && execution(public * save(*))")
    public void anySaveServiceMethod() {}
}
