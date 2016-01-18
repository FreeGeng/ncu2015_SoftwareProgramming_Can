package cot.ko.spritere;

import java.awt.Graphics;

import cot.ko.dom.DynamicObjectModule;
import cot.ko.dom.SpriteVector;

public class SpriteRenderEngine 
{
	private static DynamicObjectModule dom = DynamicObjectModule.getInstance();
	private SpriteVector		sv;
	
	public static SpriteRenderEngine getInstance()
	{
		return instance;
	}
	
	private static SpriteRenderEngine instance = new SpriteRenderEngine();
	
	public void renderSprites(Graphics g)
	{
		//get a vector of references to all the dynamic objects.
		sv = (SpriteVector) dom.getAllDynamicObjects();
		
		// draw
		
		sv.draw(g);
	}
}
