package cot.ko.uim;
import java.awt.Dimension;

import javax.swing.JPanel;

public class CapitalCitySystemPanel extends JPanel
{
	private UDMButton_Choose 	cb;
	private KingButton 		kb;
	
	public CapitalCitySystemPanel(MainFrame mf) 
	{
		// set Panel Background
		
		Dimension size = new Dimension(1000, 600);
	    this.setPreferredSize(size);
	    this.setMinimumSize(size);
	    this.setMaximumSize(size);
	    this.setSize(size);
		this.setLayout(null);
		this.setVisible(false);
		
		// set all button
		cb = new ChurchButton(mf);
		cb.setEnterimg("res/button/church_enter.png");
		cb.setReleaseimg("res/button/church_release.png");
		cb.setBounds(0, 0, 1000, 300);
		
		kb = new KingButton(mf);
		kb.setEnterimg("res/button/king_enter.png");
		kb.setReleaseimg("res/button/king_release.png");
		kb.setBounds(0, 300, 1000, 300);
		
		this.add(cb);
		this.add(kb);
	}
}
