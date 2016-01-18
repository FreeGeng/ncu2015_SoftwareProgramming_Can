package cof.yen.cdc;

import java.util.TimerTask;

public class ClockTimer extends TimerTask{
	private CDC cdc = null;
	private Encoder coder = null;
	private Parameter parameter = null;
	private int remainTime;
	
	public ClockTimer(CDC cdc) {
		this.cdc = cdc;
		this.coder = Encoder.getInstance();
		this.parameter = Parameter.getInstance();
		
		this.remainTime = parameter.MAX_GAME_TIME;
	}

	@Override
	public void run() {
		
		decreaseTime();
		addUpdateInfo(coder.enSetGameTime(remainTime));
		if(getExistPlayerNum() <= 1 || remainTime <= 0){
			cdc.addTCPUpdateInfo(coder.enSentEndScore(cdc.getEndScore()));
			cdc.endUpdatingThread();
			return;
		}		

		
		for(int clientNo : cdc.pm.keySet()){
			Player my = cdc.getPlayer(clientNo);
			if(!my.isLose()){
				my.autoAddMoney();
			}
			else{
				Coordinate.getInstance().removeAllUnits(clientNo);
			}
		}
	}
	
	private int getExistPlayerNum(){
		int result = 0;
		for(int clientNo : cdc.pm.keySet()){
			if(!cdc.getPlayer(clientNo).isLose()){
				result++;
			}
		}
		return result;
	}
	

	private void addUpdateInfo(String instruction){
		cdc.addUpdateInfo(instruction);
	}
	
	public void decreaseTime(){
		this.remainTime--;
	}
}
