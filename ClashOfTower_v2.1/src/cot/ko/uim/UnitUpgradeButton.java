package cot.ko.uim;

import java.io.IOException;

import cof.hong.tcpcm.TcpClient;
import cot.ko.dom.RenderThread;


public class UnitUpgradeButton extends UDMButton_Simple
{
	public static UnitUpgradeButton getInstance()
	{
		return instance;
	}
	
	private static UnitUpgradeButton instance = new UnitUpgradeButton();
	
	
	private InformationCenter 	ic;
	private UnitStatePanel		usp;
	private TcpClient			tc;
	private RenderThread		rt;
	private String 				type;
	private int 				currentlevel;
	
	private UnitUpgradeButton()
	{
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
	
	public void setUnitStatePanel(UnitStatePanel usp)
	{
		this.usp = usp;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
	
	
	@Override
	public void clicked() 
	{
		currentlevel = ic.getUnitLevel(type);
		
		if(currentlevel < 3)
		{
			ic.setUnitLevel( type, currentlevel + 1 );
			try {
				tc.upgradeUnit(currentlevel + 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			usp.setVisible(false);
			rt.startRenderThread();
		}
	}
	
	public void setButtonLock()
	{
		this.setClickEnable(false);
		this.setPressedimg("res/button/upgrade_button_locked.png");
		this.setReleaseimg("res/button/upgrade_button_locked.png");
	}
	
	public void setButtonUnlock()
	{
		if(currentlevel < 3)
		{
			this.setClickEnable(true);
			this.setPressedimg("res/button/upgrade_button_pressed.png");
			this.setReleaseimg("res/button/upgrade_button.png");
		}
		else 
		{
			this.setClickEnable(false);
			this.setPressedimg("res/button/upgrade_button_max.png");
			this.setReleaseimg("res/button/upgrade_button_max.png");
		}
	}
}
