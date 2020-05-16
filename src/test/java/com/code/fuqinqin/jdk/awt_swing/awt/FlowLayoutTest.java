package com.code.fuqinqin.jdk.awt_swing.awt;

import com.code.fuqinqin.jdk.awt_swing.AwtSwingTest;
import org.junit.Test;
import java.awt.*;

/**
 * 布局测试
 * @author fuqinqin
 * @date 2020-05-15
 * */
public class FlowLayoutTest extends AwtSwingTest {
    @Test
    public void test(){
        Frame frame = new Frame("Hello");
        FlowLayout layout = new FlowLayout();

        frame.setLayout(layout);

        Button leftButton = new Button("left");
        leftButton.addActionListener(e -> {
            layout.setAlignment(FlowLayout.LEFT);
            layout.layoutContainer(frame);
        });

        Button centerButton = new Button("center");
        centerButton.addActionListener(e -> {
            layout.setAlignment(FlowLayout.CENTER);
            layout.layoutContainer(frame);
        });

        Button rightButton = new Button("right");
        rightButton.addActionListener(e -> {
            layout.setAlignment(FlowLayout.RIGHT);
            layout.layoutContainer(frame);
        });

        frame.add(leftButton);
        frame.add(centerButton);
        frame.add(rightButton);
        frame.setSize(200, 100);
        frame.setVisible(true);
    }
}
