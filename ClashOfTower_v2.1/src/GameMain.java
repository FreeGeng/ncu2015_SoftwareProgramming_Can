


import cot.ko.dom.DynamicObjectModule;
import cot.ko.dom.RenderThread;
import cot.ko.uim.InformationCenter;
import cot.ko.uim.MainFrame;



public class GameMain 
{
	private InformationCenter	ic = InformationCenter.getInstance();
	private RenderThread		rt = RenderThread.getInstance();
	private DynamicObjectModule dom = DynamicObjectModule.getInstance();
	private int 				playerITP[][] = new int[4][3];
	
	public static void main(String[] args) throws InterruptedException
	{
		new GameMain();
		
	}
	
	public GameMain() throws InterruptedException
	{
		
		playerITP[0][0] = 0;
		playerITP[0][1] = 1000;
		playerITP[0][2] = 2500;
		
		playerITP[1][0] = 1;
		playerITP[1][1] = 2500;
		playerITP[1][2] = 1000;
		
		playerITP[2][0] = 2;
		playerITP[2][1] = 4000;
		playerITP[2][2] = 2500;
		
		playerITP[3][0] = 3;
		playerITP[3][1] = 2500;
		playerITP[3][2] = 4000;
		
		MainFrame mf = MainFrame.getInstance();
		rt.startRenderThread();
		ic.startCoolDownTimer();
		dom.initTowerPosition(playerITP);
		
		dom.addUnit(0, 101, "Mailk", 0, 1000, 1000, "walk", 0);
		dom.addEffect("church2", 1000, 2500);
		//dom.addUnit(0, 201, "Ork", 1, 1020, 1020, "walk", 1);
		
		int x = 1000;
		int y = 1000;
		
		for(y = 1000; y<=4000; y+=20)
		{
			dom.updateUnit(0, 101, x, y, 1, "walk");
			//dom.updateUnit(0, 201, x+50, y+50, 1, "walk");
			Thread.sleep(100);
		}

		for(x = 1000; x<=4000; x+=20)
		{
			dom.updateUnit(0, 101, x, y, 0, "walk");
			//dom.updateUnit(0, 201, x+50, y+50, 0, "walk");
			Thread.sleep(100);
		}
		
		for(y = 4000; y>=1000; y-=20)
		{
			dom.updateUnit(0, 101, x, y, 0, "walk");
			//dom.updateUnit(0, 201, x+50, y+50, 0, "walk");
			Thread.sleep(100);
		}
		
		for(x = 4000; x>=1000; x-=20)
		{
			dom.updateUnit(0, 101, x, y, 1, "walk");
			//dom.updateUnit(0, 201, x+50, y+50, 1, "walk");
			Thread.sleep(100);
		}
	}
}
