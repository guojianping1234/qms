package com.myspringbt.demo.service;

import com.myspringbt.demo.Task.DistributaskTask;
import groovy.util.ObservableList;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskMgs extends ThreadPoolExecutor {

    //运行时任务列表
    final List<DistributaskTask> taskList = Collections.synchronizedList(new ObservableList());


    //无参构造
    public TaskMgs() {

        this(80);

    }

    public TaskMgs(int maxmumPoolSize) {
        super(2, maxmumPoolSize, 1, TimeUnit.SECONDS, new SynchronousQueue<>());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        DistributaskTask task = (DistributaskTask) r;
        taskList.remove(task);
        task.afterExcetue();

    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        DistributaskTask task = (DistributaskTask) r;
        taskList.add(task);
        task.beforeExccetue();
    }

    @Override
    public boolean isTerminated() {
        return super.isTerminated();
    }
}
