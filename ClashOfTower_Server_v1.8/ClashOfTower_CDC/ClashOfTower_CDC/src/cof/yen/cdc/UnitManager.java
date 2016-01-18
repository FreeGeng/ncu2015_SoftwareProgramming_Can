package cof.yen.cdc;

import java.util.ArrayList;

public class UnitManager extends ArrayList<Unit>{
		
	public UnitManager() {
		// Do nothing, and this class just extends arraylist
	}
	
	public Unit getBySerialNum(int serialNumber){
		for(int i=0 ; i<this.size() ; i++){
			Unit curUnit = this.get(i);
			if(curUnit.getSerialNumber() == serialNumber)
				return curUnit;
		}		
		System.out.println("### UnitManager ERROR¡G There is no such unit has this serialNumber. ###");
		assert false : "### UnitManager ERROR¡G There is no such unit has this serialNumber. ###";
		return null;
	}
	
	public void removeBySerialNum(int clientNo, int serialNumber ){
		for(int i=0 ; i<this.size() ; i++){
			if(this.get(i).getSerialNumber() == serialNumber){
				super.remove(i);
				CDC.getInstance().addTCPUpdateInfo(Encoder.getInstance().enRemoveUnit(clientNo, serialNumber));
				return;
			}
		}		
	}
}
