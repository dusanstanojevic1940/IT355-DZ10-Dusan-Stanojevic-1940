package com.it355.logers;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class ContactLogger {
    @Pointcut("execution(* com.it355.dao.ContactDao.*(..))")
    public void daoAll() {}

    @Pointcut("execution(* com.it355.models.Contact.*(..))")
    public void modelAll() {}

    @Before("daoAll()")
    public void beforeDaoAll() {
        System.out.println("Accessing contact DAO");
    }

    @Before("modelAll()")
    public void beforeModelAll() {
        System.out.println("Accessing contact Model");
    }
}
