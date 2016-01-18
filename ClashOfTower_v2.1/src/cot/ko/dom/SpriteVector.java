package cot.ko.dom;

import java.awt.Graphics;
import java.util.Vector;

import cot.qin.sre.SceneRenderEngine;

public class SpriteVector extends Vector 
{
	private SceneRenderEngine sre = SceneRenderEngine.getInstance();
	private Sprite tempSprite;

	public void draw(Graphics g) 
	{
		if (!this.isEmpty()) 
		{
			// Iterate through sprites, drawing each
			for (int i = 0; i < size(); i++) 
			{
				tempSprite = (Sprite) elementAt(i);
				tempSprite.update();
				if (sre.inWindow(new int[] { tempSprite.getPosition().x,
						tempSprite.getPosition().y })) 
				{
					int[] xy = sre.changeXY(new int[] { tempSprite.getPosition().x,
						tempSprite.getPosition().y });
					((Sprite) elementAt(i)).draw(g, xy[0], xy[1]);
				}
				
			}
		}
	}
}