package com.bj.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by neko on 2018/3/5.
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.bj.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

        System.out.println("time aspect start");

        //因为Interceptor获取不到方法的参数，所以需要使用Aspect
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }

        long start = new Date().getTime();

        //过滤器链中调用被拦截的方法
        Object proceed = pjp.proceed();

        System.out.println("time aspect : " + (new Date().getTime() - start));

        System.out.println("time aspect end");

        return null;
    }

}
