package com.code.fuqinqin.jdk.awt_swing.swing;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * 边框
 * @author fuqinqin
 * @date 2020-05-15
 * */
public class BorderTest extends JFrame {
    /**
     * 根据Border构建JPanel
     * */
    public static JPanel getPanel(Border border){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        String name = border.getClass().getName().substring(border.getClass().getName().lastIndexOf(".") + 1);
        panel.add(new JLabel(name, JLabel.CENTER), BorderLayout.CENTER);
        panel.setBorder(border);    // 设置边框
        return panel;
    }

    public BorderTest(String name){
        super(name);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(2, 4));
        contentPane.add(getPanel(new TitledBorder("Title1")));
        contentPane.add(getPanel(new EtchedBorder()));
        contentPane.add(getPanel(new LineBorder(Color.RED)));
        contentPane.add(getPanel(new MatteBorder(5, 5, 30, 30, Color.PINK)));
        contentPane.add(getPanel(new BevelBorder(BevelBorder.RAISED)));
        contentPane.add(getPanel(new SoftBevelBorder(BevelBorder.LOWERED)));
        contentPane.add(getPanel(new CompoundBorder(
                new EtchedBorder(),
                new LineBorder(Color.BLUE)
        )));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new BorderTest("Border Test");
    }
}
