package cot.ko.dom;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class SmallBomb extends Effect
{
	public static Image[] 	image;
	
	public SmallBomb(Component comp, int x, int y) 
	{
		super(comp, image, 0, 1, 1, x, y);
	}
	
	public static void initResources(MediaTracker tracker, int id) 
	{
		image = new Image[15];
		for (int i = 0; i < 15; i++) 
		{
			ImageIcon img = new ImageIcon( "res/effect/SmallBomb" + (i+1) + ".png" );
			image[i] = img.getImage();
			tracker.addImage(image[i], id);
		}
	}
}
