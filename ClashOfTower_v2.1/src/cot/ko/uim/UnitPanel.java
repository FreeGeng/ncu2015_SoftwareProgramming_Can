package cot.ko.uim;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class UnitPanel extends JPanel
{
	private Image 			backgroundImg;
	
	private MessengerButton mb;
	private HumanButton 	hb;
	private WolfButton		wb;
	private OrkButton		ob;
	private HeroButton		herob;
	private TowerFireButton	tfb;
	
	
	public UnitPanel(String HeroName) 
	{
		
		// set Panel Background
		backgroundImg = new ImageIcon("res/background/unit_panel.png").getImage();
		Dimension size = new Dimension(backgroundImg.getWidth(null), backgroundImg.getHeight(null));
	    this.setPreferredSize(size);
	    this.setMinimumSize(size);
	    this.setMaximumSize(size);
	    this.setSize(size);
		this.setLayout(null);
		
		// set all button
		mb = MessengerButton.getInstance();
		mb.setCosttext("500");
		mb.setPressedimg("res/button/messenger_button_pressed.png");
		mb.setReleaseimg("res/button/messenger_button.png");
		mb.setBounds(30, 10, 119, 130);
		
		hb = HumanButton.getInstance();
		hb.setCosttext("100");
		hb.setPressedimg("res/button/warrior_button_pressed.png");
		hb.setReleaseimg("res/button/warrior_button.png");
		hb.setBounds(190, 10, 119, 130);
		
		wb = WolfButton.getInstance();
		wb.setCosttext("100");
		wb.setPressedimg("res/button/wolf_button_pressed.png");
		wb.setReleaseimg("res/button/wolf_button.png");
		wb.setBounds(350, 10, 119, 130);
		
		ob = OrkButton.getInstance();
		ob.setCosttext("150");
		ob.setPressedimg("res/button/ork_button_pressed.png");
		ob.setReleaseimg("res/button/ork_button.png");
		ob.setBounds(510, 10, 119, 130);
		
		herob = HeroButton.getInstance();
		herob.setCosttext("800");
		herob.setPressedimg("res/button/"+ HeroName +"_button_pressed.png");
		herob.setReleaseimg("res/button/"+ HeroName +"_button.png");
		herob.setName(HeroName);
		herob.setBounds(670, 10, 119, 130);
		
		tfb = TowerFireButton.getInstance();
		tfb.setMaskimg("res/button/sword_sliver_mask.png");
		tfb.setEnterimg("res/button/sword_gold_button_enter.png");
		tfb.setReleaseimg("res/button/sword_gold_button.png");
		tfb.setClickEnable(false);
		tfb.setBounds(830, 8, 121, 133);
		
		this.add(mb);
		this.add(hb);
		this.add(wb);
		this.add(ob);
		this.add(herob);
		this.add(tfb);
	}
	
	public void paintComponent(Graphics g) 
	{
		g.drawImage(backgroundImg, 0, 0, null);
	}
}
