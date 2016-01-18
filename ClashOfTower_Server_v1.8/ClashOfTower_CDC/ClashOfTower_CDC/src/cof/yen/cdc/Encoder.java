package cof.yen.cdc;

import java.util.ArrayList;
import java.util.HashMap;


public class Encoder {	
	public static Encoder coder = null;
	private String instructionName = null; 
	
	public Encoder(){
		
	}
	
	public static Encoder getInstance(){
		if(coder == null)
			coder = new Encoder();
		return coder;
	}
	
	public String enInitTowerPosition(HashMap<Integer, int[]> position){
		instructionName = "initTowerPosition";
		int []temp;
		String tempString = "";
		//key;temp[0];temp[1]-key.......
		for(int key : position.keySet()){
			temp = position.get(key);
			tempString = tempString + key;
			for(int i = 0; i < temp.length; i++){
				tempString = tempString + ";" + temp[i] ;
			}
			tempString = tempString + "-";
		}
		return instructionName + "#" + tempString;
	}
	
	public String enRemoveUnit(int clientNo, int serialNumber){
		instructionName = "removeUnit";
		return instructionName + "#" + clientNo + ";" + serialNumber;
	}
	
	public String enAddUnit(int clientNo,int serialNumber,String type,int level,int x,int y,String action,String direction){
		instructionName = "addUnit";
		return instructionName + "#" + clientNo + ";" + serialNumber + ";" + type + ";"
		+ (level-1) + ";" + x + ";" + y + ";" + action + ";" + direction;
	}
	
	public String enSetUnitProperty (int clientNo, int serialNumber, int x, int y,
			String direction, String action){
		instructionName = "setUnitProperty";
		return instructionName + "#" + clientNo + ";" + serialNumber + ";" + x + ";"
		+ y + ";" + direction + ";" + action;
	}
	
	public String enAddMessenger (int clientNo, int x, int y){
		instructionName = "addMessenger";
		return instructionName + "#" + clientNo + ";" + x + ";" + y;
	}
	
	public String enRemoveMessenger (int clientNo){
		instructionName = "removeMessenger";
		return instructionName + "#" + clientNo;
	}
	
	public String enSetMessengerProperty (int clientNo, int x, int y){
		instructionName = "setMessengerProperty";
		return instructionName + "#" + clientNo + ";" + x + ";" + y;
	}
	
	public String enSentMessengerArriveMsg (int clientNo){
		instructionName = "sentMessengerArriveMsg";
		return instructionName + "#" + clientNo;		
	}
	
	public String enSentEndScore (ArrayList<EndScore> endScore){
		instructionName = "sentEndScore";
		String result = instructionName + "#";
		for(int i=0 ; i<endScore.size() ; i++){
			EndScore curScore = endScore.get(i);
			result += curScore.getClientNo() + ";" + curScore.getScore();
			if(i != endScore.size()-1)
				result += "-" ;
		}	
		return result;	
	}
	
	public String enSetPlayerMoney(int clientNo, int money){
		instructionName = "setPlayerMoney";
		return instructionName + "#" + clientNo + ";" + money;		
	}
	
	public String enSetTowerHp (int clientNo, int serialNumber, int hp){
		instructionName = "setTowerHp";
		return instructionName + "#" + clientNo + ";" + serialNumber + ";" + hp;
	}
	
	public String enSetEffect (String effectName, int x, int y){
		instructionName = "setEffect";
		return instructionName + "#" + effectName + ";" + x + ";" + y;
	}
	
	public String enSetGameTime (int time){
		instructionName = "setGameTime";
		return instructionName + "#" + time;		
	}
	
	public String enNotifyHeroUnlocked(int clientNo){
		instructionName = "notifyHeroUnlocked";
		return instructionName + "#" + clientNo;
	}
}
