package com.example.zti_books.aspects;

import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Log
@Component
@Aspect
public class Aspects {
    @After("execution(public * com.example.zti_books.login..*.*(..)) || execution(public * com.example.zti_books.registration..*.*(..)) || execution(public * com.example.zti_books.user..*.*(..)) || execution(public * com.example.zti_books.book..*.*(..)) || execution(public * com.example.zti_books.exceptions..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("[LOGGER]: " + joinPoint.getTarget().getClass().getSimpleName()
                + " run public method " + joinPoint.getSignature().getName()
        );
    }
}