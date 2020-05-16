package com.code.fuqinqin.jdk.awt_swing.swing;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;

/**
 * 文本域，区别于文本框
 *
 * @author fuqinqin
 * @date 2020-05--16
 * */
public class TextAreaTest extends JFrame {
    private JTextArea a1 = new JTextArea(5, 10);    // 5行10列文本域
    private JTextArea a2 = new JTextArea(5, 10);

    private JScrollPane p1 = new JScrollPane(a1,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // 垂直方向始终保持滚动条，水平方向在需要时才显示滚动条
    private JScrollPane p2 = new JScrollPane(a2);

    private JButton button = new JButton("Copy");

    public TextAreaTest(String name){
        super(name);

        button.addActionListener(e -> {
            a2.setText(StringUtils.isBlank(a1.getSelectedText()) ? a1.getText() : a1.getSelectedText());
        });

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(p1);
        contentPane.add(button);
        contentPane.add(p2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new TextAreaTest("TextAreaTest");
    }
}
