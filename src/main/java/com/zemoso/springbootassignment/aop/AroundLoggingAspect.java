package com.zemoso.springbootassignment.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Aspect
@Component
@EnableAspectJAutoProxy
public class AroundLoggingAspect {

    private Logger myLogger = LoggerFactory.getLogger(getClass().getName());

    @Around("within(com.zemoso.springbootassignment..*)")
    public Object aroundLogging(ProceedingJoinPoint theProceedingJP) throws Throwable {

        String method = theProceedingJP.getSignature().toShortString();
        myLogger.info("===> Executing @Around on method: " + method);
        Object result = null;
        try {
            result = theProceedingJP.proceed();
        } catch (Exception exception) {
            // log the exception - warning
            myLogger.warn("\n###### EXCEPTION #########\n" + exception.getMessage());
            throw exception; // rethrowing exception
        }

        myLogger.info("===> @Around executed | Returned from method " + method);
        return result;
    }

}
