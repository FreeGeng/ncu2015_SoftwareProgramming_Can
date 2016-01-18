package cot.ko.effectre;

import java.awt.Graphics;

import cot.ko.dom.DynamicObjectModule;
import cot.ko.dom.EffectVector;

public class EffectRenderEngine 
{
	private static DynamicObjectModule dom = DynamicObjectModule.getInstance();
	private EffectVector		ev;
	
	public static EffectRenderEngine getInstance()
	{
		return instance;
	}
	
	private static EffectRenderEngine instance = new EffectRenderEngine();
	
	public void renderSprites(Graphics g)
	{
		// get a vector of references to all the dynamic objects.
		ev = (EffectVector) dom.getAllEffectObjects();
		
		// draw
		ev.draw(g);
	}
}