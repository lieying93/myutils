package com.example;  

import java.awt.*;  
import javax.swing.*;  
  
public class QLogin extends JFrame{  
        //�������  
        //��������  
    JLabel jl1; //����ͼƬ  
      
        //�ϲ�����  
    JButton jb1, jb2, jb3;//��¼��ȡ����ע����  
    JPanel jp1;//ע�⣺panel��壬pane�Ǵ���  
      
        //�в�����  
    JTabbedPane jtp;// ѡ�����  
    JPanel jp2, jp3, jp4;  
        //QQѡ��  
    JLabel jl2, jl3, jl4, jl5;//QQ���롢QQ���롢�������롢���뱣��  
    JTextField jtf; //�ı���  
    JPasswordField jpf;//�����  
    JButton jb4;//�������  
    JCheckBox jcb1, jcb2;//�����¼����ס����  
        //�ֻ�ѡ��  
    JLabel jl2a, jl3a, jl4a, jl5a;//�ֻ����� ��QQ���롢�������롢���뱣��  
    JTextField jtfa;    //�ı���  
    JPasswordField jpfa;//�����  
    JButton jb4a;//�������  
    JCheckBox jcb1a, jcb2a;//�����¼����ס����  
      
    public static void main(String[] args) {  
        QLogin testLogin = new QLogin();  
  
    }  
        //���캯��  
    public QLogin(){  
        //�������  
            //��������  
        jl1 = new JLabel(new ImageIcon("images/QQ.jpg"));  
            //�в�����  
        jtp = new JTabbedPane();    //ѡ�����  
        //�в�QQ JPanel1  
        jp2 = new JPanel();  
        jl2 = new JLabel("QQ���룺", JLabel.CENTER);  
        jl3 = new JLabel("QQ���룺", JLabel.CENTER);  
        jl4 = new JLabel("��������", JLabel.CENTER);  
        jl4.setFont(new Font("����", Font.PLAIN, 13));  
        jl4.setForeground(Color.blue);  
        jl5 = new JLabel("<html><a href='www.qq.com'>�������뱣��</a></html>");  
        jcb1 = new JCheckBox("�����¼");  
        jcb2 = new JCheckBox("��ס����");  
        jtf = new JTextField(18);  
        jpf = new JPasswordField(18);  
        jb4 = new JButton("�������");  
          
        //�в��ֻ�JPanel2  
        jp3 = new JPanel();  
        jl2a = new JLabel("�ֻ����룺", JLabel.CENTER);  
        jl3a = new JLabel("QQ�� �룺", JLabel.CENTER);  
        jl4a = new JLabel("��������", JLabel.CENTER);  
        jl4a.setFont(new Font("����", Font.PLAIN, 13));  
        jl4a.setForeground(Color.blue);  
        jl5a = new JLabel("<html><a href='www.qq.com'>�������뱣��</a></html>");  
        jcb1a = new JCheckBox("�����¼");  
        jcb2a = new JCheckBox("��ס����");  
        jtfa = new JTextField(18);  
        jpfa = new JPasswordField(18);  
        jb4a = new JButton("�������");  
          
            //�ϲ�����  
        jp1 = new JPanel();  
        jb1 = new JButton("��¼");  
        jb2 = new JButton("ȡ��");  
        jb3 = new JButton("ע����");  
          
            //���ò���  
        jp2.setLayout(new GridLayout(3, 3));  
          
          
            //������  
        jp1.add(jb1);   //�ϲ�  
        jp1.add(jb2);  
        jp1.add(jb3);  
            //�в�  
        jp2.add(jl2);     
        jp2.add(jtf);  
        jp2.add(jb4);  
        jp2.add(jl3);  
        jp2.add(jpf);  
        jp2.add(jl4);  
        jp2.add(jcb1);  
        jp2.add(jcb2);  
        jp2.add(jl5);  
          
        //���3�������񲼾�3��3��  
        jp3.setLayout(new GridLayout(3, 3));  
            /*ѡ�2,ע�⣺����ͼʡ�£�����jp2����е���ͬ����ټ���jp3����У� 
                ����jp2���е�����ͻ���ʾ��jp3�У�jp2�ͻ�ʧȥ��Ӧ�����*/  
        jp3.add(jl2a);    
        jp3.add(jtfa);  
        jp3.add(jb4a);  
          
        jp3.add(jl3a);  
        jp3.add(jpfa);  
        jp3.add(jl4a);  
          
        jp3.add(jcb1a);  
        jp3.add(jcb2a);  
        jp3.add(jl5a);  
          
        //�������ӵ�ѡ�����  
        jtp.add("QQ����", jp2);  
        jtp.add("�ֻ�����", jp3);  
        jtp.add("��������", jp4);  
          
        //���봰��  
        this.add(jl1, BorderLayout.NORTH);  // ͼƬ��ǩλ�ڱ���  
        this.add(jp1, BorderLayout.SOUTH);  //��ťλ���ϲ�  
        this.add(jtp, BorderLayout.CENTER); //ѡ�����λ���в�  
          
        this.setSize(400, 265);  
        this.setResizable(false);   //��ֹ�ı䴰���С  
        this.setTitle("QQ2014");  
        this.setVisible(true);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
  
}  