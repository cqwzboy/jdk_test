package com.code.fuqinqin.jvm.jstack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JstackTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object object = new Object();
        createLockThread(object);
        br.readLine();
        for (int i = 0; i < 3; i++) {
            new Thread(new SyncAddRunnable(1, 2), "12-" + i).start();
            new Thread(new SyncAddRunnable(2, 1), "21-" + i).start();
        }
    }

    /**
     * 线程死循环演示
     */
    private static void createBusyThread() {
        new Thread(() -> {
            while (true)
                ;
        }, "testBusyThread").start();
    }

    /**
     * 线程锁等待演示
     */
    private static void createLockThread(final Object lock) {
        new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread").start();
    }

    /**
     * 死锁演示
     */
    private static class SyncAddRunnable implements Runnable {
        private int a, b;

        SyncAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println("[" + Thread.currentThread().getName() + "] " + (a + b));
                }
            }
        }
    }
}
