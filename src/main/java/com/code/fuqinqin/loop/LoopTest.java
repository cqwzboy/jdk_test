package com.code.fuqinqin.loop;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 循环处理效率验证
 *
 * @author fuqinqin
 * @date 2021-12-21
 * */
public class LoopTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        List<Student> studentList = Student.buildList(10000000);
        new Thread(new SingleLoopThread(studentList, countDownLatch), SingleLoopThread.class.getSimpleName()).start();
        new Thread(new MultiLoopThread(studentList, countDownLatch), MultiLoopThread.class.getSimpleName()).start();
        countDownLatch.await();
        System.out.println("main done!");
    }
}
