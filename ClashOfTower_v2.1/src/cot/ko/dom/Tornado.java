package cot.ko.dom;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class Tornado extends Effect
{
	public static Image[] 	image;
	
	public Tornado(Component comp, int x, int y) 
	{
		super(comp, image, 0, 1, 1, x, y);
	}
	
	public static void initResources(MediaTracker tracker, int id) 
	{
		image = new Image[19];
		for (int i = 0; i < 19; i++) 
		{
			ImageIcon img = new ImageIcon( "res/effect/Tornado" + (i+1) + ".png" );
			image[i] = img.getImage();
			tracker.addImage(image[i], id);
		}
	}
}
