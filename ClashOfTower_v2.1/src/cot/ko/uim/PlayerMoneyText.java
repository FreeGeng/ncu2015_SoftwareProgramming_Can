package cot.ko.uim;
import javax.swing.JLabel;

public class PlayerMoneyText extends JLabel
{
	public static PlayerMoneyText getInstance()
	{
		return instance;
	}
	
	private static PlayerMoneyText instance = new PlayerMoneyText();
}
