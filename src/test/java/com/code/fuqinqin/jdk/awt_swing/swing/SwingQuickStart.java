package com.code.fuqinqin.jdk.awt_swing.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Swing组件快速启动
 * @author fuqinqin
 * @date 2020-05-16
 * */
public class SwingQuickStart extends JFrame implements ActionListener {
    private JLabel label;
    private JButton button;
    private String labelPrefix = "current num : ";
    private int num = 0;

    public SwingQuickStart(String name){
        super(name);
        label = new JLabel(labelPrefix + num);
        button = new JButton("i am a swing Button");

        // 创建一个button的快捷键[alt + i]，等同于单击按钮
        button.setMnemonic('i');

        // 设置按钮提示语
        button.setToolTipText("press me");

        button.addActionListener(this);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 1));
        contentPane.add(label);
        contentPane.add(button);

        pack();
        setVisible(true);

        // 用户选择jframe的关闭图标将结束程序
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        num++;
        label.setText(labelPrefix + num);
    }

    public static void main(String[] args){
        new SwingQuickStart("Hello Swing");
    }
}
