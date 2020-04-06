package com.code.fuqinqin.jvm.gc;

/**
 * 验证对象优先在Eden分配，放不下的情况下放入老年代
 *
 * -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:SurvivorRatio=8
 *
 * @author fuqinqin
 * */
public class EdenFirstDemo {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args){
        byte[] b1 = new byte[2 * _1M];
        byte[] b2 = new byte[2 * _1M];
        byte[] b3 = new byte[2 * _1M];
        byte[] b4 = new byte[4 * _1M];

//        System.gc();
    }
}
