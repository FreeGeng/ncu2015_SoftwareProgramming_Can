package cof.yen.cdc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TestDrive{
	private JFrame window = null;
	private ButtonHandler bh = null;
	private JButton[] btnSet = null;
	private final int btnSize = 5;
	JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	JTextField f1, f2,f3,f4;
	private CDC cdc = null;
	
	public TestDrive(CDC cdc) {
		initUI();		
		btnSet = new JButton[btnSize];
		this.cdc = cdc;
		addPlayer();
	}
	
	private void addPlayer(){
		for(int i=0 ; i<4 ; i++){
			cdc.addPlayer(i);
		}
		cdc.startGame();
		
		add();		
		add();		
		add();		
		add();		
		add();		
		add();		
		add();		
		add();		
	}
	
	public void add(){
		cdc.addUnit("Ork", 1, 0, 1);
		cdc.addUnit("Human", 1, 0, 1);
		cdc.addUnit("Denas", 1, 0, 2);
		cdc.addUnit("Wolf", 1, 0, 2);
		
		cdc.addUnit("Ork", 1, 1, 2);
		cdc.addUnit("Human", 1, 1, 2);
		cdc.addUnit("Denas", 1, 1, 3);
		cdc.addUnit("Wolf", 1, 1, 3);

		cdc.addUnit("Ork", 1, 2, 3);
		cdc.addUnit("Human", 1, 2, 3);
		cdc.addUnit("Denas", 1, 2, 4);
		cdc.addUnit("Wolf", 1, 2, 4);

		cdc.addUnit("Ork", 1, 3, 4);
		cdc.addUnit("Human", 1, 3, 4);
		cdc.addUnit("Denas", 1, 3, 1);
		cdc.addUnit("Wolf", 1, 3, 1);
	}
	
	public void initUI(){
		this.window = new JFrame("Test");	
		this.bh = new ButtonHandler();
				
		btn1 = addBtn("addPlayer", 250, 10, 150, 30);
		btn2 = addBtn("addUnit", 250, 50, 150, 30);
		btn3 = addBtn("upgradeUnit", 250, 90, 150, 30);
		btn4 = addBtn("addMessenger", 250, 130, 150, 30);
		btn5 = addBtn("startGame", 250, 170, 150, 30);
		btn6 = addBtn("callCapitalCitySkill", 250, 210, 150, 30);
		btn7 = addBtn("callTowerSkill", 250, 250, 150, 30);
		btn8 = addBtn("callTowerUpgrade", 250, 290, 150, 30);
		btn9 = addBtn("getUpdateInfo", 250, 330, 150, 30);
		f1 = new JTextField();
		f2 = new JTextField();
		f3 = new JTextField();
		f4 = new JTextField();
		window.add(f1);
		window.add(f2);
		window.add(f3);
		window.add(f4);
		f1.setBounds(20, 10, 150, 30);
		f2.setBounds(20, 50, 150, 30);
		f3.setBounds(20, 90, 150, 30);
		f4.setBounds(20, 130, 150, 30);
		
		window.getContentPane().setLayout(null);
		window.setBounds(0, 0, 450, 500);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				
		window.setVisible(true);
	}
	
	public JButton addBtn(String title, int bx, int by, int x, int y){
		JButton myBtn = new JButton(title);		
		myBtn.addMouseListener(this.bh);
		myBtn.setBounds(bx, by, x, y);		
		this.window.add(myBtn);
		return myBtn;
	}
	
	private void out(String msg){
		System.out.println(msg);
	}
	private void out(int msg){
		System.out.println(msg);
	}
	
	private int str2int(String str){
		return Integer.parseInt(str);
	}
	
	private class ButtonHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == btn1){
				out("__________________"+btn1.getText()+"__________________");
				int s1 = str2int(f1.getText());
				cdc.addPlayer(s1);
				clear();
			}
			

			if(e.getSource() == btn2){
				out("__________________"+btn2.getText()+"__________________");
				String s1 = f1.getText();
				int s2 = str2int(f2.getText());
				int s3 = str2int(f3.getText());
				int s4 = str2int(f4.getText());
				cdc.addUnit(s1, s2, s3, s4);
				clear();
				
				// addUnit(String type, int level, int clientno, int aisle)
			}
			
			

			if(e.getSource() == btn3){
				out("__________________"+btn3.getText()+"__________________");

				int s1 = str2int(f1.getText());
				int s2 = str2int(f2.getText());
				cdc.upgradeUnit(s1, s2);
				clear();
				
				//upgradeUnit(int clientNo, int level)
			}
			
			

			if(e.getSource() == btn4){
				out("__________________"+btn4.getText()+"__________________");

				int s1 = str2int(f1.getText());
				cdc.addMessenger(s1);
				clear();
				
				//addMessenger(int clientNo)
			}
			
			

			if(e.getSource() == btn5){
				out("__________________"+btn5.getText()+"__________________");
				cdc.startGame();
				clear();
			}
			

			if(e.getSource() == btn6){
				out("__________________"+btn6.getText()+"__________________");

				int s1 = str2int(f1.getText());
				String s2 = f2.getText();
				cdc.callCapitalCitySkill(s1, s2);
				clear();
				
				//callCapitalCitySkill (int clientNo, String skillName)
			}
			

			if(e.getSource() == btn7){
				out("__________________"+btn7.getText()+"__________________");

				int s1 = str2int(f1.getText());
				cdc.callTowerSkill(s1);
				clear();
				//callTowerSkill (int clientNo)
			}
			

			if(e.getSource() == btn8){

				out("__________________"+btn8.getText()+"__________________");

				int s1 = str2int(f1.getText());
				String s2 = f2.getText();
				cdc.callTowerUpgrade(s1, s2);

				clear();
				//callTowerUpgrade (int clientNo, String updateName)
			}
				
			

			if(e.getSource() == btn9){

				
				out("__________________"+btn9.getText()+"__________________");
				
				ArrayList<String> result=  cdc.getUpdateInfo();
				for(int i=0 ; i<result.size() ; i++)
					out(result.get(i));

				clear();
				//getUpdateInfo()}
			
			
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		}
	
	private void clear(){
		f1.setText("");
		f2.setText("");
		f3.setText("");
		f4.setText("");
	}
}
