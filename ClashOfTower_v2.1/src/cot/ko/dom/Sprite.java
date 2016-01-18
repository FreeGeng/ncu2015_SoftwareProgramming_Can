package cot.ko.dom;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

public class Sprite 
{
	protected Component     component;
	protected Image[]       image;
	protected int           frame,
                          	frameInc,
                          	frameDelay,
                          	frameTrigger;
	protected Rectangle     position;
	
	public Sprite(Component comp, Image[] img, int f, int fi, int fd, int x, int y) 
	{
		component = comp;
		image = img;
		setPosition(new Rectangle(x, y, img[f].getWidth(comp), img[f].getHeight(comp)));
		frame = f;
		frameInc = fi;
		frameDelay = frameTrigger = fd;
	}

	public Image[] getImage() 
	{
		return image;
	}

	public void setImage(Image[] img) 
	{
		image = img;
	}

	public int getFrameInc() 
	{
		return frameInc;
	}

	public void setFrameInc(int fi) 
	{
		frameInc = fi;
	}

	public int getFrame() 
	{
		return frame;
	}
	
	public void setFrame(int f) 
	{
		frame = f;
	}

	protected void incFrame() 
	{
		if ((frameDelay > 0) && (--frameTrigger <= 0)) 
		{
			// Reset the frame trigger
			frameTrigger = frameDelay;

			// Increment the frame
			frame += frameInc;
			if (frame >= image.length)
				frame = 0;
			else if (frame < 0)
				frame = image.length - 1;
		}
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
		// Increment the frame
		incFrame();
	}

	public void draw(Graphics g, int x, int y) 
	{
		// Draw the current frame
		g.drawImage(image[frame], x, y, component);
	}
}