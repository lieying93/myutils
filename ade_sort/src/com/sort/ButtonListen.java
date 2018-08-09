package com.sort;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import org.apache.log4j.Logger;

public class ButtonListen implements ActionListener {

	private static Logger logger = Logger.getLogger(ButtonListen.class);
	private JTextArea inTextArea;
	private JTextArea outTextArea;
	private int type = 0;

	public ButtonListen(JTextArea inTextArea, JTextArea outTextArea, int type) {
		this.inTextArea = inTextArea;
		this.outTextArea = outTextArea;
		this.type = type;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String inContent = inTextArea.getText();
		logger.info("actionPerformed-->Content="+inContent);
		String outContent = "";
		logger.info("actionPerformed-->type="+type);
		switch (type) {
		case 0:
			inTextArea.setText("");
			outContent = "";
			break;
		case 1:
			outContent = SortSql.getQuery(inContent);
			break;
		case 2:
			outContent = GenerateSerial.generateNum(inContent);
			break;
		case 3:
			
			break;
		default:
			outContent = "";
			break;
		}
		outTextArea.setText(outContent);
	}

}
