package com.code.fuqinqin.designPattern.decorator.decorator;

import com.code.fuqinqin.designPattern.decorator.component.Shape;

/**
 * <p>
 * 具体装饰者1：红色
 * </p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/25 17:55
 */
public class RedDecorator extends AbstractDecorator {
    public RedDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        shape.draw();
        System.out.println("== Color: Red ==");
    }
}
