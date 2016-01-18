package cot.ko.dom;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

import javax.swing.JPanel;

import cot.ko.uim.GameScreenPanel;

public class RenderThread implements Runnable 
{
	private GameScreenPanel gsp = null;
	private MediaTracker tracker;
	private Thread animate;
	private int delay = 83; // 12 fps

	/**
	 * get handle to the singleton
	 * 
	 * @return the singleton instance of this class.
	 */
	public static RenderThread getInstance() 
	{
		return instance;
	}

	/**
	 * singleton instance created when class is loaded.
	 */
	private static RenderThread instance = new RenderThread();

	public void startRenderThread() 
	{
		if (animate == null) 
		{
			gsp = GameScreenPanel.getInstanceRT();
			try {
				if(tracker == null)
					init();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			animate = new Thread(this);
			animate.start();
		}
	}

	public void stop() 
	{
		if (animate != null) 
		{
			animate = null;
		}
	}

	public void run() 
	{
		try 
		{
			tracker.waitForID(0);
		}
		catch (InterruptedException e) 
		{
			return;
		}
		
		// Update everything
		long t = System.currentTimeMillis();
		while (Thread.currentThread() == animate) 
		{
			gsp.repaint();

			try 
			{
				t += delay;
				Thread.sleep(Math.max(0, t - System.currentTimeMillis()));
			} 
			catch (InterruptedException e) 
			{
				break;
			}
		}
	}
	
	public void init() throws InterruptedException 
	{
		// Load and track the images
		tracker = new MediaTracker(gsp);
		
		Warrior.initResources(tracker, 0);
		Page.initResources(tracker, 0);
		Knight.initResources(tracker, 0);
		CygnusKnight.initResources(tracker, 0);
		
		Wolf.initResources(tracker, 0);
		TimberWolf.initResources(tracker, 0);
		SnowWolf.initResources(tracker, 0);
		DevilWolf.initResources(tracker, 0);
		
		Ork.initResources(tracker, 0);
		Berserker.initResources(tracker, 0);
		OrkRider.initResources(tracker, 0);
		OrkKing.initResources(tracker, 0);
		
		Denas.initResources(tracker, 0);
		Gerald.initResources(tracker, 0);
		Ingvar.initResources(tracker, 0);
		Mailk.initResources(tracker, 0);
		
		Messenger.initResources(tracker, 0);
		
		Tornado.initResources(tracker, 0);
		SmallBomb.initResources(tracker, 0);
		BigBomb.initResources(tracker, 0);
	}
	
}
