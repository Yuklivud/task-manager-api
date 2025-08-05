package com.ms.todoapi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.ms.todoapi.controller..*(..))")
    public void controllerMethods(){}

    @Pointcut("execution(* com.ms.todoapi.service..*(..))")
    public void servicesMethods(){}

    @Around("controllerMethods() || servicesMethods()")
    public Object loggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        logger.info("Entering: {} with args = {}", methodName, args);

        try{
            Object result = joinPoint.proceed(args);
            logger.info("Exiting: {} with result: {}", methodName, result);
            return result;
        } catch (Throwable ex) {
            logger.error("Exception in {}: {}", methodName, ex.getMessage(), ex);
            throw ex;
        }
    }
}
