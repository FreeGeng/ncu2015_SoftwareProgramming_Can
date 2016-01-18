package cof.yen.cdc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import cof.hong.tcpsm.WriteMessage;
import cof.server.interfaces.CentralDataCenter;

public class CDC implements CentralDataCenter{
	//public:
	public static CDC cdc = null;
	public PlayerManager pm = null;
	public CapitalCity cc = null; 
	public ArrayList<String> updateInfo = null; 
	
	//private:
	private Timer updating = null; 
	private ExternUtility utility = null;
	private Parameter parameter = null;
	private Algorithm alg = null;
	private Encoder coder = null;
	private PositionHelper ph = null;
	private int curPlayerNum = 0;
	
/*---------------------------------------------------------------------------*/
	public CDC(){
		parameter = Parameter.getInstance();
		ph = PositionHelper.getInstance();
		updateInfo = new ArrayList<String>();
		pm = new PlayerManager();
		utility = new ExternUtility(this);
		alg = new Algorithm(this);
		cc  = new CapitalCity(this);
		coder = Encoder.getInstance();
	}
	
	public static CDC getInstance(){
		if(cdc == null)
			cdc = new CDC();
		return cdc;
	}
	

/*---------------------------------------------------------------------------*/

	private void startUpdatingThread() {
		assert this.pm.size() == parameter.MAX_PLAYER_NUM : "Player number is under "+parameter.MAX_PLAYER_NUM;
		updating = new Timer();
		updating.schedule(new ClockTimer(this), parameter.UPDATE_INTERVAL_1000MS, parameter.UPDATE_INTERVAL_1000MS);
		updating.schedule(new ActionTimer(this), parameter.UPDATE_INTERVAL_200MS, parameter.UPDATE_INTERVAL_200MS);
	}
	
	public void endUpdatingThread(){
		this.updating.cancel();
	}

	public ArrayList<EndScore> getEndScore() {
		return alg.getEndScore();
	}
	
	
	public Player getPlayer(int clientNo){
		if(cdc.pm.get(clientNo) == null){
			assert false : "No such client"+clientNo;
			return null;
		}
		return cdc.pm.get(clientNo);
	}	
	
	public Messenger getMessenger(int clientNo){
		Messenger myMsger = this.cc.get(clientNo); 
		return myMsger;
	}
	
	public int getCurPlayerNum() {
		return curPlayerNum;
	}

	public void addNewPlayerNum() {
		this.curPlayerNum++;
	}
	
	public void addUpdateInfo(String instruction){
		updateInfo.add(instruction);
		//SystemMsg.getInstance().out(instruction);
	}
	
	public void addTCPUpdateInfo(String instruction){
		try {
			WriteMessage.getInstance().writeMsgToAllClient(instruction);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer, int[]> getInitTowerPos(){
		HashMap<Integer, int[]> result = new HashMap<Integer, int[]>();
		for(int clientNo : this.pm.keySet()){
			result.put(clientNo, ph.getTowerPos(clientNo));
		}		
		return result;
	}
/*---------------------------------------------------------------------------*/
	
	@Override
	public void addPlayer(int clientNo) {
		utility.addPlayer(clientNo);
	}

	@Override
	public void addUnit(String type, int level, int clientno, int aisle) {
		utility.addUnit(type, level, clientno, aisle);
	}
	@Override
	public void addMessenger(int clientNo) {
		utility.addMessenger(clientNo);
	}
	@Override
	public void startGame() {
		addTCPUpdateInfo(coder.enInitTowerPosition(getInitTowerPos()));
		startUpdatingThread();
	}
	@Override
	public void callCapitalCitySkill(int clientNo, String skillName) {
		utility.callCapitalCitySkill(clientNo, skillName);
	}
	@Override
	public void callTowerSkill(int clientNo) {
		utility.callTowerSkill(clientNo);
	}
	@Override
	public void callTowerUpgrade(int clientNo, String updateName) {
		utility.callTowerUpgrade(clientNo, updateName);
	}
	@Override
	public ArrayList<String> getUpdateInfo() {
		return utility.getUpdateInfo();
	}

	@Override
	public void upgradeUnit(int clientNo, int level) {
		utility.upgradeUnit(clientNo, level);
	}
}
