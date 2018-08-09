package com.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class TestJRadioButton extends JFrame {
	JRadioButton jrb1;
	JRadioButton jrb2;
	JRadioButton jrb3;
	JRadioButton jrb4;

	public TestJRadioButton() {
		jrb1 = new JRadioButton("china");
		jrb2 = new JRadioButton("usa");
		jrb3 = new JRadioButton("Egl");
		jrb4 = new JRadioButton("fra");

		ButtonGroup bg = new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		bg.add(jrb3);
		bg.add(jrb4);

		// jp.add(bg);
		JPanel jp = new JPanel();
		jp.add(jrb1);
		jp.add(jrb2);
		jp.add(jrb3);
		jp.add(jrb4);
		// 注意jrb1,jrb2,jrb3,jrb4被添加两次，一次是加入到ButtonGroup中，一次是JPanel中，但ButtonGroup不需要被添加
		getContentPane().add(jp);

		jrb1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (jrb1.isSelected()) {
					System.out.println(jrb1.getText());
				}

			}
		});

		jrb2.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (jrb2.isSelected()) {
					System.out.println(jrb2.getText());
				}

			}
		});

		jrb3.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (jrb3.isSelected()) {
					System.out.println(jrb3.getText());
				}
			}
		});

		jrb4.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (jrb4.isSelected()) {
					System.out.println(jrb4.getText());
				}
			}
		});

		setSize(300, 200);
		setVisible(true);

	}

	public static void main(String[] args) {
		new TestJRadioButton();
	}

}
