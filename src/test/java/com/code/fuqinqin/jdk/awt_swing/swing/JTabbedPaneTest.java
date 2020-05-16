package com.code.fuqinqin.jdk.awt_swing.swing;

import javax.swing.*;
import java.awt.*;

/**
 * 页签面板
 *
 * @author fuqinqin
 * @date 2020-05-16
 * */
public class JTabbedPaneTest extends JFrame {
    private String[] colorNames = {"red", "blue", "green", "black", "yellow", "pink", "white"};
    private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.YELLOW, Color.PINK, Color.WHITE};

    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);
    private JTextField textField = new JTextField(20);

    public JTabbedPaneTest(String name){
        super(name);

        for (int i = 0; i < colors.length; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(colors[i]);
            tabbedPane.addTab(colorNames[i], panel);
        }

        tabbedPane.addChangeListener(e -> {
            textField.setText("Tab selected: "+tabbedPane.getSelectedIndex());
        });

        Container contentPane = getContentPane();
        contentPane.add(tabbedPane);
        contentPane.add(textField, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new JTabbedPaneTest("JTabbedPaneTest");
    }
}
