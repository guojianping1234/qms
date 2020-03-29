package com.myspringbt.demo.Task;

public class MigrateTask  extends DistributaskTask{
    @Override
    String getId() {
        return null;
    }

    @Override
    boolean stop() {
        return false;
    }

    @Override
    public void run() {

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

    }
}
