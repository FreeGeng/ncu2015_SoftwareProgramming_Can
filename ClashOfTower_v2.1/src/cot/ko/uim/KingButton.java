package cot.ko.uim;

public class KingButton extends UDMButton_Choose
{
	private MainFrame mf;
	
	public KingButton(MainFrame mf) 
	{
		this.mf = mf;
	}
	
	@Override
	public void clicked() 
	{
		mf.ChangeScreen("ShowKing");
	}
}
