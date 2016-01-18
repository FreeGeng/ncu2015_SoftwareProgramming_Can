package cot.ko.dom;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Tower 
{
	protected Component     component;
	protected Image       	image;
	protected Rectangle     position;
	private int				BounceCounter = 0;
	private boolean			BounceUpFlag = true;
	
	public Tower(Component comp, Image img, int x, int y) 
	{
		component = comp;
		image = img;
		setPosition(new Rectangle(x, y, img.getWidth(comp), img.getHeight(comp)));
	}

	public Image getImage() 
	{
		return image;
	}

	public void setImage(Image img) 
	{
		image = img;
	}

	public Rectangle getPosition() 
	{
		return position;
	}

	void setPosition(Rectangle pos) 
	{
		position = pos;
	}

	public void setPosition(Point pos) 
	{
		position.setLocation(pos.x, pos.y);
	}
	
	public void update() 
	{
		if(BounceCounter >= 10)
			BounceUpFlag = false;
		else if(BounceCounter <= 0)
			BounceUpFlag = true;
		
		if(BounceUpFlag)
		{
			BounceCounter++;
			position.setLocation(position.x+2, (position.y + 2));
		}
		else
		{
			BounceCounter--;
			position.setLocation(position.x-2, (position.y - 2));
		}
		
	}

	public void draw(Graphics g, int x, int y) 
	{
		// Draw the current frame
		g.drawImage(image, x, y, component);
	}
}
