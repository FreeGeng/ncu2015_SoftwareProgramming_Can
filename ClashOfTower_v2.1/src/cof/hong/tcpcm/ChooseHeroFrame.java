package cof.hong.tcpcm;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cot.ko.uim.InformationCenter;

//此class開啟選擇英雄的視窗
public class ChooseHeroFrame {
	JFrame chooseHeroFrame;
	Container frameContentPane;
	JPanel framePanel;
	JButton heroButton1,heroButton2,heroButton3,heroButton4,startGameButton,temp;
	ButtonHandler btnHandler;
	JLabel heroBackgroundJLabel;
	ImageIcon heroBackgroundImg;
	public TcpClient tcpClient = null;
	String heroName = null;
	private InformationCenter ic = InformationCenter.getInstance();
	private boolean isChoose = false;
	
	public ChooseHeroFrame(TcpClient tcpClient){
		this.tcpClient = tcpClient;
		initUI();
	}
	
	public void initUI(){
		chooseHeroFrame = new JFrame("Choose hero");
		frameContentPane = chooseHeroFrame.getContentPane();
		frameContentPane.setLayout(null);
		
		framePanel = new JPanel();
		framePanel.setLayout(null);
		framePanel.setBounds(0,0,450,300);
    	frameContentPane.add(framePanel);
		
		btnHandler = new ButtonHandler();
		heroButton1 = new JButton(new ImageIcon("img/hero1.png"));
		heroButton1.setBounds(20, 25, 58, 86);
		heroButton1.addMouseListener(btnHandler);;
		framePanel.add(heroButton1);
		
		heroButton2 = new JButton(new ImageIcon("img/hero2.png"));
		heroButton2.setBounds(20, 130, 58, 86);
		heroButton2.addMouseListener(btnHandler);
		framePanel.add(heroButton2);
		
		heroButton3 = new JButton(new ImageIcon("img/hero3.png"));
		heroButton3.setBounds(120, 25, 58, 86);
		heroButton3.addMouseListener(btnHandler);
		framePanel.add(heroButton3);
		
		heroButton4 = new JButton(new ImageIcon("img/hero4.png"));
		heroButton4.setBounds(120, 130, 58, 86);
		heroButton4.addMouseListener(btnHandler);
		framePanel.add(heroButton4);
		
		startGameButton = new JButton(new ImageIcon("img/start.png"));
		startGameButton.setBounds(280,190,126,48);
		startGameButton.addMouseListener(btnHandler);
		framePanel.add(startGameButton);
		startGameButton.setEnabled(false);
		
		temp = new JButton();
		framePanel.add(temp);
		
		chooseHeroFrame.getContentPane().setLayout(null);
		chooseHeroFrame.setBounds(0, 0, 450, 300);
		chooseHeroFrame.setResizable(false);
		chooseHeroFrame.setLocationRelativeTo(null);
		chooseHeroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		heroBackgroundJLabel = new JLabel();
		heroBackgroundImg = new ImageIcon("img/herobackground.png");
		heroBackgroundJLabel.setIcon(heroBackgroundImg);
		heroBackgroundJLabel.setBounds(0, 0, 450, 300);
		framePanel.add(heroBackgroundJLabel);
		
		chooseHeroFrame.setVisible(true);
	}
	
	private class ButtonHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == heroButton1){
				heroButton1.setIcon(new ImageIcon("img/hero1_check.png"));
				heroButton2.setIcon(new ImageIcon("img/hero2.png"));
				heroButton3.setIcon(new ImageIcon("img/hero3.png"));
				heroButton4.setIcon(new ImageIcon("img/hero4.png"));
				heroName = "Gerald";
				startGameButton.setEnabled(true);
				isChoose = true;
			}else if(e.getSource() == heroButton2){
				heroButton1.setIcon(new ImageIcon("img/hero1.png"));
				heroButton2.setIcon(new ImageIcon("img/hero2_check.png"));
				heroButton3.setIcon(new ImageIcon("img/hero3.png"));
				heroButton4.setIcon(new ImageIcon("img/hero4.png"));
				heroName = "Denas";
				startGameButton.setEnabled(true);
				isChoose = true;
			}else if(e.getSource() == heroButton3){
				heroButton1.setIcon(new ImageIcon("img/hero1.png"));
				heroButton2.setIcon(new ImageIcon("img/hero2.png"));
				heroButton3.setIcon(new ImageIcon("img/hero3_check.png"));
				heroButton4.setIcon(new ImageIcon("img/hero4.png"));
				heroName = "Mailk";
				startGameButton.setEnabled(true);
				isChoose = true;
			}else if(e.getSource() == heroButton4){
				heroButton1.setIcon(new ImageIcon("img/hero1.png"));
				heroButton2.setIcon(new ImageIcon("img/hero2.png"));
				heroButton3.setIcon(new ImageIcon("img/hero3.png"));
				heroButton4.setIcon(new ImageIcon("img/hero4_check.png"));
				heroName = "Ingvar";
				startGameButton.setEnabled(true);
				isChoose = true;
			}else{
				try {
					if(isChoose){
						tcpClient.setHeroName(heroName);
						tcpClient.writeMsgToServer("HAVE_STARTED?");
						startGameButton.setEnabled(false);
						isChoose = false;
					}
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() == heroButton1){
				temp.setIcon(new ImageIcon("img/hero1_info.png"));
				temp.setBounds(220, 0, 199, 175);
			}else if(e.getSource() == heroButton2){
				temp.setIcon(new ImageIcon("img/hero2_info.png"));
				temp.setBounds(220, 0, 199, 175);
			}else if(e.getSource() == heroButton3){
				temp.setIcon(new ImageIcon("img/hero3_info.png"));
				temp.setBounds(220, 0, 199, 175);
			}else if(e.getSource() == heroButton4){
				temp.setIcon(new ImageIcon("img/hero4_info.png"));
				temp.setBounds(220, 0, 199, 175);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() == heroButton1){
				temp.setIcon(new ImageIcon());
				temp.setBounds(0, 0, 0, 0);
			}else if(e.getSource() == heroButton2){
				temp.setIcon(new ImageIcon());
				temp.setBounds(0, 0, 0, 0);
			}else if(e.getSource() == heroButton3){
				temp.setIcon(new ImageIcon());
				temp.setBounds(0, 0, 0, 0);
			}else if(e.getSource() == heroButton4){
				temp.setIcon(new ImageIcon());
				temp.setBounds(0, 0, 0, 0);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
}
