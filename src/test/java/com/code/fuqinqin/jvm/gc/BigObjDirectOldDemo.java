package com.code.fuqinqin.jvm.gc;

/**
 * 验证大对象直接放入老年代
 *
 * -verbose:gc -XX:+PrintGCDetails -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:PretenureSizeThreshold=3145728 -XX:SurvivorRatio=8
 *
 * @author fuqinqin
 * */
public class BigObjDirectOldDemo {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args){
        byte[] bytes = new byte[4 * _1M];
    }
}
