package com.code.fuqinqin.designPattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/13 16:56
 */
public class CObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("CObserver org = "+arg+", state = "+((TestObservable)o).getState());
    }
}
