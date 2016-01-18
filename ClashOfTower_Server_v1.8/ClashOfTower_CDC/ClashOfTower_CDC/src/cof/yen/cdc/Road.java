package cof.yen.cdc;

import java.util.ArrayList;

public class Road extends ArrayList<Unit>{

	public Road() {
		// TODO Auto-generated constructor stub
	}

	public void removeBySerialNum( int serialNumber ){
		for(int i=0 ; i<this.size() ; i++){
			if(this.get(i).getSerialNumber() == serialNumber){
				super.remove(i);
				return;
			}
		}		
	}
}
