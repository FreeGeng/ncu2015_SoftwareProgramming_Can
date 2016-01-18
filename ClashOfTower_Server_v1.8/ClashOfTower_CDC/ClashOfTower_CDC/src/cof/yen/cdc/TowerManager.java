package cof.yen.cdc;

import java.util.ArrayList;

public class TowerManager extends ArrayList<Tower>{

	public TowerManager() {

	}

	public Tower getBySerialNum(int serialNumber){
		for(int i=0 ; i<this.size() ; i++){
			Tower curTower = this.get(i);
			if(curTower.getSerialNumber() == serialNumber)
				return curTower;
		}		
		
		System.out.println("### TowerManager ERROR¡G There is no such tower has this serialNumber. ###");
		assert false:"### TowerManager ERROR¡G There is no such tower has this serialNumber. ###";
		return null;
	}	
	
	public void removeBySerialNum(int serialNumber){
		for(int i=0 ; i<this.size() ; i++){
			Tower curTower = this.get(i);
			if(curTower.getSerialNumber() == serialNumber){
				this.remove(i);
				return;
			}				
		}		
		System.out.println("### TowerManager ERROR¡G There is no such tower has this serialNumber. ###");
		assert false:"### TowerManager ERROR¡G There is no such tower has this serialNumber. ###";
	}
}
