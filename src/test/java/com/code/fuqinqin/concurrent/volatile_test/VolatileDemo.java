package com.code.fuqinqin.concurrent.volatile_test;

public class VolatileDemo {
    private static boolean flag;

    public static class VolatileThread extends Thread {
        @Override
        public void run() {
            while (!flag) {
                System.out.println(1);
            }
        }
    }

    public static void main(String[] args){
        new VolatileThread().start();
        try {
               Thread.sleep(1000);
        } catch (InterruptedException e) {
               e.printStackTrace();
        }
        flag = true;
    }
}
