package cot.ko.dom;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class Warrior extends Unit
{
	public static Image[][] 	image;
	
	public Warrior(Component comp, int x, int y, int dir, String act) 
	{
		super(comp, image, x, y, dir, act);
	}

	public static void initResources(MediaTracker tracker, int id) 
	{
		image = new Image[6][5];// [action & direction][frames] 0&1=walk 2&3=attack 4&5=break / 0=Left 1=Right
		for (int i = 0; i < 6; i++) 
		{
			for (int j = 0; j < 5; j++) 
			{
				ImageIcon img = new ImageIcon( "res/unit/Warrior" + (i) + (j+1) + ".png" );
				image[i][j] = img.getImage();
				tracker.addImage(image[i][j], id);
			}
		}
	}
}