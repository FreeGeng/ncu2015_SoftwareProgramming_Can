package cot.ko.uim;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cof.hong.tcpcm.TcpClient;
import cot.ko.dom.RenderThread;
import cot.qin.sre.SceneRenderEngine;


public class HeroButton extends JComponent
{
	public static HeroButton getInstance()
	{
		return instance;
	}
	
	private static HeroButton instance = new HeroButton();
		
	private JLabel pressedimg;
	private JLabel releaseimg;
	private JLabel costtext1;
	private JLabel costtext2;
	private boolean clickenable;
	private boolean isReleased = true;
	private buttonmouselistener buttonlistener ;
	
	private String HeroName;
	
	private SceneRenderEngine 	sre;
	private InformationCenter	ic;
	private TcpClient			tc;
	
	public HeroButton()
	{
		this.sre = SceneRenderEngine.getInstance();
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
		
		costtext1 = new JLabel();
		costtext1.setBounds(85, 75, 50, 20);
		costtext1.setForeground(Color.ORANGE);
		add(costtext1);
		costtext1.setVisible(true);
	
		releaseimg = new JLabel();
		add(releaseimg);
		releaseimg.setVisible(true);
		
		costtext2 = new JLabel();
		costtext2.setBounds(85, 86, 50, 20);
		costtext2.setForeground(Color.ORANGE);
		add(costtext2);
		
		pressedimg = new JLabel();
		add(pressedimg);
	}
	
	public void setPressedimg(String img)
	{
		ImageIcon icon = new ImageIcon(img);
		pressedimg.setSize(icon.getIconWidth(),icon.getIconHeight());
		pressedimg.setIcon(icon);
		pressedimg.setVisible(false);
	}		
	public void setReleaseimg(String img)
	{
		ImageIcon icon = new ImageIcon(img);
		releaseimg.setSize(icon.getIconWidth(),icon.getIconHeight());
		releaseimg.setIcon(icon);
	}
	
	public void setCosttext(String cost)
	{
		costtext1.setText(cost);
		costtext2.setText(cost);
	}
	
	public void setClickEnable(boolean b)
	{
		clickenable = b;
	}
	
	public boolean getIsReleased()
	{
		return isReleased;
	}
	
	public void setInformationCenter(InformationCenter ic)
	{
		this.ic = ic;
	}
	
	public void clicked()
	{
		JOptionPane.showMessageDialog(null, "Hero!!!!");
	}
	public void pressed()
	{
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("res/cursor/cursor_" + HeroName + ".png").getImage(),
				new Point(16,16),"warrior cursor"));
	}
	public void released(int x, int y)
	{
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		int roadNumber = sre.onRoad( new int[]{(x+670), (y+460)} );
		
		if(roadNumber != 0)
		{
			try {
				tc.addUnit(ic.getHeroName(), 0, ic.getPlayerNumber(), roadNumber);
				ic.setHeroState(ic.getPlayerNumber(),true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void close()
	{
		removeMouseListener(buttonlistener);
		buttonlistener = null;
		
		remove(pressedimg);
		pressedimg = null;
		
		remove(releaseimg);
		releaseimg = null;
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
			
		}
		public void mouseExited(MouseEvent arg0) 
		{
			
		}
		public void mousePressed(MouseEvent arg0) 
		{
			if(!clickenable)
				return;
			
			isReleased = false;
			releaseimg.setVisible(false);
			costtext1.setVisible(false);
			pressedimg.setVisible(true);
			costtext2.setVisible(true);
			pressed();
		}
		
		public void mouseReleased(MouseEvent arg0) 
		{
			if(!clickenable)
				return;
			
			isReleased = true;
			releaseimg.setVisible(true);
			costtext1.setVisible(true);
			pressedimg.setVisible(false);
			costtext2.setVisible(false);
			released(arg0.getX(), arg0.getY());
		}
	}
	
	public void setName(String name)
	{
		this.HeroName = name;
	}
	
	public void setButtonLock()
	{
		this.setClickEnable(false);
		this.setPressedimg("res/button/"+ HeroName +"_button_locked.png");
		this.setReleaseimg("res/button/"+ HeroName +"_button_locked.png");
	}
	
	public void setButtonUnlock()
	{
		this.setClickEnable(true);
		this.setPressedimg("res/button/"+ HeroName +"_button_pressed.png");
		this.setReleaseimg("res/button/"+ HeroName +"_button.png");
	}
}