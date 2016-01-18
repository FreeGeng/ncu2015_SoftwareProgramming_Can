package cof.yen.cdc;

import java.util.ArrayList;
import java.util.logging.Level;

public class ExternUtility {
	private CDC cdc;
	private Parameter parameter = null;
	private SerialNumGenerator sng = null; 
	private PositionHelper ph = null;
	private Encoder coder = null;
	
	public ExternUtility(CDC cdc){
		this.cdc = cdc;
		this.parameter = Parameter.getInstance();
		this.sng = SerialNumGenerator.getInstance();
		this.ph = PositionHelper.getInstance();
		this.coder = Encoder.getInstance();
	}
	
	public void addPlayer(int clientNo) {
		assert cdc.getCurPlayerNum() < parameter.MAX_PLAYER_NUM : "greater than "+parameter.MAX_PLAYER_NUM;
		ph.add(clientNo);
		cdc.addNewPlayerNum();
		cdc.pm.put(clientNo, new Player(clientNo, parameter.INIT_MONEY_UP_RATE, parameter.INIT_MONEY));
	}

	public void addUnit(String type, int level, int clientNo, int aisle) {
		Player my = cdc.pm.get(clientNo); 
		int typeIndex = typeToIndex(type);
		if(typeIndex >= parameter.INIT_UNIT_ATTRI_SET.length-1){		// 把Hero0~3 轉為 type=Hero且 level=0~3
			level = typeIndex - (parameter.INIT_UNIT_ATTRI_SET.length-1);
			typeIndex -= level;
		}			
		assert level<=parameter.MAX_UNIT_LEVEL : "over unit max level.";
		int hp  = parameter.INIT_UNIT_ATTRI_SET[typeIndex][0][level];
		int atk = parameter.INIT_UNIT_ATTRI_SET[typeIndex][1][level];
		int def = parameter.INIT_UNIT_ATTRI_SET[typeIndex][2][level];
		int agi = parameter.INIT_UNIT_ATTRI_SET[typeIndex][3][level];
		int cost = parameter.INIT_UNIT_ATTRI_SET[typeIndex][4][level];
		if(cost > my.getMoney() || my.getUnitQuantity() >= parameter.MAX_UNIT_NUM)
			return;			
		String direction = ph.getUnitDirection(clientNo, aisle);
		String action = parameter.UNIT_ACTION_WALK;
		int serialNumber = sng.getNextSN();
		int[] pos = ph.getTowerPos(clientNo);
		Unit myUnit = new Unit(my, type, direction, action, clientNo, hp, atk, def, level, serialNumber, aisle, pos[0], pos[1], agi);
		my.um.add(myUnit);
		Coordinate.getInstance().addUnitToRoad(aisle2RoadIndex(clientNo, aisle), myUnit);
		cdc.addTCPUpdateInfo(coder.enAddUnit(clientNo, serialNumber, type, level, pos[0], pos[1], action, direction));
		
		if(typeIndex > 2)
			my.addOneHero(cost);
		else 
			my.addOneUnit(cost);
	}
	
	public void upgradeUnit(int clientNo, int level) {
		int cost = parameter.UNIT_UPGRADE_COST[level - 1];
		if(cdc.pm.get(clientNo).getMoney() >= cost)
			cdc.pm.get(clientNo).decreaseMoney(cost);
	}

	public void addMessenger(int clientNo) {
		Player my = cdc.getPlayer(clientNo);
		if(parameter.MESSENGER_COST > my.getMoney() || cdc.cc.isMessengerExist(clientNo))
			return;			
		
		int initPos[] = ph.getTowerPos(clientNo);
		int serialNumber = sng.getNextSN();
		cdc.cc.put(clientNo, new Messenger(initPos[0], initPos[1], clientNo, serialNumber ));
		cdc.addTCPUpdateInfo(coder.enAddMessenger(clientNo, initPos[0], initPos[1]));
	}

	public void callCapitalCitySkill(int clientNo, String skillName) {
		cdc.cc.skill(clientNo, skillName);
	}

	public void callTowerSkill(int clientNo) {
		Coordinate.getInstance().callTowerSkill(clientNo);
	}

	public void callTowerUpgrade(int clientNo, String upgradeName) {
		TowerManager myTm = cdc.pm.get(clientNo).tm;
		if(!myTm.isEmpty())
			myTm.get(0).upgrade(upgradeName);
	}

	public ArrayList<String> getUpdateInfo() {
		ArrayList<String> result = (ArrayList<String>) cdc.updateInfo.clone(); 
		cdc.updateInfo.clear();
		return result;
	}
	
	private int typeToIndex(String type){
		int result = 0;
		switch (type) {
		case "Human":
			result = 0;
			break;
		case "Wolf":
			result = 1;
			break;
		case "Ork":
			result = 2;
			break;
		case "Gerald":
			result = 3;
			break;
		case "Denas":
			result = 4;
			break;
		case "Mailk":
			result = 5;
			break;
		case "Ingvar":
			result = 6;
			break;
		default:
			assert false:"No such type "+type ;
		}
		return result;		
	}
	
	
	private int aisle2RoadIndex(int clientNo, int aisle){
		assert (aisle <= parameter.AISLE_NUM) : "Aisle must be under "+parameter.AISLE_NUM;
		int entryNo = ph.getPlayerEntryNo(clientNo);
		
		int roadIndex = 0;
		roadIndex = aisle + entryNo;
		if(entryNo == 3 && aisle == 1)
			roadIndex = 0;
		return roadIndex; 
	}
}
