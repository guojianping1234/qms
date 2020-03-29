package com.myspringbt.demo.ObservableService;

public class TestDemo {

    public static void main(String[] args) {
        ObserverVableDemo sm = new ObserverVableDemo();
        BObserver b = new BObserver(sm);
        ObserverDemo a = new ObserverDemo(sm);
        sm.setData(5);
        sm.deleteObserver(a);//注销观察者，以后被观察者有数据变化就不再通知这个已注销的观察者
        sm.setData(10);
    }


}
