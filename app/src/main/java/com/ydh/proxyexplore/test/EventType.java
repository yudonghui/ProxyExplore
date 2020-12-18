package com.ydh.proxyexplore.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ydh on 2020/12/18
 */
@Target(ElementType.ANNOTATION_TYPE)//注解的注解
@Retention(RetentionPolicy.RUNTIME)
public @interface EventType {
    Class listenerType();
    String listenerSetter();
    String methodName();
}
