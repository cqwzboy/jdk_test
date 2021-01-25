package com.code.fuqinqin.designPattern.decorator.component;

/**
 * <p>
 *     具体构件2：长方体
 * </p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/25 17:52
 */
public class Cuboid implements Shape {
    @Override
    public void draw() {
        System.out.println("-- Shape: Cuboid --");
    }
}
