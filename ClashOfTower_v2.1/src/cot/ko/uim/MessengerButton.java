package cot.ko.uim;
import java.io.IOException;

import javax.swing.JOptionPane;

import cof.hong.tcpcm.TcpClient;


public class MessengerButton extends UDMButton_Unit
{
	public static MessengerButton getInstance()
	{
		return instance;
	}
	
	private static MessengerButton instance = new MessengerButton();
	
	private TcpClient tc;
	private InformationCenter ic;
	
	private MessengerButton() 
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
			tc.addMessenger(ic.getPlayerNumber());
			ic.setMessengerState(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void pressed() {}

	@Override
	public void released(int x, int y) {}
	
	public void setButtonLock()
	{
		this.setClickEnable(false);
		this.setPressedimg("res/button/messenger_button_locked.png");
		this.setReleaseimg("res/button/messenger_button_locked.png");
	}
	
	public void setButtonUnlock()
	{
		this.setClickEnable(true);
		this.setPressedimg("res/button/messenger_button_pressed.png");
		this.setReleaseimg("res/button/messenger_button.png");
	}
}