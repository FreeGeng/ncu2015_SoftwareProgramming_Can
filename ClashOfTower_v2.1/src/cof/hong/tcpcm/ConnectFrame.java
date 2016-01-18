package cof.hong.tcpcm;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//此class開啟連線的視窗
public class ConnectFrame {
	JFrame connectFrame;
	JTextField connectTextField;
	JLabel connectBackground,connecTextArea;
	
	public ConnectFrame(){
		initUI();
	}
	
	public void initUI(){
		connectFrame = new JFrame("Connect to server");
		connectFrame.getContentPane().setLayout(null);
		connectFrame.setBounds(0,0,565,365);
		connectFrame.setResizable(false);
		connectFrame.setLocationRelativeTo(null);
		connectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		connecTextArea = new JLabel("Waiting for connecting server...");
		connecTextArea.setFont(new Font("SansSerif",Font.BOLD,18));
		connecTextArea.setBounds(250, 20, 300, 28);
		connectFrame.add(connecTextArea);
		
		connectBackground = new JLabel();
		connectBackground.setIcon(new ImageIcon("img/connectbackground.png"));
		connectBackground.setBounds(0, 0, 565, 340);
		connectFrame.add(connectBackground);
		
		connectFrame.setVisible(true);
	}
	
	public void connectSuccess(){
		connecTextArea.setText("");
		connectBackground.setIcon(new ImageIcon("img/connect_success.png"));
	}
}
