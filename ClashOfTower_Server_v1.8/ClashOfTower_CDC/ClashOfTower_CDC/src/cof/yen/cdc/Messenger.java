package cof.yen.cdc;

public class Messenger {
	private String type;
	private int x, y, host, serialNumber;
	
	/*public Messenger() {

	}*/

	public Messenger(int x, int y, int host, int serialNumber) {
		this.x = x;
		this.y = y;
		this.host = host;
		this.serialNumber = serialNumber;
	}

	public void sentArriveMsg(){		// �٭n��@  �γ\���ݭn�ѳo�Ӫ���ǰe��F�T���A�ӬO��coordinate�I�sencoder�Ӷǰe
		
	}


/*---------------------------------------------------------------------------*/	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHost() {
		return host;
	}

	public void setHost(int host) {
		this.host = host;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
}
