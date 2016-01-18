package cof.yen.cdc;

public class MsgerCoordinate {
	public static MsgerCoordinate coor = null;
	private PositionHelper ph = null;
	private Parameter par = null;
	private CDC cdc = null;
	private Encoder coder = null;
	
	public MsgerCoordinate() {
		this.ph = PositionHelper.getInstance();
		this.par = Parameter.getInstance();
		this.cdc = CDC.getInstance();
		this.coder = Encoder.getInstance();
	}
	
	public static MsgerCoordinate getInstance(){
		if(coor == null)
			coor = new MsgerCoordinate();
		return coor;
	}
	
	public void setNextPos(Messenger msger){
		int entryNo = ph.getPlayerEntryNo(msger.getHost());
		int x = msger.getX();
		int y = msger.getY();
		int moveDist = par.UNIT_MOVE_DIST;
		switch (entryNo) {
		case 0:
			msger.setY( y + moveDist );
			break;
		case 1:
			msger.setX( x - moveDist );
			break;
		case 2:
			msger.setY( y - moveDist );
			break;
		case 3:
			msger.setX( x + moveDist );
			break;
		default:
			assert false : "No such entryNo."+entryNo;
			break;
		}
		
		if(isAtCapital(x, y)){
			removeMsger(msger);
		}
	}	
	
	private void removeMsger(Messenger msger){
		cdc.addTCPUpdateInfo(coder.enRemoveMessenger(msger.getHost()));
		cdc.addTCPUpdateInfo(coder.enSentMessengerArriveMsg(msger.getHost()));
		cdc.cc.remove(msger.getHost());
	}
	
	private boolean isAtCapital(int x, int y){
		int[] ccPos = par.CAPITAL_POS; 
		
		if(x == ccPos[0] && y == ccPos[1])
			return true;
		return false;
	}
}
