package com.code.fuqinqin.designPattern.observer.org;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/13 16:20
 */
@Data
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state = 0;

    public void setState(int state){
        this.state = state;
        this.notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    private void notifyAllObservers(){
        observers.forEach(observer -> observer.update(this.state));
    }
}
