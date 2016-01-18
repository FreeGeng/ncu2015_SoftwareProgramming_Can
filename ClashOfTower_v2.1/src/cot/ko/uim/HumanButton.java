package cot.ko.uim;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;

import cof.hong.tcpcm.TcpClient;
import cot.ko.dom.RenderThread;
import cot.qin.sre.SceneRenderEngine;


public class HumanButton extends UDMButton_Unit
{
	public static HumanButton getInstance()
	{
		return instance;
	}
	
	private static HumanButton instance = new HumanButton();
	
	private int 				currentlevel;
	private UnitStatePanel 		usp;
	private InformationCenter 	ic;
	private SceneRenderEngine	sre;
	private RenderThread		rt;
	private TcpClient			tc;
	
	private HumanButton() 
	{
		this.usp = UnitStatePanel.getInstance();
		this.sre = SceneRenderEngine.getInstance();
		this.rt = RenderThread.getInstance();
		try {
			this.tc = TcpClient.getInstance();
			System.out.println("ttttt2222");
			System.out.println(this.tc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setInformationCenter(InformationCenter ic)
	{
		this.ic = ic;
	}
	
	public void setCurrentLevel(int currentlevel)
	{
		this.currentlevel = currentlevel;
		
		switch (currentlevel) 
		{
			case 1:
				this.setPressedimg("res/button/page_button_pressed.png");
				this.setReleaseimg("res/button/page_button.png");
				this.setCosttext("150");
				break;
			case 2:
				this.setPressedimg("res/button/knight_button_pressed.png");
				this.setReleaseimg("res/button/knight_button.png");
				this.setCosttext("200");
				break;
			case 3:
				this.setPressedimg("res/button/cygnusknight_button_pressed.png");
				this.setReleaseimg("res/button/cygnusknight_button.png");
				this.setCosttext("350");
				break;
			default:
				break;
		}
	}
	
	@Override
	public void clicked()
	{
		rt.stop();
		usp.setState("Human", currentlevel);
	}

	@Override
	public void pressed() 
	{
		switch (currentlevel) 
		{
			case 0:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_warrior.png").getImage(),
						new Point(16,16),"warrior cursor"));
				break;
			case 1:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_page.png").getImage(),
						new Point(16,16),"page cursor"));
				break;
			case 2:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_knight.png").getImage(),
						new Point(16,16),"knight cursor"));
				break;
			case 3:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_cygnusknight.png").getImage(),
						new Point(16,16),"cygnusknight cursor"));
				break;
			default:
				break;
		}
	}

	@Override
	public void released(int x, int y) 
	{
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		int roadNumber = sre.onRoad( new int[]{(x+190), (y+460)} );
		
		if(roadNumber != 0)
		{
			try {
				tc.addUnit("Human", (currentlevel+1), ic.getPlayerNumber(), roadNumber);
				System.out.println("ttttt1");
				System.out.println(this.tc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setButtonLock()
	{
		this.setClickEnable(false);
		
		switch (currentlevel) 
		{
			case 0:
				this.setPressedimg("res/button/warrior_button_locked.png");
				this.setReleaseimg("res/button/warrior_button_locked.png");
				break;
			case 1:
				this.setPressedimg("res/button/page_button_locked.png");
				this.setReleaseimg("res/button/page_button_locked.png");
				break;
			case 2:
				this.setPressedimg("res/button/knight_button_locked.png");
				this.setReleaseimg("res/button/knight_button_locked.png");
				break;
			case 3:
				this.setPressedimg("res/button/cygnusknight_button_locked.png");
				this.setReleaseimg("res/button/cygnusknight_button_locked.png");
				break;
			default:
				break;
		}
	}
	
	public void setButtonUnlock()
	{
		this.setClickEnable(true);
		
		switch (currentlevel) 
		{
			case 0:
				this.setPressedimg("res/button/warrior_button_pressed.png");
				this.setReleaseimg("res/button/warrior_button.png");
				break;
			case 1:
				this.setPressedimg("res/button/page_button_pressed.png");
				this.setReleaseimg("res/button/page_button.png");
				break;
				
			case 2:
				this.setPressedimg("res/button/knight_button_pressed.png");
				this.setReleaseimg("res/button/knight_button.png");
				break;
			case 3:
				this.setPressedimg("res/button/cygnusknight_button_pressed.png");
				this.setReleaseimg("res/button/cygnusknight_button.png");
				break;
			default:
				break;
		}
	}
}