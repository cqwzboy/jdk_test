package com.code.fuqinqin.designPattern.decorator.decorator;

import com.code.fuqinqin.designPattern.decorator.component.Shape;

/**
 * <p>
 * 具体装饰者2：黄色
 * </p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/25 17:59
 */
public class YellowDecorator extends AbstractDecorator {
    public YellowDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        shape.draw();
        System.out.println("== Color: Yellow ==");
    }
}
