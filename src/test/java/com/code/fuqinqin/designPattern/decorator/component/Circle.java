package com.code.fuqinqin.designPattern.decorator.component;

/**
 * <p>
 *     具体构件1：球
 * </p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/25 17:51
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("-- Shape: Circle --");
    }
}
