package cot.ko.uim;
import java.io.IOException;

import javax.swing.JOptionPane;

import cof.hong.tcpcm.TcpClient;


public class TowerRecoverButton extends UDMButton_Tower
{
	public static TowerRecoverButton getInstance()
	{
		return instance;
	}
	
	private static TowerRecoverButton instance = new TowerRecoverButton();
	
	private InformationCenter 	ic;
	private TcpClient			tc;
	
	private TowerRecoverButton()
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
	
	@Override
	public void clicked()
	{
		try {
			tc.callTowerUpgrade(ic.getPlayerNumber(), "hpRecover");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		this.setReleaseimg("res/button/tower_recover_button.png");
	}
}