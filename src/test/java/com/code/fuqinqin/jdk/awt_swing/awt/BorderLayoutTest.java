package com.code.fuqinqin.jdk.awt_swing.awt;

import com.code.fuqinqin.jdk.awt_swing.AwtSwingTest;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 边界layout
 * @author fuqinqin
 * @date 2020-05-15
 * */
public class BorderLayoutTest extends AwtSwingTest {
    @Test
    public void test(){
        Frame frame = new Frame("Hello BorderLayout");

        String[] names = {"North", "South", "West", "East", "Center"};
        Button[] buttons = new Button[5];
        String[] locations = {BorderLayout.NORTH, BorderLayout.SOUTH, BorderLayout.WEST, BorderLayout.EAST, BorderLayout.CENTER};

        ActionListener listener = e -> {
            for (Button button : buttons) {
                if(e.getSource() == button)
                    button.setVisible(false);
                else
                    button.setVisible(true);
                frame.getLayout().layoutContainer(frame);
            }
        };

        for (int i = 0; i < names.length; i++) {
            Button button = new Button(names[i]);
            buttons[i] = button;
            button.addActionListener(listener);
            frame.add(button, locations[i]);
        }

        frame.setSize(250, 250);
        frame.setVisible(true);
    }
}
