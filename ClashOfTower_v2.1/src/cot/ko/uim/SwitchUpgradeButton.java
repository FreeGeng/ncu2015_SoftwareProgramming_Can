package cot.ko.uim;

public class SwitchUpgradeButton extends UDMButton_Simple
{
	private boolean 	isUnitUpgrade = true;
	private MainPanel	mp;
	
	public SwitchUpgradeButton(MainPanel mp) 
	{
		this.mp = mp;
	}
	
	@Override
	public void clicked()
	{
		if(isUnitUpgrade)
		{
			this.setPressedimg("res/button/unit_upgrade_button.png");
			this.setReleaseimg("res/button/unit_upgrade_button.png");
			isUnitUpgrade = false;
			mp.ChangeUpgradePanel("TowerUp");
		}
		else
		{
			this.setPressedimg("res/button/tower_upgrade_button.png");
			this.setReleaseimg("res/button/tower_upgrade_button.png");
			isUnitUpgrade = true;
			mp.ChangeUpgradePanel("UnitUp");
		}
		
		mp.setState(isUnitUpgrade);
	}
}