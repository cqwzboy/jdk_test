package com.code.fuqinqin.jvm.gc;


/**
 * 引用计数算法GC测试
 *
 * 验证主流虚拟机并不是采用引用计数算法判断对象的“死活”
 *
 * @author fuqinqin
 * */
public class ReferenceCoutingGC {
    public ReferenceCoutingGC instance;
    private byte[] bigSize = new byte[2*1024*1024];

    public static void main(String[] args){
        ReferenceCoutingGC objA = new ReferenceCoutingGC();
        ReferenceCoutingGC objB = new ReferenceCoutingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }
}
