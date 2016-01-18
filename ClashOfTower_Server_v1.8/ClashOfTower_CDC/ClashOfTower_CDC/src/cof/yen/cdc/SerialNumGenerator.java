package cof.yen.cdc;

public class SerialNumGenerator {
	public static SerialNumGenerator sng;
	private int sn;
	
	public SerialNumGenerator() {
		this.sn = 120001;
	}
	
	public static SerialNumGenerator getInstance(){
		if(sng == null)
			sng = new SerialNumGenerator();
		return sng;
	}

	public int getNextSN(){
		return this.sn++;
	}
}
