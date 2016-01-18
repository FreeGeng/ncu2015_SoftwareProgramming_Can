package cot.ko.uim;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import cof.hong.tcpcm.TcpClient;


public class TowerFireButton extends JComponent
{
	public static TowerFireButton getInstance()
	{
		return instance;
	}
	
	private static TowerFireButton instance = new TowerFireButton();
	
	private JLabel maskimg;
	private JLabel enterimg;
	private JLabel releaseimg;
	private boolean clickenable;
	private buttonmouselistener buttonlistener ;
	
	private InformationCenter 	ic;
	private TcpClient			tc;
	
	public TowerFireButton ()
	{
		try {
			this.tc = TcpClient.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buttonlistener = new buttonmouselistener();
		addMouseListener(buttonlistener);
		BaseButton();
	}
	
	public void BaseButton()
	{
		clickenable = true;
		this.setLayout(null);
		
		maskimg = new JLabel();
		add(maskimg);
		maskimg.setVisible(true);
	
		releaseimg = new JLabel();
		add(releaseimg);
		releaseimg.setVisible(true);
		
		enterimg = new JLabel();
		add(enterimg);
		enterimg.setVisible(false);
	}
	
	public void setMaskimg(String img)
	{
		ImageIcon icon = new ImageIcon(img);
		maskimg.setSize(icon.getIconWidth(), icon.getIconHeight());
		maskimg.setVerticalAlignment(SwingConstants.TOP);
		maskimg.setIcon(icon);
	}		
	
	public void setEnterimg(String img)
	{
		ImageIcon icon = new ImageIcon(img);
		enterimg.setSize(icon.getIconWidth(),icon.getIconHeight());
		enterimg.setIcon(icon);
	}
	
	public void setReleaseimg(String img)
	{
		ImageIcon icon = new ImageIcon(img);
		releaseimg.setSize(icon.getIconWidth(),icon.getIconHeight());
		releaseimg.setIcon(icon);
	}
	
	public void setClickEnable(boolean b)
	{
		clickenable = b;
	}
	
	public void clicked()
	{
		try {
			tc.callTowerSkill(ic.getPlayerNumber());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resetProgress();
		ic.startCoolDownTimer();
	}
	
	public void close()
	{
		removeMouseListener(buttonlistener);
		buttonlistener = null;
		
		remove(enterimg);
		enterimg = null;
		
		remove(releaseimg);
		releaseimg = null;
	}
	
	public void setProgress(int time)
	{
		if(time > 0)
		{
			maskimg.setSize(121, time);
		}
		else 
		{
			maskimg.setVisible(false);
			this.setClickEnable(true);
		}
	}
	
	public void resetProgress()
	{
		this.setClickEnable(false);
		maskimg.setBounds(0, 0, 121, 133);
		maskimg.setVisible(true);
		releaseimg.setVisible(true);
		enterimg.setVisible(false);
	}
	
	public void setInformationCenter(InformationCenter ic)
	{
		this.ic = ic;
	}
	
	private class buttonmouselistener implements MouseListener
	{

		public void mouseClicked(MouseEvent arg0) 
		{
			if(!clickenable)
				return;
			clicked();
		}
		public void mouseEntered(MouseEvent arg0)
		{
			if(!clickenable)
				return;
			releaseimg.setVisible(false);
			enterimg.setVisible(true);
		}
		public void mouseExited(MouseEvent arg0) 
		{
			if(!clickenable)
				return;
			releaseimg.setVisible(true);
			enterimg.setVisible(false);
		}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
}
