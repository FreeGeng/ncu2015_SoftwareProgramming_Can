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

public class OrkButton extends UDMButton_Unit
{
	public static OrkButton getInstance()
	{
		return instance;
	}
	
	private static OrkButton instance = new OrkButton();
	
	private int 				currentlevel;
	private UnitStatePanel 		usp;
	private InformationCenter 	ic;
	private SceneRenderEngine	sre;
	private RenderThread		rt;
	private TcpClient			tc;
	
	private OrkButton() 
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
				this.setPressedimg("res/button/berserker_button_pressed.png");
				this.setReleaseimg("res/button/berserker_button.png");
				this.setCosttext("200");
				break;
			case 2:
				this.setPressedimg("res/button/orkrider_button_pressed.png");
				this.setReleaseimg("res/button/orkrider_button.png");
				this.setCosttext("350");
				break;
			case 3:
				this.setPressedimg("res/button/orkking_button_pressed.png");
				this.setReleaseimg("res/button/orkking_button.png");
				this.setCosttext("500");
				break;
			default:
				break;
		}
	}
	
	@Override
	public void clicked()
	{
		rt.stop();
		usp.setState("Ork", currentlevel);
	}

	@Override
	public void pressed() 
	{
		switch (currentlevel) 
		{
			case 0:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_ork.png").getImage(),
						new Point(16,16),"ork cursor"));
				break;
			case 1:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_berserker.png").getImage(),
						new Point(16,16),"berserker cursor"));
				break;
			case 2:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_orkrider.png").getImage(),
						new Point(16,16),"orkrider cursor"));
				break;
			case 3:
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						new ImageIcon("res/cursor/cursor_orkking.png").getImage(),
						new Point(16,16),"orkking cursor"));
				break;
			default:
				break;
		}
	}

	@Override
	public void released(int x, int y) 
	{
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		int roadNumber = sre.onRoad( new int[]{(x+510), (y+460)} );
		
		if(roadNumber != 0)
		{
			try {
				tc.addUnit("Ork", (currentlevel+1), ic.getPlayerNumber(), roadNumber);
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
				this.setPressedimg("res/button/ork_button_locked.png");
				this.setReleaseimg("res/button/ork_button_locked.png");
				break;
			case 1:
				this.setPressedimg("res/button/berserker_button_locked.png");
				this.setReleaseimg("res/button/berserker_button_locked.png");
				break;
			case 2:
				this.setPressedimg("res/button/orkrider_button_locked.png");
				this.setReleaseimg("res/button/orkrider_button_locked.png");
				break;
			case 3:
				this.setPressedimg("res/button/orkking_button_locked.png");
				this.setReleaseimg("res/button/orkking_button_locked.png");
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
				this.setPressedimg("res/button/ork_button_pressed.png");
				this.setReleaseimg("res/button/ork_button.png");
				break;
			case 1:
				this.setPressedimg("res/button/berserker_button_pressed.png");
				this.setReleaseimg("res/button/berserker_button.png");
				break;
			case 2:
				this.setPressedimg("res/button/orkrider_button_pressed.png");
				this.setReleaseimg("res/button/orkrider_button.png");
				break;
			case 3:
				this.setPressedimg("res/button/orkking_button_pressed.png");
				this.setReleaseimg("res/button/orkking_button.png");
				break;
			default:
				break;
		}
	}
}