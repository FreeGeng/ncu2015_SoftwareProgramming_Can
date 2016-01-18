package cot.ko.uim;
import javax.swing.JLabel;


public class TotalUnitText extends JLabel
{
	public static TotalUnitText getInstance()
	{
		return instance;
	}
	
	private static TotalUnitText instance = new TotalUnitText();
}