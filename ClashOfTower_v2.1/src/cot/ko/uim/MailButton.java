package cot.ko.uim;
import javax.swing.JOptionPane;


public class MailButton extends UDMButton_Simple
{
	public static MailButton getInstance()
	{
		return instance;
	}
	
	private static MailButton instance = new MailButton();
	
	private MainFrame			mf;
	private InformationCenter 	ic;
	
	public void setMainFrame(MainFrame mf)
	{
		this.mf = mf;
	}
	
	public void setInformationCenter(InformationCenter ic)
	{
		this.ic = ic;
	}
	
	@Override
	public void clicked()
	{
		mf.ChangeScreen("CapitalCitySystem");
		ic.setCapitalCitySystem(false);
		ic.setMessengerState(false);
	}
}
