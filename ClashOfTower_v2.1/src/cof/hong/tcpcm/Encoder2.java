package cof.hong.tcpcm;

//��class�t�d�N��server���U�ذT���s�X���@�ӫ��O
public class Encoder2 {
	public AddUnit addUnit;
	public AddMessenger addMessenger;
	public CallTowerSkill callTowerSkill;
	public CallTowerUpgrade callTowerUpgrade;
	public CallCapitalCitySkill callCapitalCitySkill;
	public UpgradeUnit upgradeUnit;
}

class AddUnit{
	private String instruction_name = "addUnit"; 
	private String type;
	private int level;
	private int clientNo;
	private int aisle;
	public AddUnit(String type, int level, int clientNo, int aisle){
		this.type = type;
		this.level = level;
		this.clientNo = clientNo;
		this.aisle = aisle;
	}
	
	public String encodeAddUnit(){
		return instruction_name + "?" + type + ";" + Integer.toString(level) + ";" + Integer.toString(clientNo) + ";" + Integer.toString(aisle);
	}
}

class AddMessenger{
	private String instruction_name = "addMessenger";
	private int clientNo;
	public AddMessenger(int clientNo){
		this.clientNo = clientNo;
	}
	
	public String encodeAddMessenger(){
		return instruction_name + "?" + clientNo;
	}
}

class CallTowerSkill{
	private String instruction_name = "callTowerSkill";
	private int clientNo;
	public CallTowerSkill(int clientNO){
		this.clientNo = clientNO;
	}
	
	public String encodeCallTowerSkill(){
		return instruction_name + "?" + clientNo;
	}
}

class CallTowerUpgrade{
	private String instruction_name = "callTowerUpgrade";
	private int clientNo;
	// updateName : hpRecover, moneyUpfaster, towerAtkup
	String updateName;
	public CallTowerUpgrade(int clientNo, String updateName){
		this.clientNo = clientNo;
		this.updateName = updateName;
	}
	
	public String encodeTowerUpgrade(){
		return instruction_name + "?" + clientNo + ";" + updateName;
	}
}

class CallCapitalCitySkill{
	private String instruction_name = "callCapitalCitySkill";
	private int clientNo;
	// skillName : capital1 ~ capital3 , church1 ~ church3
	String skillName;
	public CallCapitalCitySkill(int clientNo, String skillName){
		this.clientNo = clientNo;
		this.skillName = skillName;
	}
	
	public String encodeCapitalCitySkill(){
		return instruction_name + "?" + clientNo + ";" + skillName;
	}
}

class UpgradeUnit{
	private String instruction_name = "upgradeUnit";
	private int clientNo;
	private int cost;
	
	public UpgradeUnit(int clientNo, int cost){
		this.clientNo = clientNo;
		this.cost = cost;
	}
	
	public String encodeUpgradeUnit(){
		return instruction_name + "?" + clientNo + ";" + cost;
	}
}
