package com.code.fuqinqin.jdk.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * <p>Volatile关键字测试</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/24 9:51
 */
public class VolatileTest implements Runnable {

    //    volatile boolean flag = false;
    boolean flag = false;
    long i;

    @Override
    public void run() {
        while (!flag) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest test = new VolatileTest();
        /*Thread thread = new Thread(test);
        thread.setDaemon(true);
        thread.start();*/
        new Thread(test).start();
        TimeUnit.SECONDS.sleep(2);
        test.flag = true;
        System.out.println("i = " + test.i);
    }

}