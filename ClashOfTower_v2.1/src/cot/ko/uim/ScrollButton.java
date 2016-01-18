package cot.ko.uim;

import java.io.IOException;

import cof.hong.tcpcm.TcpClient;

public class ScrollButton extends UDMButton_Choose
{
	private MainFrame 			mf;
	private String				event;
	private TcpClient			tc;
	
	public ScrollButton(MainFrame mf) 
	{
		this.mf = mf;
		try {
			this.tc = TcpClient.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setEvent(String event)
	{
		this.event = event;
	}
	
	@Override
	public void clicked() 
	{
		try {
			tc.callCapitalCitySkill(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mf.ChangeScreen("Main");
	}
}