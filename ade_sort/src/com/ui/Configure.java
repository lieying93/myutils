package com.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.button.listen.ConListen;

public class Configure extends JFrame{
	public Configure() {
		setSize(200, 200);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
		setVisible(true);
		setResizable(false);
	}
	protected void init(){
		setTitle("配置");
		this.setIconImage(new ImageIcon("./images/amarsoft.png").getImage());
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(3, 2));
		JLabel jl1 = new JLabel("序号个数");
		jp.add(jl1);   
//		JTextField jtf1= new JTextField(5);
//		jp.add(jtf1);
		this.add("North", jp);
		
		
		JPanel jpRadioButton = new JPanel();
		ButtonGroup bg = new ButtonGroup();//创建按钮组
		JRadioButton radioButton1 = new JRadioButton("5");
		JRadioButton radioButton2 = new JRadioButton("10");
		JRadioButton radioButton3 = new JRadioButton("15");
		JRadioButton radioButton4 = new JRadioButton("20",true);
		
		bg.add(radioButton1);
		bg.add(radioButton2);
		bg.add(radioButton3);
		bg.add(radioButton4);
		
		jpRadioButton.add(radioButton1);
		jpRadioButton.add(radioButton2);
		jpRadioButton.add(radioButton3);
		jpRadioButton.add(radioButton4);

		this.add("Center",jpRadioButton);
		
		JPanel jpButton = new JPanel();
		JButton determine = new JButton("确定");
		determine.addActionListener(new ConListen(bg,this));
		JButton cancel = new JButton("取消");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 dispose();
			}
		});
		
		jpButton.add(determine);
		jpButton.add(cancel);
		this.add("South", jpButton);
	}
}
