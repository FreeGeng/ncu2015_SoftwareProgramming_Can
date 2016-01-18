package cot.ko.uim;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UnitStatePanel extends JPanel
{
	public static UnitStatePanel getInstance()
	{
		return instance;
	}
	
	private static UnitStatePanel instance = new UnitStatePanel();
	
	private Image 				backgroundImg;
		
	private JLabel				level1, upcost1, slider1,
								level2, upcost2, slider2,
								level3, upcost3, slider3,
								level4; 
	
	private CancelButton		cb;	
	private UnitUpgradeButton 	uub;
	
	public UnitStatePanel() 
	{
		// set Panel Background
		backgroundImg = new ImageIcon("res/background/unitstate_background.png").getImage();
		Dimension size = new Dimension(backgroundImg.getWidth(null), backgroundImg.getHeight(null));
		this.setPreferredSize(size);
		this.setMinimumSize(size);
		this.setMaximumSize(size);
		this.setSize(size);
		this.setOpaque(false);
		this.setLayout(null);
		this.setVisible(false);
		
		// set all label
		level1 = new JLabel();
		level1.setBounds(30, 100, 33, 46);
		
		slider1 = new JLabel();
		slider1.setBounds(70, 110, 39, 31);
		
		upcost1 = new JLabel();
		upcost1.setText("500");
		upcost1.setForeground(Color.RED);
		upcost1.setBounds(75, 140, 50, 20);
		
		level2 = new JLabel();
		level2.setBounds(120, 100, 33, 45);
		
		slider2 = new JLabel();
		slider2.setBounds(160, 110, 39, 31);
		
		upcost2 = new JLabel();
		upcost2.setText("800");
		upcost2.setForeground(Color.RED);
		upcost2.setBounds(165, 140, 50, 20);
		
		level3 = new JLabel();
		level3.setBounds(205, 100, 37, 46);
		
		slider3 = new JLabel();
		slider3.setBounds(250, 110, 39, 31);
		
		upcost3 = new JLabel();
		upcost3.setText("1000");
		upcost3.setForeground(Color.RED);
		upcost3.setBounds(255, 140, 50, 20);
		
		level4 = new JLabel();
		level4.setBounds(295, 90, 57, 57);
		
		
		// set all button
		cb = new CancelButton(this);
		cb.setPressedimg("res/button/cancel_button.png");
		cb.setReleaseimg("res/button/cancel_button.png");
		cb.setBounds(365, 0, 35, 35);
		
		uub = UnitUpgradeButton.getInstance();
		uub.setUnitStatePanel(this);
		uub.setPressedimg("res/button/upgrade_button_pressed.png");
		uub.setReleaseimg("res/button/upgrade_button.png");
		uub.setBounds(100, 190, 190, 49);
		
		
		this.add(cb);
		this.add(uub);
		this.add(level1);
		this.add(slider1);
		this.add(upcost1);
		this.add(level2);
		this.add(slider2);
		this.add(upcost2);
		this.add(level3);
		this.add(slider3);
		this.add(upcost3);
		this.add(level4);
	}
	
	public void setState(String type, int currentlevel)
	{
		switch (type) 
		{
			case "Human":
				level1.setIcon(new ImageIcon("res/icon/h1.png"));
				level2.setIcon(new ImageIcon("res/icon/h2.png"));
				level3.setIcon(new ImageIcon("res/icon/h3.png"));
				level4.setIcon(new ImageIcon("res/icon/h4.png"));
				break;
			case "Wolf":
				level1.setIcon(new ImageIcon("res/icon/w1.png"));
				level2.setIcon(new ImageIcon("res/icon/w2.png"));
				level3.setIcon(new ImageIcon("res/icon/w3.png"));
				level4.setIcon(new ImageIcon("res/icon/w4.png"));
				break;
			case "Ork":
				level1.setIcon(new ImageIcon("res/icon/o1.png"));
				level2.setIcon(new ImageIcon("res/icon/o2.png"));
				level3.setIcon(new ImageIcon("res/icon/o3.png"));
				level4.setIcon(new ImageIcon("res/icon/o4.png"));
				break;
			default:
				break;
		}
		
		switch (currentlevel) 
		{
			case 0:
				slider1.setIcon(new ImageIcon("res/icon/light_sliderRight.gif"));
				slider2.setIcon(new ImageIcon("res/icon/grey_sliderRight.png"));
				slider3.setIcon(new ImageIcon("res/icon/grey_sliderRight.png"));
				break;
			case 1:
				slider1.setIcon(new ImageIcon("res/icon/blue_sliderRight.png"));
				slider2.setIcon(new ImageIcon("res/icon/light_sliderRight.gif"));
				slider3.setIcon(new ImageIcon("res/icon/grey_sliderRight.png"));
				break;
			case 2:
				slider1.setIcon(new ImageIcon("res/icon/blue_sliderRight.png"));
				slider2.setIcon(new ImageIcon("res/icon/blue_sliderRight.png"));
				slider3.setIcon(new ImageIcon("res/icon/light_sliderRight.gif"));
				break;
			case 3:
				slider1.setIcon(new ImageIcon("res/icon/blue_sliderRight.png"));
				slider2.setIcon(new ImageIcon("res/icon/blue_sliderRight.png"));
				slider3.setIcon(new ImageIcon("res/icon/blue_sliderRight.png"));
				break;
			default:
				break;
		}
		
		if(currentlevel < 3)
		{
			uub.setClickEnable(true);
			uub.setPressedimg("res/button/upgrade_button_pressed.png");
			uub.setReleaseimg("res/button/upgrade_button.png");
		}
		else 
		{
			uub.setClickEnable(false);
			uub.setPressedimg("res/button/upgrade_button_max.png");
			uub.setReleaseimg("res/button/upgrade_button_max.png");
		}
		
		uub.setType(type);
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g) 
	{
		g.drawImage(backgroundImg, 0, 0, null);
	}
}
