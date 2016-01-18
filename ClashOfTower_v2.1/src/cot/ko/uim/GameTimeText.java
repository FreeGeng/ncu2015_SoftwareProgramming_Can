package cot.ko.uim;
import javax.swing.JLabel;

public class GameTimeText extends JLabel
{
	public static GameTimeText getInstance()
	{
		return instance;
	}
	
	private static GameTimeText instance = new GameTimeText();
}
