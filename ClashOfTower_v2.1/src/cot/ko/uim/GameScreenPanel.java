package cot.ko.uim;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cot.ko.effectre.EffectRenderEngine;
import cot.ko.spritere.SpriteRenderEngine;
import cot.ko.towerre.TowerRenderEngine;
import cot.qin.sdm.SceneDataModule;
import cot.qin.sre.SceneRenderEngine;

public class GameScreenPanel extends JPanel
{
	private SceneRenderEngine sre = SceneRenderEngine.getInstance();
	private SceneDataModule sdm = SceneDataModule.getInstance();
	private SpriteRenderEngine spritere = SpriteRenderEngine.getInstance();
	private EffectRenderEngine effectre = EffectRenderEngine.getInstance();
	private TowerRenderEngine towerre = TowerRenderEngine.getInstance();
	
	private SwitchUpgradeButton sub;
	private MailButton			mb;
	private JLabel				playerstateLabel;
	private GameTimeText		gtt;
	private PlayerMoneyText		pmt;
	private TowerHealthText		tht;
	private TotalUnitText		tut;
	private MainFrame			mf;
	private MainPanel			mp;
	
	private static GameScreenPanel instance;
	
	public static GameScreenPanel getInstance(MainFrame mf, MainPanel mp) 
	{
		if (instance == null)
			instance = new GameScreenPanel(mf, mp);
		return instance;
	}
	
	public static GameScreenPanel getInstanceRT() 
	{
		return instance;
	}
	
	public GameScreenPanel(MainFrame mf, MainPanel mp) 
	{
		this.mf = mf;
		this.mp = mp;
		
		Dimension size = new Dimension(1000, 450);
	    this.setPreferredSize(size);
	    this.setMinimumSize(size);
	    this.setMaximumSize(size);
	    this.setSize(size);
		this.setLayout(null);
		
		sub = new SwitchUpgradeButton(this.mp);
		sub.setPressedimg("res/button/tower_upgrade_button.png");
		sub.setReleaseimg("res/button/tower_upgrade_button.png");
		sub.setBounds(15, 10, 50, 50);
		
		mb = MailButton.getInstance();
		mb.setMainFrame(this.mf);
		mb.setPressedimg("res/button/mail_button_pressed.png");
		mb.setReleaseimg("res/button/mail_button.png");
		mb.setBounds(10, 70, 62, 50);
		mb.setVisible(false);
		
		playerstateLabel = new JLabel();
		playerstateLabel.setIcon(new ImageIcon("res/background/player_state.png"));
		playerstateLabel.setBounds(760, 10, 218, 80);
		
		tht = TowerHealthText.getInstance();
		tht.setText("1000");
		tht.setForeground(Color.YELLOW);
		tht.setBounds(820, 8, 50, 50);
		
		pmt = PlayerMoneyText.getInstance();
		pmt.setText("1000");
		pmt.setForeground(Color.YELLOW);
		pmt.setBounds(895, 8, 50, 50);
		
		tut = TotalUnitText.getInstance();
		tut.setText("0");
		tut.setForeground(Color.YELLOW);
		tut.setBounds(862, 56, 30, 30);
		
		gtt = GameTimeText.getInstance();
		gtt.setText("10:00");
		gtt.setFont(new Font("Serif", Font.BOLD, 30));
		gtt.setBounds(400, 5, 100, 50);
		
		this.add(sub);
		this.add(mb);
		this.add(gtt);
		this.add(tht);
		this.add(pmt);
		this.add(tut);
		this.add(playerstateLabel);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int[] information = sre.getWindowInfo();
		// int[] information =
		// {zeroX,zeroY,X,Y,windowSize,nextColumn[0],nextColumn[1],nextRow[0],nextRow[1],imageSizeX,imageSizeY};
		int theX = information[0], theY = information[1];
		for (int y = information[3]; y < information[3] + information[4]; y++) {
			for (int x = information[2]; x < information[2] + information[4]; x++) {
				ImageIcon test = new ImageIcon(sdm.getMap().getBlockImage(x, y));
				Image img = test.getImage();
				g.drawImage(img, theX + (x - information[2]) * information[5],
						theY + (x - information[2]) * information[6], information[9], information[10], this);
			}
			theX = information[0] + (y - information[3]) * information[7];
			theY = information[1] + (y - information[3]) * information[8];
		}
		
		towerre.renderSprites(g);
		spritere.renderSprites(g);
		effectre.renderSprites(g);
	}
}
