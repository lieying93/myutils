package com.button.listen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import com.sort.ReadConfig;
import com.ui.Configure;

public class ConListen implements ActionListener {
	private ButtonGroup bg;
	private Configure con;
	public ConListen(ButtonGroup bg,Configure con) {
		// TODO Auto-generated constructor stub
		this.bg = bg;
		this.con=con;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String enable="";
		Enumeration<AbstractButton> radioBtns	= bg.getElements();
		while (radioBtns.hasMoreElements()) {  
		    AbstractButton btn = radioBtns.nextElement();  
		    if(btn.isSelected()){  
		         enable=btn.getText(); 
		        ReadConfig.setValue("serialNum", enable);
		        break;
		    }  
		}  
		con.dispose();
	}
  
}
