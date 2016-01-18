package cot.ko.uim;

import cot.ko.dom.RenderThread;

public class CancelButton extends UDMButton_Simple
{
	private UnitStatePanel	usp;
	private RenderThread	rt = RenderThread.getInstance();
	
	public CancelButton(UnitStatePanel usp) 
	{
		this.usp = usp;
	}
	
	@Override
	public void clicked() 
	{
		usp.setVisible(false);
		rt.startRenderThread();
	}
}
