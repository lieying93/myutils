package com.sort;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Logger;

import com.ui.Configure;

public class StartInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1890626147982656163L;
	private static Logger logger = Logger.getLogger(StartInterface.class);
	public static void main(String[] args) {
		new StartInterface();
	}

	public StartInterface() {
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		init();
		setVisible(true);
		setResizable(false);
	}

	protected void init() {
		setTitle("sql生成工具");
		this.setIconImage(new ImageIcon("./images/amarsoft.png").getImage());
		JMenuBar menubar = new JMenuBar();
		JMenu jm1=new JMenu("文件");//创建“文件”菜单   
		menubar.add(jm1);
		JMenuItem exit = new JMenuItem("退出");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.info("init-->系统正常退出");
				System.exit(0);
			}
			
		});
		jm1.add(exit);
		
		JMenu jm2=new JMenu("编辑");//创建“文件”菜单   
		JMenuItem configure = new JMenuItem("配置");
		configure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.info("init-->编辑-->配置 ");
				new Configure();
			}
			
		});
		jm2.add(configure);
		menubar.add(jm2);
		
		JMenu jm3=new JMenu("帮助");//创建“文件”菜单   
		menubar.add(jm3);
		this.setJMenuBar(menubar);
		JPanel pField = new JPanel();
		getContentPane().add("North", pField);
     
		pField.add(new JLabel("输入框"));

		JTextArea inTextArea = new JTextArea(10, 70);
		inTextArea.setLineWrap(true);// 设置自动换行
		JScrollPane jsp = new JScrollPane(inTextArea);// 给文本区添加滚动条
		pField.add(jsp);
		//生成sql的区域
		JPanel pSql = new JPanel();
		getContentPane().add("South", pSql);

		pSql.add(new JLabel("输出框"));
		JTextArea outTextArea = new JTextArea(10, 70);
		outTextArea.setLineWrap(true);// 设置自动换行
		JScrollPane jspSql = new JScrollPane(outTextArea);// 给文本区添加滚动条
		pSql.add(jspSql);

		// 功能区域
		JPanel pFunction = new JPanel();
		JButton query = new JButton("查询语句");
		query.addActionListener(new ButtonListen(inTextArea, outTextArea, 1));
		// query.setPreferredSize(new Dimension(100,50));
		JButton number = new JButton("生成序号");
		// query.setPreferredSize(new Dimension(100,50));
		// 为Button添加监听者
		number.addActionListener(new ButtonListen(inTextArea, outTextArea, 2));
		//清空功能
		JButton clear = new JButton("清空");
		clear.addActionListener(new ButtonListen(inTextArea, outTextArea, 0));
		
		pFunction.add(query);
		pFunction.add(number);
		pFunction.add(clear);
		getContentPane().add("Center", pFunction);
	}
}