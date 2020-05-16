package com.code.fuqinqin.jdk.awt_swing.swing;

import javax.swing.*;
import java.awt.*;

/**
 * 下拉列表
 *
 * @author fuqinqin
 * @date 2020-05-16
 * */
public class ComboBoxTest extends JFrame {
    /**
     * 城市列表
     * */
    private String[] cities
            = {"北京", "上海", "天津", "重庆", "成都", "武汉", "沈阳", "乌鲁木齐", "广州"};


    private JComboBox comboBox = new JComboBox();
    private JButton button = new JButton("添加更多城市选项");
    private JTextField textField = new JTextField(15);

    public ComboBoxTest(String name){
        super(name);

        // 添加默认选项
        for (int i = 0; i < 4; i++) {
            comboBox.addItem(cities[i]);
        }

        // 添加事件
        comboBox.addActionListener(e -> {
            textField.setText(comboBox.getSelectedIndex() + " - " + comboBox.getSelectedItem());
        });
        button.addActionListener(e -> {
            for (String city : cities) {
                comboBox.addItem(city);
            }
        });

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(comboBox);
        contentPane.add(button);
        contentPane.add(textField);

        comboBox.setEditable(true);
        textField.setEditable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new ComboBoxTest("ComboBoxTest");
    }
}
