package com.code.fuqinqin.designPattern.observer.org;

/**
 * <p>观察者</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/13 16:20
 */
public abstract class Observer {
    protected Subject subject;
    protected abstract void update(int state);
}
