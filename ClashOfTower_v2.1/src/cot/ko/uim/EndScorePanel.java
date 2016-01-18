package cot.ko.uim;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EndScorePanel extends JPanel
{
	JLabel client0,client1,client2,client3,
	clientNo0Score,clientNo1Score,clientNo2Score,clientNo3Score;
	
	private static EndScorePanel instance;
	
	public static EndScorePanel getInstance() 
	{
		if (instance == null)
			instance = new EndScorePanel();
		return instance;
	}
	
	public EndScorePanel()
	{
		initUI();
	}
	
	public void initUI()
	{
		this.setLayout(null);
		client0 = new JLabel();
		client0.setText("0");
		client0.setFont(new Font("SansSerif",Font.BOLD,30));
		client0.setBounds(405, 235, 30, 30);
		this.add(client0);
		
		client1 = new JLabel();
		client1.setText("1");
		client1.setFont(new Font("SansSerif",Font.BOLD,30));
		client1.setBounds(405, 325, 30, 30);
		this.add(client1);
		
		client2 = new JLabel();
		client2.setText("2");
		client2.setFont(new Font("SansSerif",Font.BOLD,30));
		client2.setBounds(405, 415, 30, 30);
		this.add(client2);
		
		client3 = new JLabel();
		client3.setText("3");
		client3.setFont(new Font("SansSerif",Font.BOLD,30));
		client3.setBounds(405, 505, 30, 30);
		this.add(client3);
		
		clientNo0Score = new JLabel();
		clientNo0Score.setText("1200");
		clientNo0Score.setFont(new Font("SansSerif",Font.BOLD,30));
		clientNo0Score.setBounds(750,235,70,30);
		this.add(clientNo0Score);
		
		clientNo1Score = new JLabel();
		clientNo1Score.setText("1400");
		clientNo1Score.setFont(new Font("SansSerif",Font.BOLD,30));
		clientNo1Score.setBounds(750,325,70,30);
		this.add(clientNo1Score);
		
		clientNo2Score = new JLabel();
		clientNo2Score.setText("1600");
		clientNo2Score.setFont(new Font("SansSerif",Font.BOLD,30));
		clientNo2Score.setBounds(750,415,70,30);
		this.add(clientNo2Score);
		
		clientNo3Score = new JLabel();
		clientNo3Score.setText("1800");
		clientNo3Score.setFont(new Font("SansSerif",Font.BOLD,30));
		clientNo3Score.setBounds(750,505,70,30);
		this.add(clientNo3Score);
	}
	
	 public void paintComponent(Graphics g) 
	 {
		 super.paintComponent(g);
	     Image image = new ImageIcon("img/endscore.png").getImage();
	     g.drawImage(image,0,0,1000,600,null);
	 }
}
