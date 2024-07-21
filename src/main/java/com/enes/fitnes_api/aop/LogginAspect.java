package com.enes.fitnes_api.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogginAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogginAspect.class);

    @Before("execution(* com.enes.fitnes_api.services.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        System.out.println("----------------------------------- Logging Aspect -----------------------------------");
        LOGGER.info("Method called: {}", joinPoint.getSignature().getName());
        LOGGER.info("Method arguments: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.enes.fitnes_api.services.*.*(..))", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        LOGGER.info("Method returned: {}", result);
    }

    @AfterThrowing(pointcut = "execution(* com.enes.fitnes_api.services.*.*(..))", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Exception exception) {
        LOGGER.error("Method threw exception: {}", exception.getMessage());
    }

    @After("execution(* com.enes.fitnes_api.services.*.*(..))")
    public void logMethodExecute(JoinPoint joinPoint) {
        LOGGER.info("Method executed: {}", joinPoint.getSignature().getName());
    }

}
