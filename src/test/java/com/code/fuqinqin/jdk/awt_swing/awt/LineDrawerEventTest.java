package com.code.fuqinqin.jdk.awt_swing.awt;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * 画图事件测试类
 * @author fuqinqin
 * @date 2020-05-15
 * */
public class LineDrawerEventTest extends Frame {
    private List<List<Point>> lines = new ArrayList<>();
    private List<Point> currLine;

    public LineDrawerEventTest(){}

    public LineDrawerEventTest(String name){
        super(name);
    }

    public void run(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currLine = new ArrayList<>();
                lines.add(currLine);
                currLine.add(new Point(e.getX(), e.getY()));
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currLine.add(new Point(e.getX(), e.getY()));
                repaint();
            }
        });

        setSize(300, 300);
        setVisible(true);

        synchronized (LineDrawerEventTest.class){
            try {
                LineDrawerEventTest.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 每次调用paint方法前都不清空界面，就重写该方法
     * */
    @Override
    public void update(Graphics g) {
        paint(g);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        for (List<Point> line : lines) {
            int x1=-1, y1=-1, x2=-1, y2=-1;
            for (Point point : line) {
                x2 = (int) point.getX();
                y2 = (int) point.getY();
                if(x1!=-1 && y1!=-1){
                    g.drawLine(x1, y1, x2, y2);
                }
                x1 = x2;
                y1 = y2;
            }
        }
    }

    public static void main(String[] args){
        new LineDrawerEventTest().run();
    }
}
