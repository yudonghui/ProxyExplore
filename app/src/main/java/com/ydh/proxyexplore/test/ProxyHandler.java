package com.ydh.proxyexplore.test;

import android.app.Activity;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by ydh on 2020/12/18
 */
public class ProxyHandler implements InvocationHandler {

    public ProxyHandler(Activity activity) {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      /*  Object handler = mHandlerRef.get();
        if (null == handler) return null;
        String name = method.getName();
        //将onClick方法的调用映射到activity 中的invokeClick()方法
        Method realMethod = mMethodHashMap.get(name);
        if (null != realMethod){
            return realMethod.invoke(handler, args);
        }*/
        return null;
    }
}
