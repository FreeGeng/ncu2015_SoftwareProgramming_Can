package cot.ko.dom;

import java.awt.Graphics;
import java.util.Vector;

import cot.qin.sre.SceneRenderEngine;

public class EffectVector extends Vector 
{
	private SceneRenderEngine sre = SceneRenderEngine.getInstance();
	private Effect tempSprite;
	
	public void draw(Graphics g) 
	{
		if( !this.isEmpty() )
		{
			// Iterate through sprites, drawing each
			for (int i = 0; i < size(); i++)
			{
				if( !( (Effect)elementAt(i) ).getFinishFlag() )
				{
					tempSprite = (Effect) elementAt(i);
					tempSprite.update();
					if (sre.inWindow(new int[] { tempSprite.getPosition().x,
							tempSprite.getPosition().y })) 
					{
						int[] xy = sre.changeXY(new int[] { tempSprite.getPosition().x,
							tempSprite.getPosition().y });
						((Effect) elementAt(i)).draw(g, xy[0], xy[1]);
					}
				}
				else
				{
					this.removeElementAt(i);
				}
			}
		}
	}
}
