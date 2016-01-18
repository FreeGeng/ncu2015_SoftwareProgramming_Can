package cot.ko.dom;

import java.awt.Graphics;
import java.util.Vector;

import cot.qin.sre.SceneRenderEngine;

public class TowerVector extends Vector
{
	private SceneRenderEngine sre = SceneRenderEngine.getInstance();
	private Tower tempTower;
	
	public void draw(Graphics g) 
	{
		if (!this.isEmpty()) 
		{
			// Iterate through sprites, drawing each
			for (int i = 0; i < size()-1; i++) 
			{
				tempTower = (Tower) elementAt(i);
				if(tempTower!=null)
				{
					if (sre.inWindow(new int[] { tempTower.getPosition().x,
							tempTower.getPosition().y })) 
					{
						int[] xy = sre.changeXY(new int[] { tempTower.getPosition().x,
								tempTower.getPosition().y });
						tempTower.draw(g, xy[0], xy[1]);
					}
				}
			}
			
			tempTower = (Tower) elementAt(size()-1);
			if(tempTower!=null)
			{
				tempTower.update();
				if (sre.inWindow(new int[] { tempTower.getPosition().x,
						tempTower.getPosition().y })) 
				{
					int[] xy = sre.changeXY(new int[] { tempTower.getPosition().x,
							tempTower.getPosition().y });
					((Tower) elementAt(size()-1)).draw(g, xy[0], xy[1]);
				}
			}
		}
	}
}
