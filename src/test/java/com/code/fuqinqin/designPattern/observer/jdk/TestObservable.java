package com.code.fuqinqin.designPattern.observer.jdk;

import lombok.Data;

import java.util.Observable;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/13 16:54
 */
@Data
public class TestObservable extends Observable {
    private int state;

    public synchronized void setState(int state){
        this.state = state;
        super.setChanged();
        super.notifyObservers(state);
    }
}
