package com.code.fuqinqin.jdk.awt_swing;

import org.junit.After;

public class AwtSwingTest {
    @After
    public void keeper(){
        synchronized (AwtSwingTest.class){
            try {
                AwtSwingTest.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
