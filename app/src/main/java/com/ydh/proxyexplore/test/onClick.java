package com.ydh.proxyexplore.test;

import android.view.View;

import androidx.lifecycle.OnLifecycleEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ydh on 2020/12/18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface onClick {
    int[] value();
}
