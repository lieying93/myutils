package com.ui;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Interface extends JFrame{ 
	private JPanel contentPane;
	private JPanel qrcodePanel ;
	private String filePath="";
	private String title="快速生成排序语句";
	private JTextArea jta = null;   //文本域  
	private JPanel jp = null;       //面板  
	private JScrollPane jsp =null;  
	private JComboBox jc = null;    //组合框  
	private JTextField jtf = null;  //文本框     
	private JButton jb = null;      //按钮  
	public JPanel getQrcodePanel() {
		return qrcodePanel;
	}

	public void setQrcodePanel(JPanel qrcodePanel) {
		this.qrcodePanel = qrcodePanel;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public Interface() {
//		System.out.println(Config.getValue("qrcodePath"));
//加载界面
		initFrame();
	}

	public void initFrame() {
		setBackground(Color.WHITE);
		this.setResizable(true);
		this.setTitle(title);
//		图标
		this.setIconImage(new ImageIcon("./image/tubiao.png").getImage());
//		关闭，释放资源
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 900, 400);
		this.contentPane = new JPanel();
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
//		面板
		qrcodePanel = new JPanel();
		qrcodePanel.setBounds(0, 0, 295, 295);
		contentPane.add(qrcodePanel);		
		this.setLocationRelativeTo(null);
//		图形界面显示
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		  new Interface();
	  }
}