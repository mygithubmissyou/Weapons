package com.lzy.weapon.testjava;

/**
 * Created by Adminstrator on 2018/3/15.
 */

public class ThreadTest extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("thread run");
    }
    public static void main(String[] args){
        ThreadTest threadTest=new ThreadTest();
        threadTest.start();
    }
}
