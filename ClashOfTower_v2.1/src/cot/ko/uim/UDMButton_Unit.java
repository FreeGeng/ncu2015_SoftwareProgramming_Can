package cot.ko.uim;
import java.awt.Color;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public abstract class UDMButton_Unit extends JComponent
{
	private JLabel pressedimg;
	private JLabel releaseimg;
	private JLabel costtext1;
	private JLabel costtext2;
	private boolean clickenable;
	private boolean isReleased = true;
	private buttonmouselistener buttonlistener ;
	
	public UDMButton_Unit()
	{
		buttonlistener = new buttonmouselistener();
		addMouseListener(buttonlistener);
		BaseButton();
	}
	
	public void BaseButton()
	{
		clickenable = true;
		
		costtext1 = new JLabel();
		costtext1.setBounds(50, 80, 50, 20);
		costtext1.setForeground(Color.BLACK);
		add(costtext1);
		costtext1.setVisible(true);
	
		releaseimg = new JLabel();
		add(releaseimg);
		releaseimg.setVisible(true);
		
		costtext2 = new JLabel();
		costtext2.setBounds(50, 91, 50, 20);
		costtext2.setForeground(Color.BLACK);
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
	
	public abstract void clicked();
	public abstract void pressed();
	public abstract void released(int x, int y);
	
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
}