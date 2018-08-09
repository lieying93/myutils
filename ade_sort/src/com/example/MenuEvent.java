package com.example;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MenuEvent extends JFrame {
	private JMenuBar aaa;
	private JMenu bbb;
	private JPanel panel;
	private JButton buttons[][];
	int a = 5, b = 5;
	Container c = getContentPane();

	public MenuEvent() {
		aaa = new JMenuBar();
		this.setJMenuBar(aaa);
		bbb = new JMenu("选项");
		aaa.add(bbb);
		JMenuItem ccc = new JMenuItem("初级");
		JMenuItem ddd = new JMenuItem("中级");
		JMenuItem eee = new JMenuItem("高级");
		ccc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				a = 10;
				b = 10;
				refreshUI();
			}
		});
		bbb.add(ccc);
		bbb.addSeparator();
		ddd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				a = 15;
				b = 15;
				refreshUI();
			}
		});
		bbb.add(ddd);
		bbb.addSeparator();
		eee.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				a = 20;
				b = 20;
				refreshUI();
			}
		});
		bbb.add(eee);

		refreshUI();
		setSize(500, 500);
		setVisible(true);
	}

	private void refreshUI() {
		buttons = new JButton[a][b];
		panel = new JPanel();
		panel.setLayout(new GridLayout(a, b, 2, 2));
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				buttons[i][j] = new JButton();
				panel.add(buttons[i][j]);
			}
		}
		c.removeAll();
		c.add(panel, BorderLayout.CENTER);
		this.validate();
	}

	public static void main(String[] args) {
		MenuEvent m = new MenuEvent();
	}
}