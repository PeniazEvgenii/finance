package by.it_academy.jd2.auditservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAllAspect {

    @Pointcut("@within(by.it_academy.jd2.commonlib.aop.LoggingAspect) && execution(public * *(..))")
    public void isLoggingClassLayer() {
    }

    @Before("isLoggingClassLayer()")
    public void logMethodCall(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        String declaringTypeName = signature.getDeclaringTypeName();

        log.info("BEFORE - invoke method for class: '{}', name of method: '{}', arguments: {}", declaringTypeName, name, Arrays.toString(args));
    }

    @After("isLoggingClassLayer()")
    public void logAfterMethodCall(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        String declaringTypeName = signature.getDeclaringTypeName();

        if (log.isDebugEnabled()) {
            log.debug("AFTER - method completed for class: '{}', name of method: '{}'", declaringTypeName, name);
        }
    }

    @AfterReturning(pointcut = "isLoggingClassLayer() && !execution(* findAll*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();

        log.info("AFTER RETURNING - method completed for class: '{}', name of method: '{}', returned value: {}",              // на дебаг поменять
                declaringTypeName, name, result);
    }

    @AfterThrowing(pointcut = "isLoggingClassLayer()", throwing = "ex")
    public void logAfterThrowingMethodCall(JoinPoint joinPoint, Throwable ex) {
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();
        String declaringTypeName = signature.getDeclaringTypeName();

        log.error("AFTER THROWING - exception in class: '{}', method: '{}', exception: '{}'",
                declaringTypeName, name, ex.getMessage());
    }
}
