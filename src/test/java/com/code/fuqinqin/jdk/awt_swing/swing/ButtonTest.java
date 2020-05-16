package com.code.fuqinqin.jdk.awt_swing.swing;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

/**
 * 按钮组件
 * @author fuqinqin
 * @date 2020-05-16
 * */
public class ButtonTest {

    public static class Test_1 extends JFrame{
        public Test_1(String name){
            super(name);

            Container contentPane = getContentPane();
            contentPane.setLayout(new FlowLayout());
            contentPane.add(new JButton("JButton"));
            contentPane.add(new JToggleButton("JToggleButton"));
            contentPane.add(new JCheckBox("JCheckBox"));
            contentPane.add(new JRadioButton("JRadioButton"));
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.setBorder(new TitledBorder("Directions"));
            panel.add(new BasicArrowButton(BasicArrowButton.NORTH));
            panel.add(new BasicArrowButton(BasicArrowButton.SOUTH));
            panel.add(new BasicArrowButton(BasicArrowButton.WEST));
            panel.add(new BasicArrowButton(BasicArrowButton.EAST));
            contentPane.add(panel);

            pack();
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public static void main(String[] args){
            new Test_1("test_1");
        }
    }
}
