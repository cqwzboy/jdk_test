package com.code.fuqinqin.loop;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

/**
 * 在多次循环内处理多种业务逻辑
 *
 * @author fuqinqin
 * @date 2021-12-21
 */
public class MultiLoopThread implements Runnable {
    private CountDownLatch countDownLatch;
    private List<Student> studentList;

    public MultiLoopThread(List<Student> studentList, CountDownLatch countDownLatch) {
        this.studentList = studentList;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        List<Student> type2StudentList = studentList.stream().filter(student -> student.getType() == 2).collect(Collectors.toList());
        List<Student> type9StudentList = studentList.stream().filter(student -> student.getType() == 9).collect(Collectors.toList());
        List<Student> evenTypeStudentList = studentList.stream().filter(student -> student.getType() % 2 == 0).collect(Collectors.toList());
        System.out.println("[" + Thread.currentThread().getName() + "]集合数量=" + studentList.size() + ", 耗时=" + (System.currentTimeMillis() - start));
        countDownLatch.countDown();
    }
}
