package cof.yen.cdc;

import java.util.ArrayList;
import java.util.HashMap;

public class Instruction {

}



class InitTowerPosition{
	private String instructionName = "initTowerPosition";
	private HashMap<Integer, int[]> position;		// 也可以用int[4][3]

	public InitTowerPosition(HashMap<Integer, int[]> position) {
		this.position = position;
	}
	
	public String toString(){
		int []temp;
		String tempString = "";
		//key;temp[0];temp[1]$key.......
		for(int key : position.keySet()){
			temp = position.get(key);
			tempString = tempString + key;
			for(int i = 0; i < temp.length; i++){
				tempString = tempString + ";" + temp[i] ;
			}
			tempString = tempString + "$";
		}
		return instructionName + "?" + tempString;
	}
}

class RemoveUnit{
	private String instructionName = "removeUnit";
	private int clientNo,serialNumber;
	
	public RemoveUnit(int clientNo, int serialNumber){
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
	}
	
	public String toString(){
		return instructionName + "?" + clientNo + ";" + serialNumber;
	}
}
class AddUnit{
	private String instructionName = "addUnit";
	private int clientNo,serialNumber,level,x,y;
	private String type,action,direction;
	
	public AddUnit(int clientNo,int serialNumber,String type,int level,int x,int y,String action,String direction){
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
		this.type = type;
		this.level = level;
		this.x = x;
		this.y = y;
		this.action = action;
		this.direction = direction;
	}
	
	public String toString(){
		return instructionName + "?" + clientNo + ";" + serialNumber + ";" + type + ";"
				+ level + ";" + x + ";" + y + ";" + action + ";" + direction;
	}
}
class SetUnitProperty{
	private String instructionName = "setUnitProperty";
	private int clientNo, serialNumber, x, y; 		// 若有數值為int -99999 或 String NO_CONFIGURE就是不設定此屬性 
	private String direction, action;
	
	public SetUnitProperty(int clientNo, int serialNumber, int x, int y,
			String direction, String action) {
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.action = action;
	}
	
	public String toString(){
		return instructionName + "?" + clientNo + ";" + serialNumber + ";" + x + ";"
				+ y + ";" + direction + ";" + action;
	}
}
class AddMessenger{
	private String instructionName = "addMessenger";
	private int serialNumber,x,y;
	
	public AddMessenger(int serialNumber, int x, int y){
		this.serialNumber = serialNumber;
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return instructionName + "?" + serialNumber + ";" + x + ";" + y;
	}
}
class RemoveMessenger{
	private String instructionName = "removeMessenger";
	private int serialNumber;
	
	public RemoveMessenger(int serialNumber){
		this.serialNumber = serialNumber;
	}
	
	public String toString(){
		return instructionName + "?" + serialNumber;
	}
}
class SetMessengerProperty{// 若有任何一個數值為-999999(NO_CONFIGURE_INT)  代表不設定這個屬性
	private String instructionName = "setMessengerProperty";
	private int clientNo, serialNumber, x, y;

	public SetMessengerProperty(int clientNo, int serialNumber, int x, int y) {
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return instructionName + "?" + clientNo + ";" + serialNumber + ";" + x + ";" + y;
	}
}
class SentMessengerArriveMsg{
	private String instructionName = "sentMessengerArriveMsg";
	private int clientNo;
	
	public SentMessengerArriveMsg(int clientNo){
		this.clientNo = clientNo;
	}
	
	public String toString(){
		return instructionName + "?" + clientNo;
	}
}

class SentEndScore{
	private String instructionName = "sentEndScore";
	private ArrayList<EndScore> endScore;

	public SentEndScore(ArrayList<EndScore> endScore) {
		this.endScore = endScore;
	}
	
	public String toString(){
		String result = instructionName + "?";
		for(int i=0 ; i<endScore.size() ; i++){
			EndScore curScore = endScore.get(i);
			result += curScore.getClientNo() + ";" + curScore.getScore();
			if(i != endScore.size()-1)
				result += "$" ;
		}	
		return result;		
	}
}

class SetPlayerMoney{
	private String instructionName = "setPlayerMoney";
	private int clientNo,money;
	
	public SetPlayerMoney(int clientNo, int money){
		this.clientNo = clientNo;
		this.money = money;
	}
	
	public String toString(){
		return instructionName + "?" + clientNo + ";" + money;
	}
}
class SetTowerHp{
	private String instructionName = "setTowerHp";
	private int clientNo, serialNumber, hp;
	
	public SetTowerHp(int clientNo, int serialNumber, int hp){
		this.clientNo = clientNo;
		this.serialNumber = serialNumber;
		this.hp = hp;
	}
	
	public String toString(){
		return instructionName + "?" + clientNo + ";" + serialNumber + ";" + hp;
	}
}
class SetEffect{
	private String instructionName = "setEffect";
	private String effectName ;
	private int x, y;
	
	public SetEffect(String effectName, int x, int y){
		this.effectName = effectName;
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return instructionName + "?" + effectName + ";" + x + ";" + y;
	}
}
/*class MakeTowerFiring{
	private String instructionName = "makeTowerFiring";
	private int x,y;
	
	public MakeTowerFiring(int x , int y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return instructionName + "?" + x + ";" + y;
	}
}*/
class SetGameTime{
	private String instructionName = "setGameTime";
	private int time;
	
	public SetGameTime(int time){
		this.time = time; 
	}
	
	public String toString(){
		return instructionName + "?" + time;
	}
}