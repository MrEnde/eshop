package com.shop.eshop.aspect;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ServiceProfilingAspect {
    @SneakyThrows
    @Around("execution(public * com.shop.eshop.services.*.*(..))")
    public Object serviceProfiling(ProceedingJoinPoint proceedingJoinPoint) {
        var serviceName = proceedingJoinPoint.getSignature().getName();
        var timer = new StopWatch();
        Object result;
        try {
            timer.start();
            result = proceedingJoinPoint.proceed();
        } finally {
            timer.stop();
        }
        log.info("Service {} completed in {} milliseconds", serviceName, timer.getTotalTimeMillis());
        return result;
    }
}
