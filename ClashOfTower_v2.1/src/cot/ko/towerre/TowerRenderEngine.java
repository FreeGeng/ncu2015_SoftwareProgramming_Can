package cot.ko.towerre;

import java.awt.Graphics;

import cot.ko.dom.DynamicObjectModule;
import cot.ko.dom.TowerVector;

public class TowerRenderEngine 
{
	private static DynamicObjectModule dom = DynamicObjectModule.getInstance();
	private TowerVector		tv;
	
	public static TowerRenderEngine getInstance()
	{
		return instance;
	}
	
	private static TowerRenderEngine instance = new TowerRenderEngine();
	
	public void renderSprites(Graphics g)
	{
		// get a vector of references to all the dynamic objects.
		tv = (TowerVector) dom.getAllTowerObjects();
		
		// draw
		tv.draw(g);
	}
}