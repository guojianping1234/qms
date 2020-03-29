package com.myspringbt.demo.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Progress {
    //观察者
    protected PropertyChangeSupport pce = new PropertyChangeSupport(this);
    double pecenttage;
    boolean cancelable = true;
    boolean canceled;

    public void addPropertyChangeLister(PropertyChangeListener listener) {
        this.pce.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeLister(String propertyName, PropertyChangeListener listener) {
        this.pce.addPropertyChangeListener(propertyName, listener);
    }


    void setPecenttage(double percentage) {
        String oldVlaue = String.valueOf(this.pecenttage);
        this.pecenttage = percentage;
        pce.firePropertyChange("pecenttage", oldVlaue, percentage);
    }

    String getPercentText() {
        return pecenttage * 100 + "%";

    }


}
