package com.ydh.proxyexplore.observermodel;

/**
 * Created by ydh on 2020/12/18
 * 观察者接口
 */
public interface IObserver<T> {
    void onSucess(T t);

    void onFail(T t);
}
