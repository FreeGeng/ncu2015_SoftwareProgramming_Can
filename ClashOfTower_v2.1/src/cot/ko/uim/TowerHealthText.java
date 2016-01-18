package cot.ko.uim;
import javax.swing.JLabel;

public class TowerHealthText extends JLabel
{
	public static TowerHealthText getInstance()
	{
		return instance;
	}
	
	private static TowerHealthText instance = new TowerHealthText();
}
