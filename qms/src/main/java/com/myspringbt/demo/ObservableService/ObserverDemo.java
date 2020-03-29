package com.myspringbt.demo.ObservableService;


import java.util.Observable;
import java.util.Observer;

//观察者
public class ObserverDemo implements Observer {

    public ObserverDemo(ObserverVableDemo sm) {
        super();
        // TODO Auto-generated constructor stub
        sm.addObserver(this);
        //注册加入观察者
    }


    @Override
    public void update(Observable o, Object arg) {
        System.out.println("AObserver receive:Data has changed to " + ((ObserverVableDemo) o).getData());
    }
}
