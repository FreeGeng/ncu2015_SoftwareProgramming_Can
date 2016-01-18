package cof.hong.tcpsm;

import cof.server.interfaces.CentralDataCenter;

//此class負責將從client傳送過來的編碼指令解碼，並呼叫CDC內相對應的function
public class Decoder {
	private CentralDataCenter cdc;
	private String decode[]; 
	
	public Decoder(CentralDataCenter cdc){
		this.cdc = cdc;
	}
	
	public String splitInstruction(String decodeString){
		decode = decodeString.split("\\?");
		return decode[0];
	}
	
	public void decodeAddUnit(String decodeString){
//		System.out.println(decodeString.split("\\?")[1].split(";")[0] + 
//		" " + Integer.valueOf(decodeString.split("\\?")[1].split(";")[1])+
//		" " + Integer.valueOf(decodeString.split("\\?")[1].split(";")[2]) + 
//		" " + Integer.valueOf(decodeString.split("\\?")[1].split(";")[3]));
		cdc.addUnit(decodeString.split("\\?")[1].split(";")[0], 
				Integer.valueOf(decodeString.split("\\?")[1].split(";")[1]), 
				Integer.valueOf(decodeString.split("\\?")[1].split(";")[2]), 
				Integer.valueOf(decodeString.split("\\?")[1].split(";")[3]));
	}
	
	public void decodeAddMessenger(String decodeString){
		//System.out.println(Integer.valueOf(decodeString.split("\\?")[1]));
		cdc.addMessenger(Integer.valueOf(decodeString.split("\\?")[1]));
	}
	
	public void decodeCallTowerSkill(String decodeString){
		//System.out.println(Integer.valueOf(decodeString.split("\\?")[1]));
		cdc.callTowerSkill(Integer.valueOf(decodeString.split("\\?")[1]));
	}
	
	public void decodeCallTowerUpgrade(String decodeString){
//		System.out.println(Integer.valueOf(decodeString.split("\\?")[1].split(";")[0]) + " "
//				+ decodeString.split("\\?")[1].split(";")[1]);
		cdc.callTowerUpgrade(Integer.valueOf(decodeString.split("\\?")[1].split(";")[0]), 
				decodeString.split("\\?")[1].split(";")[1]);
	}
	
	public void decodeCallCapitalCitySkill(String decodeString){
//		System.out.println(Integer.valueOf(decodeString.split("\\?")[1].split(";")[0]) + " "
//				+ decodeString.split("\\?")[1].split(";")[1]);
		cdc.callCapitalCitySkill(Integer.valueOf(decodeString.split("\\?")[1].split(";")[0]), 
				decodeString.split("\\?")[1].split(";")[1]);
	}
	
	public void decodeUpgradeUnit(String decodeString){
//		System.out.println(Integer.valueOf(decodeString.split("\\?")[1].split(";")[0]) + " "
//				+ Integer.valueOf(decodeString.split("\\?")[1].split(";")[1]));
		cdc.upgradeUnit(Integer.valueOf(decodeString.split("\\?")[1].split(";")[0]),
				Integer.valueOf(decodeString.split("\\?")[1].split(";")[1]));
	}
}
