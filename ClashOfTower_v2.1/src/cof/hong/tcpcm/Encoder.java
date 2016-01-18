package cof.hong.tcpcm;

//此class負責將給server的各種訊息編碼成一個指令
public class Encoder {	
	public static Encoder coder = null;
	
	public static Encoder getInstance(){
		if(coder == null)
			coder = new Encoder();
		return coder;
	}
	

	public String enAddUnit(String type, int level, int clientNo, int aisle){
		String instruction_name = "addUnit"; 
		return instruction_name + "?" + type + ";" + Integer.toString(level) + ";" + Integer.toString(clientNo) + ";" + Integer.toString(aisle);
		
	}
		
	public String enAddMessenger(int clientNo){
		String instruction_name = "addMessenger";
		return instruction_name + "?" + clientNo;
		
	}
	
	public String enCallTowerSkill(int clientNo){
		String instruction_name = "callTowerSkill";
		return instruction_name + "?" + clientNo;
	}
	
	public String enCallTowerUpgrade(int clientNo, String updateName){
		String instruction_name = "callTowerUpgrade";
		return instruction_name + "?" + clientNo + ";" + updateName;
	}
	
	public String enCallCapitalCitySkill(int clientNo, String skillName){
		String instruction_name = "callCapitalCitySkill";
		return instruction_name + "?" + clientNo + ";" + skillName;
	}
	
	public String enUpgradeUnit(int clientNo, int cost){
		String instruction_name = "upgradeUnit";
		return instruction_name + "?" + clientNo + ";" + cost;
	}	
}
