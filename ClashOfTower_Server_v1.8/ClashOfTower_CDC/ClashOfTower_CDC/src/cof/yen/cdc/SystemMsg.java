package cof.yen.cdc;

public class SystemMsg {
	
	public static SystemMsg msg = null;

	public SystemMsg() {

	}
	
	public static SystemMsg getInstance(){
		if(msg == null)
			msg = new SystemMsg();
		return msg;
	}
	/*
	public void outBreakTower(int no, int broker){
		out("A tower "+no+" is broken by "+ broker +"!");
	}	

	public void outUnitBreak(int sn){
		out("Unit "+sn+" breaked!");
	} 
	
	public void outUnitHP(int sn, int hp){
		out("Unit"+sn+" HP: "+ hp);
		
	}
	public void outKillUnit(){
		out("A unit is killed!");
	}
	
	public void outChangeRoad(int old, int news){
		out("Change from "+old +" to "+news);
	}
	
	public void out(String msg){
		System.out.println(msg);
	}
	public void out(int msg){
		System.out.println(msg);
	}*/
}
