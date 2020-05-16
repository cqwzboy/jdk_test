package com.code.fuqinqin.jdk.awt_swing.awt;

import com.code.fuqinqin.jdk.awt_swing.AwtSwingTest;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 卡片布局期
 * @author fuqinqin
 * @date 2020-05-15
 * */
public class CardLayoutTest extends AwtSwingTest {
    @Test
    public void test(){
        String[] names = {"blue", "yellow", "green"};
        Color[] colors = {Color.BLUE, Color.YELLOW, Color.GREEN};

        Frame frame = new Frame("Hello CardLayout");
        Panel northPanel = new Panel(new GridLayout(1, 3));
        Panel centerPanel = new Panel();
        CardLayout cardLayout = new CardLayout();
        centerPanel.setLayout(cardLayout);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);

        ActionListener listener = e -> {
            Button button = (Button) e.getSource();
            cardLayout.show(centerPanel, button.getLabel());
        };

        for (int i = 0; i < names.length; i++) {
            Button button = new Button(names[i]);
            button.addActionListener(listener);
            northPanel.add(button);

            Panel panel = new Panel();
            panel.setBackground(colors[i]);
            centerPanel.add(panel, names[i]);
        }

        cardLayout.show(centerPanel, names[1]); // 默认显示黄色按钮
        frame.setSize(250, 250);
        frame.setVisible(true);
    }
}
