package com.ms.todoapi.aop;

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

}
