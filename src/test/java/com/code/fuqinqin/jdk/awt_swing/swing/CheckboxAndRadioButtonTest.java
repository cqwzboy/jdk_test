package com.code.fuqinqin.jdk.awt_swing.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 复选框和单选框
 *
 * @author fuqinqin
 * @date 2020-05-16
 * */
public class CheckboxAndRadioButtonTest extends JFrame {
    private JLabel label1 = new JLabel("兴趣：");
    private JCheckBox cb1 = new JCheckBox("游泳");
    private JCheckBox cb2 = new JCheckBox("骑行");
    private JCheckBox cb3 = new JCheckBox("旅游");
    private JLabel label2 = new JLabel("性别：");
    private JRadioButton rb1 = new JRadioButton("男", true);
    private JRadioButton rb2 = new JRadioButton("女");
    private ButtonGroup group = new ButtonGroup();

    private JTextArea area = new JTextArea(10, 20);
    private JScrollPane downPane = new JScrollPane(area);
    private JPanel upPanel = new JPanel();

    /**
     * 兴趣爱好列表
     * */
    private List<String> hobbies = new ArrayList<>();
    private String sex = "男";

    /**
     * JCheckBox 监听器
     * */
    private ActionListener listener1 = e -> {
        JCheckBox checkBox = (JCheckBox) e.getSource();
        if(checkBox.isSelected()){// 当前复选框被选中
            hobbies.add(checkBox.getText());
        }else{
            hobbies.remove(checkBox.getText());
        }
        print();
    };

    /**
     * JRadioButton 监听器
     * */
    private ActionListener listener2 = e -> {
        JRadioButton radioButton = (JRadioButton) e.getSource();
        sex = radioButton.getText();
        print();
    };

    public CheckboxAndRadioButtonTest(String name){
        super(name);

        // 文本域可编辑
        area.setEditable(true);

        cb1.addActionListener(listener1);
        cb2.addActionListener(listener1);
        cb3.addActionListener(listener1);
        rb1.addActionListener(listener2);
        rb2.addActionListener(listener2);

        group.add(rb1);
        group.add(rb2);

        // 布局管理器
        upPanel.setLayout(new FlowLayout());
        upPanel.add(label1);
        upPanel.add(cb1);
        upPanel.add(cb2);
        upPanel.add(cb3);
        upPanel.add(label2);
        upPanel.add(rb1);
        upPanel.add(rb2);

        Container contentPane = getContentPane();
        contentPane.add(upPanel, BorderLayout.NORTH);
        contentPane.add(downPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new CheckboxAndRadioButtonTest("CheckboxAndRadioButtonTest");
    }

    /**
     * 打印
     * */
    private void print(){
        area.append("您的兴趣爱好包括：");
        for (String hobby : hobbies) {
            area.append(hobby);
            area.append(" ");
        }
        area.append("\t您的性别是："+sex+"\n");
    }
}
