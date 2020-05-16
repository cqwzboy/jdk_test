package com.code.fuqinqin.jdk.awt_swing.swing;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 文本框，区别于文本域
 *
 * @author fuqinqin
 * @date 2020-05-15
 * */
public class TextFieldTest extends JFrame {
    private JButton
            jb1 = new JButton("输入文本"),
            jb2 = new JButton("拷贝");
    private JTextField
            jt1 = new JTextField(30),
            jt2 = new JTextField(30),
            jt3 = new JTextField(30);
    // 存放当前用户选择的文本
    private String str = new String();
    private UpperCaseDocument document = new UpperCaseDocument();

    public TextFieldTest(String name){
        super(name);

        jb1.addActionListener(new ActionListenerB1());
        jb2.addActionListener(new ActionListenerB2());

        jt1.setDocument(document);  // JTextField与UpperCaseDocument产生关联

        document.addDocumentListener(new DocumentListenerT());

        jt1.addActionListener(new ActionListenerT());

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(4, 1));
        JPanel panel = new JPanel();
        panel.add(jb1);
        panel.add(jb2);
        contentPane.add(panel);
        contentPane.add(jt1);
        contentPane.add(jt2);
        contentPane.add(jt3);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args){
        new TextFieldTest("Hello TextFieldTest");
    }

    private class DocumentListenerT implements DocumentListener{
        @Override
        public void insertUpdate(DocumentEvent e) {
            jt2.setText(jt1.getText());
            jt3.setText("Text: " + jt1.getText());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            jt2.setText(jt1.getText());
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    public class ActionListenerT implements ActionListener{
        private int count = 0;
        @Override
        public void actionPerformed(ActionEvent e) {
            jt3.setText("jt1 Action Event " + count++);
        }
    }

    public class ActionListenerB1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            jt1.setEditable(true);  // 使文本域jt1可编辑
        }
    }

    public class ActionListenerB2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(StringUtils.isBlank(jt1.getSelectedText())){
                str = jt1.getText();
            }else{
                str = jt1.getSelectedText();
            }
            document.setUpperCase(false);
            jt1.setText("Inserted by Button 2: "+str);
            document.setUpperCase(true);
            jt1.setEditable(false);
        }
    }

    @Data
    public class UpperCaseDocument extends PlainDocument{
        private boolean upperCase = true;

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if(upperCase){
                str = str.toUpperCase();
            }
            super.insertString(offs, str, a);
        }
    }
}
