package com.code.fuqinqin.loop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 在单次循环内处理多种业务逻辑
 *
 * @author fuqinqin
 * @date 2021-12-21
 */
public class SingleLoopThread implements Runnable {
    private List<Student> studentList;
    private CountDownLatch countDownLatch;

    public SingleLoopThread(List<Student> studentList, CountDownLatch countDownLatch) {
        this.studentList = studentList;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        List<Student> type2StudentList = new ArrayList<>();
        List<Student> type9StudentList = new ArrayList<>();
        List<Student> evenTypeStudentList = new ArrayList<>();
        studentList.forEach(student -> {
            if (student.getType() == 2) {
                type2StudentList.add(student);
            }
            if (student.getType() == 9) {
                type9StudentList.add(student);
            }
            if (student.getType() % 2 == 0) {
                evenTypeStudentList.add(student);
            }
        });
        System.out.println("[" + Thread.currentThread().getName() + "]集合数量=" + studentList.size() + ", 耗时=" + (System.currentTimeMillis() - start));
        countDownLatch.countDown();
    }
}
