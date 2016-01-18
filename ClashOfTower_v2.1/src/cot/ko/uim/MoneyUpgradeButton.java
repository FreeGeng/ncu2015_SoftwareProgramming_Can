package cot.ko.uim;

import java.io.IOException;

import cof.hong.tcpcm.TcpClient;


public class MoneyUpgradeButton extends UDMButton_Tower
{
	public static MoneyUpgradeButton getInstance()
	{
		return instance;
	}
	
	private static MoneyUpgradeButton instance = new MoneyUpgradeButton();
	
	private TowerPanel			tp;
	private InformationCenter 	ic;
	private TcpClient			tc;
	private int level = 0;
	
	private MoneyUpgradeButton()
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
			tc.callTowerUpgrade(ic.getPlayerNumber(), "moneyUpFaster");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ic.setTowerLevel("Money", level);
		tp.setMoneyLevel(level);
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
		this.setReleaseimg("res/button/tower_moneyup_button.png");
	}
}
