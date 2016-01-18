package cof.yen.cdc;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class CapitalCity extends HashMap<Integer, Messenger>{
	private CDC cdc = null;
	private Parameter par = Parameter.getInstance();
	
	public CapitalCity(CDC cdc) {		// 如果場上已經有使者，就不能再送addMsger指令過來  要在client判斷
		this.cdc = cdc;
	}
	
	public void skill(int clientNo, String skillName){	
		switch (skillName) {
		case "castle1":
			castle1(clientNo);
			break;

		case "castle2":
			castle2(clientNo);
			break;
			
		case "castle3":
			castle3(clientNo);
			break;
			
		case "church1":
			church1(clientNo);			
			break;
			
		case "church2":
			church2(clientNo);		
			break;
			
		case "church3":
			church3(clientNo);		
			break;						
		default:
			assert false : "Undefined skill name.";
			break;
		}
	}
	
	
	public boolean isMessengerExist(int clientNo){
		if(this.get(clientNo) != null)
			return true;
		return false;
	}

	@Override
	public Messenger put(Integer key, Messenger value){
		Player my = cdc.pm.get(key);
		if(!isMessengerExist(key) && my.getMoney() >= par.MESSENGER_COST){
			my.addOneMessenger();
			return super.put(key, value);
		}
		else{
			assert false : "This client has already send a messenger.";
			return null;
		}
	} 
	
	private void church1(int clientNo){
		if(!cdc.getPlayer(clientNo).isLose())
			cdc.getPlayer(clientNo).increaseMoney(par.CAPITAL_SKILL_CHURCH1);
	} 
	
	private void church2(int clientNo){		
		Coordinate.getInstance().church2();
	} 
	
	private void church3(int clientNo){
		Player my = cdc.pm.getRandomPlayer();
		if(!my.isLose()){
			Tower myTower = my.getTower();
			cdc.addUpdateInfo(Encoder.getInstance().enSetEffect(par.SET_EFFECT_CHURCH3, myTower.getX(), myTower.getY()));
			myTower.hurt(par.CAPITAL_SKILL_CHURCH3, clientNo);
		}
	} 
	
	private void castle1(int clientNo){
		Player my = cdc.pm.getRandomPlayer();
		if(!my.isLose()){
			int afterMoneyLevel = my.getTower().getMoneyLevel() + par.CAPITAL_SKILL_CASTLE1;
			afterMoneyLevel = (afterMoneyLevel < 1)? 1 : afterMoneyLevel;
			my.getTower().setMoneyLevel(afterMoneyLevel);
		}
	} 

	private void castle2(int clientNo){
		Player my = cdc.pm.getRandomPlayer();
		if(!my.isLose()){
			my.decreaseMoney(par.CAPITAL_SKILL_CASTLE2);
		}
	} 

	private void castle3(int clientNo){
		Player my = cdc.getPlayer(clientNo);
		if(!my.isLose()){
			int afterAtkLevel = my.getTower().getAtkLevel() + par.CAPITAL_SKILL_CASTLE3;
			afterAtkLevel = (afterAtkLevel > par.MAX_TOWER_LEVEL)? par.MAX_TOWER_LEVEL : afterAtkLevel;
			my.getTower().setAtkLevel(afterAtkLevel);
		}
	} 
}
