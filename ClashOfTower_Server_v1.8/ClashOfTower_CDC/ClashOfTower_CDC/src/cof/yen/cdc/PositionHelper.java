package cof.yen.cdc;

import java.util.ArrayList;

public class PositionHelper extends ArrayList<Integer>{
	public static PositionHelper ph = null;
	private Parameter parameter = null;
	
	public PositionHelper() {
		parameter = Parameter.getInstance();
	}

	public static PositionHelper getInstance(){
		if(ph == null)
			ph = new PositionHelper();
		return ph;
	}
	
	public int[] getTowerPos(int clientNo){
		for(int i=0 ; i<this.size() ; i++)
			if(this.get(i) == clientNo)
				return parameter.INIT_TOWER_POS[i];
		assert false: "No such Client"+clientNo;
		return null;
	}
	
	public int getClientNoByEntryNo(int entryNo){
		assert entryNo < parameter.MAX_PLAYER_NUM : "EntryNo shall be under "+parameter.MAX_PLAYER_NUM;
		return this.get(entryNo);
	}
	
	
	public int getPlayerEntryNo(int clientNo){
		for(int i=0 ; i<this.size() ; i++)
			if(this.get(i) == clientNo)
				return i;
		assert false: "No such Client"+clientNo;
		return parameter.ERROR_MSG;
	}
	
	public String getUnitDirection(int clientNo, int aisle){
		String result = "";
		int entryNo = getPlayerEntryNo(clientNo);
		if(entryNo % 2 == 0){		// 已定義順時針：第0個入場的玩家 是右上寶塔、第1個入場的玩家 是右下寶塔、第2個入場的玩家 是左下寶塔
			if(aisle == parameter.AISLE_RIGHT || aisle == parameter.AISLE_DOWN)
				result = parameter.UNIT_DIRECTION_RIGHT;
			else
				result = parameter.UNIT_DIRECTION_LEFT;
		}
		else{
			if(aisle == parameter.AISLE_RIGHT || aisle == parameter.AISLE_UP)
				result = parameter.UNIT_DIRECTION_RIGHT;
			else
				result = parameter.UNIT_DIRECTION_LEFT;
		}
		return result; 
	}
}
