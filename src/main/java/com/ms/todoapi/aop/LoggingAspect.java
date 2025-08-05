package com.ms.todoapi.aop;

import com.ms.todoapi.util.annotation.Protected;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Field;
import java.util.Collection;


@Aspect
@Component
public class LoggingAspect {
    private final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.ms.todoapi.controller..*(..))")
    public void controllerMethods(){}

    @Around("controllerMethods()")
    public Object loggingAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String httpMethod = request != null ? request.getMethod() : "N/A";
        String requestURI = request != null ? request.getRequestURI() : "N/A";


        for(Object arg : args){
            if(arg != null){
                maskField(arg);
            }
        }

        logger.info("HTTP {} {} - Entering: {} with args = {}", httpMethod, requestURI, methodName, args);

        Long startTime = System.currentTimeMillis();
        try{
            Object result = joinPoint.proceed(args);
            Long endTime = System.currentTimeMillis() - startTime;

            logger.info("HTTP {} {} - Exiting: {} with result: {}, duration: {} ms", httpMethod, requestURI, methodName, summarizeResult(result), endTime);
            return result;
        } catch (Throwable ex) {
            logger.error("HTTP {} {} - Exception in {}: {}", httpMethod, requestURI, methodName, ex.getMessage(), ex);
            throw ex;
        }
    }

    private void maskField(Object obj){
        for(Field field : obj.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Protected.class)) {
                field.setAccessible(true);
                try {
                    field.set(obj, "[PROTECTED]");
                } catch (IllegalAccessException ignored) {}
            }
        }
    }

    private Object summarizeResult(Object result){
        if(result == null) return null;
        if (result instanceof Collection) {
            return "Collection of size " + ((Collection<?>)result).size();
        }
        if (result instanceof String && ((String) result).length() > 100) {
            return ((String) result).substring(0, 100) + "...";
        }
        return result;
    }
}
