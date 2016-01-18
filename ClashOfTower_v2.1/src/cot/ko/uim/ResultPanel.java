package cot.ko.uim;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ResultPanel extends JPanel
{
	private Image 			backgroundImg;
	private ScrollButton	sb;
	
	public ResultPanel(MainFrame mf)
	{
		// set Panel Background
		Dimension size = new Dimension(1000, 600);
	    this.setPreferredSize(size);
	    this.setMinimumSize(size);
	    this.setMaximumSize(size);
	    this.setSize(size);
		this.setLayout(null);
		this.setVisible(false);
		
		// set all button
		sb = new ScrollButton(mf);
		sb.setBounds(300, 50, 390, 500);
		
		this.add(sb);
	}
	
	public void setBackground(String img)
	{
		backgroundImg = new ImageIcon("res/background/"+img+"_background.png").getImage();
		repaint();
	}
	
	public void setScroll(int event)
	{
		switch (event) 
		{
			case 0:
				sb.setEnterimg("res/button/scroll_harvest_enter.png");
				sb.setReleaseimg("res/button/scroll_harvest.png");
				sb.setEvent("church1");
				break;
			case 1:
				sb.setEnterimg("res/button/scroll_tornado_enter.png");
				sb.setReleaseimg("res/button/scroll_tornado.png");
				sb.setEvent("church3");
				break;
			case 2:
				sb.setEnterimg("res/button/scroll_meteorite_enter.png");
				sb.setReleaseimg("res/button/scroll_meteorite.png");
				sb.setEvent("church2");
				break;
			case 3:
				sb.setEnterimg("res/button/scroll_build_enter.png");
				sb.setReleaseimg("res/button/scroll_build.png");
				sb.setEvent("castle1");
				break;
			case 4:
				sb.setEnterimg("res/button/scroll_tax_enter.png");
				sb.setReleaseimg("res/button/scroll_tax.png");
				sb.setEvent("castle2");
				break;
			case 5:
				sb.setEnterimg("res/button/scroll_atkup_enter.png");
				sb.setReleaseimg("res/button/scroll_atkup.png");
				sb.setEvent("castle3");
				break;
			default:
				break;
		}
	}
	
	public void paintComponent(Graphics g) 
	{
		if(backgroundImg != null)
			g.drawImage(backgroundImg, 0, 0, null);
	}
}
