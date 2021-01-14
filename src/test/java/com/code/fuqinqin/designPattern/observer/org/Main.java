package com.code.fuqinqin.designPattern.observer.org;

import com.code.fuqinqin.designPattern.observer.org.impls.AObserver;
import com.code.fuqinqin.designPattern.observer.org.impls.BObserver;
import com.code.fuqinqin.designPattern.observer.org.impls.CObserver;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/13 16:26
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();

        new AObserver(subject);
        new BObserver(subject);
        new CObserver(subject);

        System.out.println("-------------- first update --------------");
        subject.setState(1);
        System.out.println("-------------- seconde update --------------");
        subject.setState(2);
    }
}
