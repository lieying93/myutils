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
		setTitle("sql���ɹ���");
		this.setIconImage(new ImageIcon("./images/amarsoft.png").getImage());
		JMenuBar menubar = new JMenuBar();
		JMenu jm1=new JMenu("�ļ�");//�������ļ����˵�   
		menubar.add(jm1);
		JMenuItem exit = new JMenuItem("�˳�");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.info("init-->ϵͳ�����˳�");
				System.exit(0);
			}
			
		});
		jm1.add(exit);
		
		JMenu jm2=new JMenu("�༭");//�������ļ����˵�   
		JMenuItem configure = new JMenuItem("����");
		configure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				logger.info("init-->�༭-->���� ");
				new Configure();
			}
			
		});
		jm2.add(configure);
		menubar.add(jm2);
		
		JMenu jm3=new JMenu("����");//�������ļ����˵�   
		menubar.add(jm3);
		this.setJMenuBar(menubar);
		JPanel pField = new JPanel();
		getContentPane().add("North", pField);
     
		pField.add(new JLabel("�����"));

		JTextArea inTextArea = new JTextArea(10, 70);
		inTextArea.setLineWrap(true);// �����Զ�����
		JScrollPane jsp = new JScrollPane(inTextArea);// ���ı�����ӹ�����
		pField.add(jsp);
		//����sql������
		JPanel pSql = new JPanel();
		getContentPane().add("South", pSql);

		pSql.add(new JLabel("�����"));
		JTextArea outTextArea = new JTextArea(10, 70);
		outTextArea.setLineWrap(true);// �����Զ�����
		JScrollPane jspSql = new JScrollPane(outTextArea);// ���ı�����ӹ�����
		pSql.add(jspSql);

		// ��������
		JPanel pFunction = new JPanel();
		JButton query = new JButton("��ѯ���");
		query.addActionListener(new ButtonListen(inTextArea, outTextArea, 1));
		// query.setPreferredSize(new Dimension(100,50));
		JButton number = new JButton("�������");
		// query.setPreferredSize(new Dimension(100,50));
		// ΪButton��Ӽ�����
		number.addActionListener(new ButtonListen(inTextArea, outTextArea, 2));
		//��չ���
		JButton clear = new JButton("���");
		clear.addActionListener(new ButtonListen(inTextArea, outTextArea, 0));
		
		pFunction.add(query);
		pFunction.add(number);
		pFunction.add(clear);
		getContentPane().add("Center", pFunction);
	}
}