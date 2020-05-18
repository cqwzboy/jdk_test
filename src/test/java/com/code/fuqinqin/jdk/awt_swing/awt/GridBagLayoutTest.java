package com.code.fuqinqin.jdk.awt_swing.awt;

import com.code.fuqinqin.jdk.awt_swing.AwtSwingTest;
import org.junit.Test;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 网格包布局管理器
 * @author fuqinqin
 * @date 2020-05-15
 * */
public class GridBagLayoutTest extends AwtSwingTest {
    private Frame frame = new Frame("Hello GridBagLayout");
    private GridBagLayout gridBagLayout = new GridBagLayout();
    private GridBagConstraints constraints = new GridBagConstraints();
    private Button[] buttons = new Button[7];

    @Test
    public void test(){
        frame.setLayout(gridBagLayout);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new Button("button"+i);
        }

        constraints.fill = GridBagConstraints.BOTH;
        addComponent(buttons[0], 0, 0, 1, 3);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        addComponent(buttons[1], 0, 1, 2, 1);

        addComponent(buttons[2], 2, 1, 2, 1);

        constraints.weightx = 100;  // 设置水平方向的重量
        constraints.weighty = 1;  // 设置垂直方向的重量
//        constraints.weightx = 0;  // 设置水平方向的重量
//        constraints.weighty = 0;  // 设置垂直方向的重量
        constraints.fill = GridBagConstraints.BOTH;
        addComponent(buttons[3], 1, 1, 1, 1);

        constraints.weightx = 0;  // 设置水平方向的重量
        constraints.weighty = 0;  // 设置垂直方向的重量
        addComponent(buttons[4], 1, 2, 1, 1);

        addComponent(buttons[5], 3, 0, 2, 1);

        addComponent(buttons[6], 3, 2, 1, 1);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing...");
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private void addComponent(Component component,
                              int row, int column,
                              int weight, int height){
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = weight;
        constraints.gridheight = height;
        gridBagLayout.setConstraints(component, constraints);
        frame.add(component);
    }
}
