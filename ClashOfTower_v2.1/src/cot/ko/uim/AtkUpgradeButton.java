package cot.ko.uim;

import java.io.IOException;

import cof.hong.tcpcm.TcpClient;


public class AtkUpgradeButton extends UDMButton_Tower
{
	public static AtkUpgradeButton getInstance()
	{
		return instance;
	}
	
	private static AtkUpgradeButton instance = new AtkUpgradeButton();
	
	private TowerPanel			tp;
	private InformationCenter 	ic;
	private TcpClient			tc;
	private int level = 0;
	
	private AtkUpgradeButton()
	{
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
	
	public void setTowerPanel(TowerPanel tp)
	{
		this.tp = tp;
	}
	
	@Override
	public void clicked()
	{
		if(level < 4)
		{
			level++;
		}
		
		try {
			tc.callTowerUpgrade(ic.getPlayerNumber(), "towerAtkUp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ic.setTowerLevel("TowerATK", level);
		tp.setAtkLevel(level);
	}
	
	public void setButtonLock()
	{
		this.setClickEnable(false);
		this.setPressedimg("res/button/upgrade_locked.png");
		this.setReleaseimg("res/button/upgrade_locked.png");
	}
	
	public void setButtonUnlock()
	{
		this.setClickEnable(true);
		this.setPressedimg("res/button/upgrade_ok.png");
		this.setReleaseimg("res/button/tower_atkup_button.png");
	}
}