package com.myspringbt.demo.Task;

import com.myspringbt.demo.model.Progress;
import groovy.util.ObservableList;

import java.beans.PropertyChangeSupport;

public abstract class DistributaskTask implements Runnable, Thread.UncaughtExceptionHandler {
    ObservableList errorMessageList = new ObservableList();
    protected PropertyChangeSupport pcs;

    public static final int CRATED = 0;
    public static final int PAUSE = 1;
    public static final int RUNNING = 2;
    public static final int CANCELED = 3;
    public static final int WAITING = 4;
    public static final int FINISHED = 5;

    private int status;
    private Object attach;

    final Progress progress = new Progress();

    public void beforeExccetue() {

    }

    public void afterExcetue() {

    }

    abstract String getId();

    abstract boolean stop();


}
