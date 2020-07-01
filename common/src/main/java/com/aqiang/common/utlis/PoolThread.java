package com.aqiang.common.utlis;

import java.util.concurrent.Executors;

public class PoolThread {
    private PoolThread(){

    }

    private static PoolThread poolThread = new PoolThread();

    public static PoolThread getInstance(){
        return poolThread;
    }

    public void execute(Runnable runnable){
        Executors.newCachedThreadPool().submit(runnable);
    }
}
