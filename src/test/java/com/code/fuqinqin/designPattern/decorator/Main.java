package com.code.fuqinqin.designPattern.decorator;

import com.code.fuqinqin.designPattern.decorator.component.Circle;
import com.code.fuqinqin.designPattern.decorator.component.Cuboid;
import com.code.fuqinqin.designPattern.decorator.component.Shape;
import com.code.fuqinqin.designPattern.decorator.decorator.AbstractDecorator;
import com.code.fuqinqin.designPattern.decorator.decorator.RedDecorator;
import com.code.fuqinqin.designPattern.decorator.decorator.YellowDecorator;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/25 18:00
 */
public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape cuboid = new Cuboid();

        AbstractDecorator redDecorator = new RedDecorator(circle);
        AbstractDecorator yellowCuboid = new YellowDecorator(cuboid);

        System.out.println("<< Circle Red >>");
        redDecorator.draw();

        System.out.println("<< Cuboid Yellow >>");
        yellowCuboid.draw();
    }
}
