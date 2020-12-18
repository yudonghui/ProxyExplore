package com.ydh.proxyexplore.observermodel;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by ydh on 2020/12/18
 */
public class MyObserver implements Observer {
    private int id;
    private MyPerson myPerson;

    public MyObserver(int id) {
        System.out.println("我是观察者---->" + id);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MyPerson getMyPerson() {
        return myPerson;
    }

    public void setMyPerson(MyPerson myPerson) {
        this.myPerson = myPerson;
    }

    @Override
    public void update(Observable observable, Object data) {
        System.out.println("观察者---->" + id + "得到更新");
        this.myPerson = (MyPerson) observable;
        System.out.println(((MyPerson) observable).toString());
    }
}
