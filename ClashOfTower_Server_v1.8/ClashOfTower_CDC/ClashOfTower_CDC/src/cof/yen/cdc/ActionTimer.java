package cof.yen.cdc;

import java.util.TimerTask;

import org.omg.IOP.Codec;

public class ActionTimer extends TimerTask{
	private Coordinate coor = null;
	private MsgerCoordinate msgCoor = null;
	private CDC cdc = null;
	private Road[] roads = null;
	private Encoder coder = null;
	private PositionHelper ph = null;
	private Parameter par = null;
	private int atkCounter = 0, atkPeriod;
	private int effectCounter = 0, effectPeriod;  // 寶塔被攻打的動畫 3秒一次
	
	public ActionTimer(CDC cdc) {
		this.cdc = cdc;
		this.coor = Coordinate.getInstance();
		this.msgCoor = MsgerCoordinate.getInstance();
		this.roads = coor.road;
		this.coder = Encoder.getInstance();
		this.ph = PositionHelper.getInstance();
		this.par = Parameter.getInstance();
		this.atkPeriod = par.UPDATE_INTERVAL_1000MS / par.UPDATE_INTERVAL_200MS;
		this.effectPeriod = 3000 / par.UPDATE_INTERVAL_200MS;
	}

	@Override
	public void run() {
		updateUnit();
		updateMsger();
	}
	
	private void updateUnit(){
		for(int roadInd=0 ; roadInd<roads.length ; roadInd++){
			Road curRoad = roads[roadInd];
			for(int j=0 ; j<curRoad.size() ; j++){
				Unit myUnit = curRoad.get(j);
				coor.fight(getIsAllowAtk(), getIsTowerEffect(), roadInd, myUnit);
				coor.setRoadNextPos(roadInd, myUnit);
			}
		}
	}
	
	private void updateMsger(){
		CapitalCity cc = this.cdc.cc;
		for(int i=0 ; i<par.MAX_PLAYER_NUM ; i++){
			int clientNo = ph.getClientNoByEntryNo(i);
			if(!cc.isMessengerExist(clientNo))
				continue;
			Messenger myMsger = cc.get(clientNo);
			msgCoor.setNextPos(myMsger);
			if( cc.isMessengerExist(clientNo) )
				cdc.addUpdateInfo(coder.enSetMessengerProperty(clientNo, myMsger.getX(), myMsger.getY()));
		}
	}
	
	private boolean getIsAllowAtk(){
		if( (atkCounter++) % atkPeriod == 0)
			return true;
		return false; 
	}
	
	private boolean getIsTowerEffect(){
		if( (effectCounter++) % effectPeriod == 0)
			return true;
		return false; 
	}
}

