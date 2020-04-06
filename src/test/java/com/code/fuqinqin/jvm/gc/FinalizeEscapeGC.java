package com.code.fuqinqin.jvm.gc;

/**
 * 对象通过finalize()方法逃脱GC
 *
 * 演示了两点：
 * 1. 对象可以在被GC时自我拯救
 * 2. 这种自救的机会只有一次，因为一个对象的finalise()方法最多只能被系统自动调用一次
 *
 * @author fuqinqin
 * */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("yes, i am still alive.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method invoked");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, i am dead.");
        }

        // 下面这段代码与上面完全相同，但是这次自救却失败了，原因是每个对象的finalize方法只会被迪奥哟用一次
        SAVE_HOOK = null;
        System.gc();
        // 因为finalize方法优先级低，所以暂停0.5秒以等待它
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, i am dead.");
        }
    }
}
