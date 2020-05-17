package com.code.fuqinqin.jdk.awt_swing.myself;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * WebSocket工具类
 *
 *
 * @author fuqinqin
 * @date 2020-05-17
 * */
public class WebSocketKit extends JFrame {
    private int width = 1500;
    private int height = 840;

    public WebSocketKit(String name){
        super(name);

        Container contentPane = getContentPane();
        contentPane.add(buildNorthTab(), BorderLayout.NORTH);
        contentPane.add(buildCenter(), BorderLayout.CENTER);
        contentPane.add(buildEast(), BorderLayout.EAST);
        contentPane.add(buildSouth(), BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocation(200, 100);
        setResizable(false);
        setVisible(true);
    }

    /**
     * 构建top栏
     * */
    private JPanel buildNorthTab(){
        JPanel northPanel = new JPanel();
        northPanel.setSize(width, height/5);
        FlowLayout layout = new FlowLayout();
        layout.setAlignment(FlowLayout.LEFT);
        northPanel.setLayout(layout);
        northPanel.setBorder(new TitledBorder("URL"));

        JLabel jLabel = new JLabel("URL：");
        northPanel.add(jLabel);

        JTextField urlText = new JTextField(50);
        urlText.setEditable(true);
        northPanel.add(urlText);

        JButton connect = new JButton("连接");
        JButton disconnect = new JButton("断开连接");
        northPanel.add(connect);
        northPanel.add(disconnect);

        return northPanel;
    }

    private JPanel buildCenter(){
        JPanel centerPanel = new JPanel();
        TitledBorder titledBorder = new TitledBorder("请求数据");
        titledBorder.setTitleColor(Color.BLUE);
        centerPanel.setBorder(titledBorder);



        return centerPanel;
    }

    private JPanel buildEast(){
        JPanel eastPanel = new JPanel();
        eastPanel.setBorder(new TitledBorder("响应数据"));
        GridLayout layout = new GridLayout(1, 1);
        eastPanel.setLayout(layout);

        JTextArea textArea = new JTextArea(30, 50);
        eastPanel.add(new JScrollPane(textArea));

        return eastPanel;
    }

    private JPanel buildSouth(){
        JPanel southPanel = new JPanel();
        southPanel.setBorder(new TitledBorder("日志"));

        JTextArea textArea = new JTextArea(10, 134);
        JScrollPane scrollPane = new JScrollPane(textArea);
        southPanel.add(scrollPane);

        return southPanel;
    }

    public static void main(String[] args){
        new WebSocketKit("WebSocketKit");
    }
}
