package com.code.fuqinqin.designPattern.decorator.decorator;

import com.code.fuqinqin.designPattern.decorator.component.Shape;

/**
 * <p>
 *     抽象装饰角色
 * </p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/25 17:53
 */
public abstract class AbstractDecorator implements Shape {
    protected Shape shape;

    public AbstractDecorator(Shape shape){
        this.shape = shape;
    }
}
