package com.MessageQueue.Framework.Utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAOP {

    @Around("@annotation(org.springframework.kafka.annotation.KafkaListener)")
    public void AroundMethod(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Before point cut............");
        joinPoint.proceed();
        System.out.println("After point cut.............");
    }

}
