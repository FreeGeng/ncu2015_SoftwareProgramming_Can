package cot.ko.dom;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class Messenger extends Sprite
{
	public static Image[][] 	image;
	protected int       		direction;
	
	public Messenger (Component comp, int x, int y, int dir) 
	{
		super(comp, image[0], 0, 0, 2, x, y);
		setDirection(dir);
	}

	public static void initResources(MediaTracker tracker, int id) 
	{
		image = new Image[2][5];// [direction][frames] 0=Left 1=Right
		for (int i = 0; i < 2; i++) 
		{
			for (int j = 0; j < 5; j++) 
			{
				ImageIcon img = new ImageIcon( "res/unit/Messenger" + (i) + (j+1) + ".png" );
				image[i][j] = img.getImage();
				tracker.addImage(image[i][j], id);
			}
		}
	}
	
	public void setDirection(int dir) 
	{
		this.direction = dir;
		// Set the image
		setImage(image[direction]);
		setFrameInc(1);
	}
}