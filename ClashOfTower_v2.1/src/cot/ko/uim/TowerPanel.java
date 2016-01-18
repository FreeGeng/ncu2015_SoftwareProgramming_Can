package cot.ko.uim;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TowerPanel extends JPanel
{
	public static TowerPanel getInstance()
	{
		return instance;
	}
	
	private static TowerPanel instance = new TowerPanel();
	
	private Image 			backgroundImg;
	
	private TowerRecoverButton 	trb;
	private MoneyUpgradeButton 	mub;
	private AtkUpgradeButton	aub;
	private JLabel				moneylv, atklv;
	
	public TowerPanel() 
	{
		// set Panel Background
		backgroundImg = new ImageIcon("res/background/unit_panel.png").getImage();
		Dimension size = new Dimension(backgroundImg.getWidth(null), backgroundImg.getHeight(null));
	    this.setPreferredSize(size);
	    this.setMinimumSize(size);
	    this.setMaximumSize(size);
	    this.setSize(size);
		this.setLayout(null);
		this.setVisible(false);
		
		// set all button & label
		trb = TowerRecoverButton.getInstance();
		trb.setPressedimg("res/button/upgrade_ok.png");
		trb.setReleaseimg("res/button/tower_recover_button.png");
		trb.setCosttext("500");
		trb.setBounds(150, 4, 111, 142);
		
		
		mub = MoneyUpgradeButton.getInstance();
		mub.setTowerPanel(this);
		mub.setPressedimg("res/button/upgrade_ok.png");
		mub.setReleaseimg("res/button/tower_moneyup_button.png");
		mub.setCosttext("500");
		mub.setBounds(450, 4, 111, 142);
		
		moneylv = new JLabel();
		moneylv.setBounds(420, 10, 26, 123);
		
		aub = AtkUpgradeButton.getInstance();
		aub.setTowerPanel(this);
		aub.setPressedimg("res/button/upgrade_ok.png");
		aub.setReleaseimg("res/button/tower_atkup_button.png");
		aub.setCosttext("500");
		aub.setBounds(750, 4, 111, 142);
		
		atklv = new JLabel();
		atklv.setBounds(720, 10, 26, 123);
		
		this.add(trb);
		this.add(mub);
		this.add(aub);
		this.add(moneylv);
		this.add(atklv);
	}
	
	public void setMoneyLevel(int currentlevel)
	{
		switch (currentlevel) 
		{
			case 0:
				break;
			case 1:
				moneylv.setIcon(new ImageIcon("res/icon/lv1.png"));
				break;
			case 2:
				moneylv.setIcon(new ImageIcon("res/icon/lv2.png"));
				break;
			case 3:
				moneylv.setIcon(new ImageIcon("res/icon/lv3.png"));
				break;
			case 4:
				moneylv.setIcon(new ImageIcon("res/icon/lv4.png"));
				break;
			default:
				break;
		}
	}
	
	public void setAtkLevel(int currentlevel)
	{
		switch (currentlevel) 
		{
			case 0:
				break;
			case 1:
				atklv.setIcon(new ImageIcon("res/icon/lv1.png"));
				break;
			case 2:
				atklv.setIcon(new ImageIcon("res/icon/lv2.png"));
				break;
			case 3:
				atklv.setIcon(new ImageIcon("res/icon/lv3.png"));
				break;
			case 4:
				atklv.setIcon(new ImageIcon("res/icon/lv4.png"));
				break;
			default:
				break;
		}
	}
	
	public void paintComponent(Graphics g) 
	{
		g.drawImage(backgroundImg, 0, 0, null);
	}
}
