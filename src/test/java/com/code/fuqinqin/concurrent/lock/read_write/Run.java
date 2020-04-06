package com.code.fuqinqin.concurrent.lock.read_write;

public class Run {
    public static void main(String[] args){
        Service service = new Service();

//        readRead(service);

//        writeWrite(service);

        readWrite(service);
    }

    /********************* 写写共享 ********************/
    private static void writeWrite(Service service) {
        ThreadContainer.ThreadC threadC = new ThreadContainer.ThreadC(service);
        ThreadContainer.ThreadD threadD = new ThreadContainer.ThreadD(service);
        threadC.start();
        threadD.start();
    }

    /********************* 读读共享 ********************/
    private static void readRead(Service service) {
        ThreadContainer.ThreadA threadA = new ThreadContainer.ThreadA(service);
        ThreadContainer.ThreadB threadB = new ThreadContainer.ThreadB(service);
        threadA.start();
        threadB.start();
    }

    /********************* 读写互斥 ********************/
    private static void readWrite(Service service) {
        ThreadContainer.ThreadE threadE = new ThreadContainer.ThreadE(service);
        ThreadContainer.ThreadF threadF = new ThreadContainer.ThreadF(service);
        threadE.start();
        threadF.start();
    }
}
