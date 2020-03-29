package com.myspringbt.demo.ObservableService;

import java.util.Observable;
import java.util.Observer;

public class BObserver implements Observer {

    public BObserver(ObserverVableDemo sm) {
        super();
        sm.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        System.out.println("BObserver receive:Data has changed to " + ((ObserverVableDemo) o).getData());

    }
}
