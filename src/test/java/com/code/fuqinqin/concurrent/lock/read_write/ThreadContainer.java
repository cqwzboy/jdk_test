package com.code.fuqinqin.concurrent.lock.read_write;

public class ThreadContainer {
    /*************************** 读读共享 *************************/
    public static class ThreadA extends Thread {
        private Service service;

        public ThreadA(Service service){
            super("thread-a");
            this.service = service;
        }

        @Override
        public void run() {
            service.read();
        }
    }
    public static class ThreadB extends Thread {
        private Service service;

        public ThreadB(Service service){
            super("thread-b");
            this.service = service;
        }

        @Override
        public void run() {
            service.read();
        }
    }

    /*************************** 写写互斥 *************************/
    public static class ThreadC extends Thread {
        private Service service;

        public ThreadC(Service service) {
            super("thread-c");
            this.service = service;
        }

        @Override
        public void run() {
            service.write();
        }
    }
    public static class ThreadD extends Thread {
        private Service service;

        public ThreadD(Service service){
            super("thread-d");
            this.service = service;
        }

        @Override
        public void run() {
            service.write();
        }
    }

    /*************************** 读写互斥 *************************/
    public static class ThreadE extends Thread {
        private Service service;

        public ThreadE(Service service) {
            super("thread-e");
            this.service = service;
        }

        @Override
        public void run() {
            service.read();
        }
    }
    public static class ThreadF extends Thread {
        private Service service;

        public ThreadF(Service service){
            super("thread-f");
            this.service = service;
        }

        @Override
        public void run() {
            service.write();
        }
    }
}
