package cot.ko.uim;

public class ChurchButton extends UDMButton_Choose
{
	private MainFrame mf;
	
	public ChurchButton(MainFrame mf) 
	{
		this.mf = mf;
	}
	
	@Override
	public void clicked() 
	{
		mf.ChangeScreen("ShowChurch");
	}
}
