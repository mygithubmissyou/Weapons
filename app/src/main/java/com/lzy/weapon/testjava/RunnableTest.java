package com.lzy.weapon.testjava;

/**
 * Created by Adminstrator on 2018/3/15.
 */

public class RunnableTest implements Runnable {
    @Override
    public void run() {
        System.out.print("run");
    }
    public static void main(String[] args){
        RunnableTest runnableTest=new RunnableTest();
        Thread thread=new Thread(runnableTest);
        thread.start();
    }
}
