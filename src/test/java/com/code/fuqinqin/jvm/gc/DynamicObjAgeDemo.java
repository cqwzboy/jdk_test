package com.code.fuqinqin.jvm.gc;

/**
 * 动态对象年龄判断
 *
 * 如果Survivor空间中相同年龄的所偶有对象大小总和达到了Survivor
 * 空间的一半，将会直接忽略PretenureSizeThreshold设置，将年龄大于等于临界值
 * 年龄的所有对象转移到老年代
 *
 * @author fuqinqin
 * */
public class DynamicObjAgeDemo {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args){
        byte[] b1 = new byte[_1M / 4];
        byte[] b2 = new byte[_1M / 4];
        byte[] b3 = new byte[_1M * 4];
        byte[] b4 = new byte[_1M * 4];
        b4 = null;
        b4 = new byte[_1M * 4];
//        byte[] b5 = new byte[_1M * 9];
        try {
               Thread.sleep(1000000);
        } catch (InterruptedException e) {
               e.printStackTrace();
        }
    }
}
