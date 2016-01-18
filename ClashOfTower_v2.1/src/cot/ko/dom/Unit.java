package cot.ko.dom;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.ImageIcon;

public class Unit extends Sprite
{
	public Image[][] 	image;
	protected int       direction;
	protected String	action;
	
	private int 		id;
	
	public Unit(Component comp, Image[][] img, int x, int y, int dir, String act) 
	{
		super(comp, img[0], 0, 1, 2, x, y);
		this.image = img;
		setState(dir, act);
	}
	
	public void setState(int dir, String act) 
	{
		this.direction = dir;
		this.action = act;
		// Set the image
		if( action.equals("walk") )
			setImage(image[direction]);
		else if( action.equals("attack") || action.equals("attackTower"))
			setImage(image[direction+2]);
		else if( action.equals("break") )
			setImage(image[direction+4]);
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public int getID()
	{
		return id;
	}
}
