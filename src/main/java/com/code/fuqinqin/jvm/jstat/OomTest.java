package com.code.fuqinqin.jvm.jstat;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class OomTest {

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
        System.out.println("over...");
        System.gc();
        synchronized (OomTest.class){
            OomTest.class.wait();
        }
    }

    private static void fillHeap(int num) throws InterruptedException {
        List<OomObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OomObject());
        }
//        System.gc();
    }

    @Data
    private static class OomObject {
        private byte[] placeholder = new byte[64 * 1024];
    }

}
