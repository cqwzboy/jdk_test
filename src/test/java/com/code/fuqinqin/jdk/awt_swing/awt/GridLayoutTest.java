package com.code.fuqinqin.jdk.awt_swing.awt;

import com.code.fuqinqin.jdk.awt_swing.AwtSwingTest;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 网格布局
 * @author fuqinqin
 * @date 2020-05-15
 * */
public class GridLayoutTest extends AwtSwingTest {
    @Test
    public void test(){
        String[] names = {"one", "two", "three", "four", "five", "six"};

        setLayout();

        ActionListener listener = e -> {
            setLayout();
            frame.getLayout().layoutContainer(frame);
        };

        for (int i = 0; i < names.length; i++) {
            Button button = new Button(names[i]);
            button.addActionListener(listener);
            frame.add(button);
        }

        frame.setSize(250, 250);
        frame.setVisible(true);
    }

    private Frame frame = new Frame("Hello GridLayout");
    private GridLayout layout1 = new GridLayout(2, 3);
    private GridLayout layout2 = new GridLayout(2, 3, 5, 10);
    private boolean flag;
    private void setLayout(){
        if(flag){
            frame.setLayout(layout1);
        }else{
            frame.setLayout(layout2);
        }
        flag = !flag;
    }

    /**
     * 计算器页面
     * */
    @Test
    public void calculator(){
        String[] grids = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "=", "/"};

        Frame frame = new Frame("计算器");
        Panel panel = new Panel(new GridLayout(4, 4, 3, 3));
        Label label = new Label();
        frame.add(panel, BorderLayout.CENTER);
        frame.add(label, BorderLayout.NORTH);

        for (int i = 0; i < grids.length; i++) {
            panel.add(new Button(grids[i]));
        }

//        frame.pack();
        frame.setSize(250, 250);
        frame.setVisible(true);
    }
}
