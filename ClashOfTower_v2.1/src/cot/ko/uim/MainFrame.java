package cot.ko.uim;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame
{
	private JPanel					container;
	private MainPanel 				mp;
	private CapitalCitySystemPanel 	ccsp;
	private ResultPanel				rp;
	private EndScorePanel			esp;
	private CardLayout				cl;
	private InformationCenter		ic;
	
	public static MainFrame getInstance() {
		return instance;
	}

	private static  MainFrame instance = new  MainFrame();
	
	public MainFrame()
	{
		ic = InformationCenter.getInstance();
		
		// set frame
		this.setTitle("Clash Of Tower");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		// create the panel container
		cl = new CardLayout();
		container = new JPanel();
		container.setLayout(cl);
		
		//create all panel
		mp = new MainPanel(this, ic.getHeroName());
		ccsp = new CapitalCitySystemPanel(this);
		rp = new ResultPanel(this);
		esp = EndScorePanel.getInstance();
		
		// add all into container
		container.add(mp,"MainPanel");
		container.add(ccsp,"CapitalCitySystemPanel");
		container.add(rp, "ResultPanel");
		container.add(esp, "EndScorePanel");
		
		//add container on frame
		this.add(container);
		
		this.pack();//Size the frame.
		
		// set frame
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void ChangeScreen(String screen)
	{
		if( screen.equals("CapitalCitySystem") )
		{
			cl.show(container, "CapitalCitySystemPanel");
		}
		else if( screen.equals("ShowChurch") )
		{
			rp.setBackground("church");
			Random ran = new Random();
			rp.setScroll( ran.nextInt(3) );
			cl.show(container, "ResultPanel");
		}
		else if( screen.equals("ShowKing") )
		{
			rp.setBackground("king");
			Random ran = new Random();
			rp.setScroll( ran.nextInt(3) + 3 );
			cl.show(container, "ResultPanel");
		}
		else if( screen.equals("Main") )
		{
			cl.show(container, "MainPanel");
			mp.setFocusable(true);
		}
		else if( screen.equals("GameOver") )
		{
			cl.show(container, "EndScorePanel");
		}
	}
}
