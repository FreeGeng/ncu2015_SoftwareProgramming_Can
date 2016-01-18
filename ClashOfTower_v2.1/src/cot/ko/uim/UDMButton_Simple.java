package cot.ko.uim;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


public abstract class UDMButton_Simple  extends JComponent
{
	private JLabel pressedimg;
	private JLabel releaseimg;
	private boolean clickenable;
	private buttonmouselistener buttonlistener ;
	
	public UDMButton_Simple()
	{
		buttonlistener = new buttonmouselistener();
		addMouseListener(buttonlistener);
		BaseButton();
	}
	
	public void BaseButton()
	{
		clickenable = true;
	
		releaseimg = new JLabel();
		add(releaseimg);
		releaseimg.setVisible(true);
		
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
	
	public void setClickEnable(boolean b)
	{
		clickenable = b;
	}
	
	public abstract void clicked();
	
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
			releaseimg.setVisible(false);
			pressedimg.setVisible(true);
		}
		public void mouseReleased(MouseEvent arg0) 
		{
			if(!clickenable)
				return;
			releaseimg.setVisible(true);
			pressedimg.setVisible(false);
		}
	}
}