package cot.ko.uim;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import cof.hong.tcpcm.TcpClient;
import cot.ko.dom.RenderThread;
import cot.qin.sre.SceneRenderEngine;


public class WolfButton extends UDMButton_Unit
{
	public static WolfButton getInstance()
	{
		return instance;
	}
	
	private static WolfButton instance = new WolfButton();
	
	private int 				currentlevel;
	private UnitStatePanel 		usp;
	private InformationCenter 	ic;
	private SceneRenderEngine	sre;
	private RenderThread		rt;
	private TcpClient			tc;
	
	private WolfButton() 
	{
		this.usp = UnitStatePanel.getInstance();
		this.sre = SceneRenderEngine.getInstance();
		this.rt = RenderThread.getInstance();
		try {
			this.tc = TcpClient.getInstance();
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
				this.setPressedimg("res/button/timberwolf_button_pressed.png");
				this.setReleaseimg("res/button/timberwolf_button.png");
				this.setCosttext("150");
				break;
			case 2:
				this.setPressedimg("res/button/snowwolf_button_pressed.png");
				this.setReleaseimg("res/button/snowwolf_button.png");
				this.setCosttext("250");
				break;
			case 3:
				this.setPressedimg("res/button/devilwolf_button_pressed.png");
				this.setReleaseimg("res/button/devilwolf_button.png");
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
		usp.setState("Wolf", currentlevel);
	}

	@Override
	public void pressed() 
	{
		switch (currentlevel) 
		{
			case 0:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_wolf.png").getImage(),
						new Point(16,16),"wolf cursor"));
				break;
			case 1:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_timberwolf.png").getImage(),
						new Point(16,16),"timberwolf cursor"));
				break;
			case 2:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_snowwolf.png").getImage(),
						new Point(16,16),"snowwolf cursor"));
				break;
			case 3:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_devilwolf.png").getImage(),
						new Point(16,16),"devilwolf cursor"));
				break;
			default:
				break;
		}
	}

	@Override
	public void released(int x, int y) 
	{
		// TODO Auto-generated method stub
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		int roadNumber = sre.onRoad( new int[]{(x+350), (y+460)} );
		
		if(roadNumber != 0)
		{
			try {
				tc.addUnit("Wolf", (currentlevel+1), ic.getPlayerNumber(), roadNumber);
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
				this.setPressedimg("res/button/wolf_button_locked.png");
				this.setReleaseimg("res/button/wolf_button_locked.png");
				break;
			case 1:
				this.setPressedimg("res/button/timberwolf_button_locked.png");
				this.setReleaseimg("res/button/timberwolf_button_locked.png");
				break;
				
			case 2:
				this.setPressedimg("res/button/snowwolf_button_locked.png");
				this.setReleaseimg("res/button/snowwolf_button_locked.png");
				break;
			case 3:
				this.setPressedimg("res/button/devilwolf_button_locked.png");
				this.setReleaseimg("res/button/devilwolf_button_locked.png");
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
				this.setPressedimg("res/button/wolf_button_pressed.png");
				this.setReleaseimg("res/button/wolf_button.png");
				break;
			case 1:
				this.setPressedimg("res/button/timberwolf_button_pressed.png");
				this.setReleaseimg("res/button/timberwolf_button.png");
				break;
				
			case 2:
				this.setPressedimg("res/button/snowwolf_button_pressed.png");
				this.setReleaseimg("res/button/snowwolf_button.png");
				break;
			case 3:
				this.setPressedimg("res/button/devilwolf_button_pressed.png");
				this.setReleaseimg("res/button/devilwolf_button.png");
				break;
			default:
				break;
		}
	}
}