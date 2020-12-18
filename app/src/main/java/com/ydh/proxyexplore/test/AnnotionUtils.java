package com.ydh.proxyexplore.test;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.ydh.proxyexplore.R;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ydh on 2020/12/18
 */
public class AnnotionUtils {

    public static void injectView(final Activity activity) {
        if (null == activity) return;

        Class<? extends Activity> activityClass = activity.getClass();
        Field[] declaredFields = activityClass.getDeclaredFields();
        for (Field field : declaredFields) {
            //设置为可访问，暴力反射，就算是私有的也能访问到
            field.setAccessible(true);
            if (field.isAnnotationPresent(InjectView.class)) {
                //解析InjectView 获取button id
                InjectView annotation = field.getAnnotation(InjectView.class);
                int value = annotation.value();
                View viewById = activity.findViewById(value);
                try {
                    field.set(activity, viewById);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //获取所有的方法（私有方法也可以获取到）
        Method[] declaredMethods = activityClass.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            final Method method = declaredMethods[i];
            //获取方法上面的注解
            onClick annotation = method.getAnnotation(onClick.class);
            if (annotation == null) {
                //如果该方法上没有注解，循环下一个
                continue;
            }
            //获取注解中的数据，因为可以给多个button绑定点击事件，因此定义注解类时使用的是int[]作为数据类型。
            int[] value = annotation.value();
            for (int j = 0; j < value.length; j++) {
                int id = value[j];

                final View button = activity.findViewById(id);

                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        try {
                            //反射调用用户指定的方法
                            //第一个参数是方法属于的对象（如果是静态方法，则可以直接传 null）
                            //第二个可变参数是该方法的参数
                            method.invoke(activity, button);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        }
    }


}
