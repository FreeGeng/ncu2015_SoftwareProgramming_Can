package cof.yen.cdc;

public class Coordinate {
	public static Coordinate coordinate = null;
	public Road[] road = null;
	private Parameter par = null;
	private CDC cdc = null;
	private PositionHelper ph = null;
	private Encoder coder = null;
	
	public Coordinate() {
		par = Parameter.getInstance();
		ph = PositionHelper.getInstance();
		coder = Encoder.getInstance();
		cdc = CDC.getInstance();
		initRoad();		//雙向道路	road的index除以2會等於aisle值
	}
		
	public static Coordinate getInstance(){
		if(coordinate == null)
			coordinate = new Coordinate();
		return coordinate;
	}	
	
	public void setRoadNextPos(int roadIndex, Unit unit){
		int x = unit.getX();
		int y = unit.getY();
		int blockX = getBlockSize(x);
		int blockY = getBlockSize(y);
		int movedist = par.UNIT_MOVE_DIST;
		String direction = unit.getDirection();
		
		
		if( !isLegalRoad(blockX, blockY) ){
			assert false:"isn't on legal road.";
			return;
		}
		
		if(unit.getAction() != par.UNIT_ACTION_WALK)
			return;
		
		int cornerIndex = isAtCorner(x, y);
		if(cornerIndex != -1){			// 在四個角落時
			switch (cornerIndex) {
			case 0:			// road 0, 1
				if(roadIndex % 2 == 0){		// 順時針
					unit.setX( x + movedist );
				}else {
					unit.setY( y + movedist );
				}				
				break;
			case 1:			// road 2, 3
				if(roadIndex % 2 == 0){		// 順時針
					unit.setY( y + movedist );
					unit.setDirection(par.UNIT_DIRECTION_LEFT);
				}else {
					unit.setX( x - movedist );
					unit.setDirection(par.UNIT_DIRECTION_LEFT);
				}		
				break;
			case 2:			// road 4, 5
				if(roadIndex % 2 == 0){		// 順時針
					unit.setX( x - movedist );
				}else {
					unit.setY( y - movedist );
				}
				break;
			case 3:			// road 6, 7
				if(roadIndex % 2 == 0){		// 順時針
					unit.setY( y - movedist );
					unit.setDirection(par.UNIT_DIRECTION_RIGHT);
				}else {
					unit.setX( x + movedist );
					unit.setDirection(par.UNIT_DIRECTION_RIGHT);
				}
				break;
			}
		}
		else{						// 在四條直路時
			int isAtStraight = isAtStraight(x, y);
			switch(isAtStraight){
			case 0: 			
			case 2: 		// 往Y軸移動
				if( direction == par.UNIT_DIRECTION_RIGHT ){		
					unit.setX( x+movedist );
				}else{
					unit.setX( x-movedist );
				}
				break;
			case 1: 
			case 3: 		//往X軸方向移動
				if( direction == par.UNIT_DIRECTION_RIGHT ){
					unit.setY( y-movedist );
				}else{
					unit.setY( y+movedist );
				}
				break;
			default:
				assert false: "is not on stright";
			}
		} 
		
		int isAtTower = isAtTower(unit.getX(), unit.getY());
		int newRoadIndex = 0;
		if(isAtTower != -1){
			if(roadIndex % 2 ==0){
				newRoadIndex = (roadIndex + 2) % 8;
			}
			else{
				newRoadIndex = (roadIndex + 6) % 8;
			}		
			road[newRoadIndex].add(unit);	
			road[roadIndex].removeBySerialNum(unit.getSerialNumber());
			unit.setAisle(((int) (newRoadIndex/2))+1);
		}		
		updateUnitProperty(unit);
	}
	
	
	public void setRoadPrePos(int roadIndex, Unit unit){
		int x = unit.getX();
		int y = unit.getY();
		int blockX = getBlockSize(x);
		int blockY = getBlockSize(y);
		int movedist = par.UNIT_MOVE_DIST;
		String direction = unit.getDirection();
		
		
		if( !isLegalRoad(blockX, blockY) ){
			assert false:"isn't on legal road.";
			return;
		}
		
		unit.setAction(par.UNIT_ACTION_BREAK);
		
		int cornerIndex = isAtCorner(x, y);
		if(cornerIndex != -1){			// 在四個角落時
			switch (cornerIndex) {
			case 0:			// road 0, 1
				if(roadIndex % 2 == 0){		// 順時針
					unit.setY( y + movedist );
				}else {
					unit.setX( x + movedist );
				}				
				break;
			case 1:			// road 2, 3
				if(roadIndex % 2 == 0){		// 順時針
					unit.setX( x - movedist );
					unit.setDirection(par.UNIT_DIRECTION_RIGHT);
				}else {
					unit.setY( y + movedist );
					unit.setDirection(par.UNIT_DIRECTION_RIGHT);
				}		
				break;
			case 2:			// road 4, 5
				if(roadIndex % 2 == 0){		// 順時針
					unit.setY( y - movedist );
				}else {
					unit.setX( x - movedist );
				}
				break;
			case 3:			// road 6, 7
				if(roadIndex % 2 == 0){		// 順時針
					unit.setX( x + movedist );
					unit.setDirection(par.UNIT_DIRECTION_LEFT);
				}else {
					unit.setY( y - movedist );
					unit.setDirection(par.UNIT_DIRECTION_LEFT);
				}
				break;
			}
		}
		else{						// 在四條直路時
			int isAtStraight = isAtStraight(x, y);
			switch(isAtStraight){
			case 0: 			
			case 2: 		// 往Y軸移動
				if( direction == par.UNIT_DIRECTION_RIGHT ){
					unit.setX( x-movedist );
				}else{
					unit.setX( x+movedist );
				}
				break;
			case 1: 
			case 3: 		//往X軸方向移動
				if( direction == par.UNIT_DIRECTION_RIGHT ){
					unit.setY( y+movedist );
				}else{
					unit.setY( y-movedist );
				}
				break;
			default:
				assert false: "is not on stright";
			}
		}		
		updateUnitProperty(unit);
	}
	
	
	public void fight(boolean isAllowAtk, boolean isAllowEffect ,int myRoadIndex, Unit myUnit){		// 1秒一次
		int opposeRoadIndex = 0, opposeEntryNo = 0, opposeClientNo = 0;
		
		assert myRoadIndex < par.ROAD_NUM : "MyRoadNum must be under "+par.ROAD_NUM;
		if(myRoadIndex % 2 == 0){		// 我方若是順時針的士兵
			opposeEntryNo = myRoadIndex / 2 ;		
			opposeRoadIndex = myRoadIndex + 1;			
		}else{						// 我方若是逆時針的士兵
			opposeEntryNo = convertRoadIndex2EntryNo( myRoadIndex );	
			opposeRoadIndex = myRoadIndex - 1;						
		}
		
		Road opposeRoad = this.road[opposeRoadIndex];
		Unit oppose = myUnit.testCollision(opposeRoad);
		if(oppose != null){									//偵測是否有兵可以打   找到敵兵可以打
			myUnit.setAction(par.UNIT_ACTION_ATTACK);
			if(isAllowAtk){
				updateUnitProperty(myUnit);
				oppose.hurt(myUnit.getAtk());
				checkAfterHurt(oppose, opposeRoad, opposeRoadIndex);
			}
			return;
		}

		opposeClientNo = ph.getClientNoByEntryNo(opposeEntryNo);		// 偵測是否有塔可以打
		if(!cdc.getPlayer(opposeClientNo).isLose()){		// 沒兵可打  轉而打塔
			Tower opposeTower = cdc.getPlayer(opposeClientNo).getTower();
			if( myUnit.isInAtkRange(opposeTower.getX(), opposeTower.getY()) ){
				myUnit.setAction(par.UNIT_ACTION_ATTACK);
				if(isAllowAtk){
					if(isAllowEffect){
						cdc.addUpdateInfo(coder.enSetEffect(par.SET_EFFECT_TOWERATK, opposeTower.getX(), opposeTower.getY()));
					}
					updateUnitProperty(myUnit);
					opposeTower.hurt(myUnit.getAtk(), myUnit.getHost());
				}
				return;
			}				
		}		
		myUnit.setAction(par.UNIT_ACTION_WALK);
	}
	
	
	public void callTowerSkill(int clientNo){
		Tower myTower = cdc.getPlayer(clientNo).getTower();
		int[] target = findTowerAtkRoad(clientNo);
		for(int i=0 ; i<target.length ; i++){
			int rivalRoadInd = target[i];
			Road rivalRoad = this.road[rivalRoadInd];
			for(int j=0 ; j<rivalRoad.size() ; j++){
				Unit rival = rivalRoad.get(j);
				if( myTower.isInAtkRange(rival.getX(), rival.getY()) ){
					cdc.addUpdateInfo(coder.enSetEffect(par.SET_EFFECT_CHURCH2, rival.getX(), rival.getY()));
					rival.hurt(myTower.getAtk());
					checkAfterHurt(rival, rivalRoad, rivalRoadInd);
				}					
			}
		}
	}
	
	public void church2(){
		int[] target = findRandomAtkAisle(); 
		for(int i=0 ; i<target.length ; i++){
			int rivalRoadInd = target[i];
			Road rivalRoad = this.road[rivalRoadInd];
			while(rivalRoad.size() != 0){
				Unit rival = rivalRoad.get(0);
				cdc.addUpdateInfo(coder.enSetEffect(par.SET_EFFECT_CHURCH2, rival.getX(), rival.getY()));
				rival.hurt(par.CAPITAL_SKILL_CHURCH2);	
				checkAfterHurt(rival, rivalRoad, rivalRoadInd);
			}
		}
	} 
	
	public void removeAllUnits(int clientNo){
		for(int i=0 ; i<road.length ; i++){
			System.out.println(road[i].size());
			for(int j=0 ; j<road[i].size() ; j++){
				Unit curUnit = road[i].get(j);
				if(curUnit.getHost() == clientNo){
					cdc.addUpdateInfo(coder.enSetEffect(par.SET_EFFECT_CHURCH2, curUnit.getX(), curUnit.getY()));
					curUnit.hurt(par.CAPITAL_SKILL_CHURCH2);	
					checkAfterHurt(curUnit, road[i], i);
					j--;
				}					
			}
		}
	}
	
	
	private void checkAfterHurt(Unit rival, Road rivalRoad, int rivalRoadInd){
		if(rival.isKilled()){
			rivalRoad.removeBySerialNum(rival.getSerialNumber());	
			return;
		}		
		if( !rival.isKilled() && rival.isBreak() ){				// 擊退
			rival.goBreaked();
			if( isAtTower(rival.getX(), rival.getY()) == -1 && getDistToTower(rival.getX(), rival.getY()) > (par.UNIT_BREAK_DIST+40) ){
				int breakTime = par.UNIT_BREAK_DIST / par.UNIT_MOVE_DIST;
				for(int i=0 ; i<breakTime ; i++)
					setRoadPrePos( rivalRoadInd ,  rival );		// 後退多個pixel
			}
		}
	}
	
	
	private int[] findTowerAtkRoad(int clientNo){		// for tower skill
		int entryNo = ph.getPlayerEntryNo(clientNo);
		int[] result = new int[2];
		result[0] = entryNo * 2;
		result[1] = (result[0] + 3) % 8;		
		return result;
	}
	
	private int[] findRandomAtkAisle(){
		int[] result = new int[2];
		int random = (int)(Math.random()*par.MAX_PLAYER_NUM) ;
		result[0] = random * 2;
		result[1] = result[0] + 1;
		return result;
	}
	
	private int getDistance(int x1, int y1, int x2, int y2){
		return (int) Math.sqrt(Math.pow( x1-x2, 2) + Math.pow( y1-y2, 2));
	}
	
	private int getDistToTower(int x, int y){
		int min = 99999;
		int[][] towerPos = Parameter.getInstance().INIT_TOWER_POS;
		for( int i=0 ; i<towerPos.length ; i++){
			int tmp = getDistance(towerPos[i][0], towerPos[i][1], x, y);
			if( tmp < min )
				min = tmp;
		}		
		return min;
	}
	
	
	private void updateUnitProperty(Unit myUnit){
		cdc.addUpdateInfo(coder.enSetUnitProperty(myUnit.getHost(), myUnit.getSerialNumber(), myUnit.getX()	, myUnit.getY(), myUnit.getDirection(), myUnit.getAction()));
	}
	
	private void initRoad(){
		this.road = new Road[par.ROAD_NUM];
		for(int i=0; i<road.length ; i++)
			road[i] = new Road();
	}
	
	private int convertRoadIndex2EntryNo(int index){
		int result = 0;
		switch(index){
		case 1:
			result = 3;
			break;
		case 3:
			result = 0;
			break;
		case 5:
			result = 1;
			break;
		case 7:
			result = 2;
			break;
		default:
			assert false : "No such road index." ;
		}
		return result;
	}	
	
	
	private int isAtTower(int x, int y){
		int[][] towerPos = par.INIT_TOWER_POS;
		for( int i=0 ; i<towerPos.length ; i++){
			if(towerPos[i][0] == x && towerPos[i][1] == y)
				return i;
		}
		return -1;				// 不在塔上
	} 
		
	private int isAtStraight(int x, int y){
		int[] up = par.CORNER_UP;
		int[] right = par.CORNER_RIGHT;
		int[] down = par.CORNER_DOWN;
		int[] left = par.CORNER_LEFT;
		
		if( x > up[0] && x < right[0] && y == up[1] )		// 上方走道
			return 0;
		if( x == right[0] && y < down[1] && y > right[1] )		// 右方走道
			return 1;
		if( x > left[0] && x < down[0] && y == left[1] )		// 下方走道
			return 2;
		if( x == up[0] && y < left[1] && y > up[1] )		// 左方走道
			return 3;
		assert false : "is not on the straight road.";
		return -1; 	// ERROR
	}
	
	private int isAtCorner(int x, int y){
		int[][] corner = par.CORNER_POS;
		if(x == y){
			if(x == corner[0][0])
				return 0;
			return 2;
		}

		if(x == corner[1][0] && y == corner[1][1])
			return 1;
		if(x == corner[3][0] && y == corner[3][1])
			return 3;
		
		return -1;		// error, not at corner
	}
	
	private boolean isLegalRoad(int blockX, int blockY){		// 將pixel 轉block

		if(blockX == par.MARGIN_X_LEFT_BLOCK || blockX== par.MARGIN_X_RIGHT_BLOCK)
			if( blockY >= par.MARGIN_Y_UP_BLOCK && blockY <= par.MARGIN_Y_DOWN_BLOCK)
				return true;
		if(blockY == par.MARGIN_Y_UP_BLOCK || blockY == par.MARGIN_Y_DOWN_BLOCK)
			if( blockX >= par.MARGIN_X_LEFT_BLOCK && blockX <= par.MARGIN_X_RIGHT_BLOCK)
				return true;
		return false;
	}
	
	private int getBlockSize(int x){
		return x/par.MAP_BLOCK_SIZE;
	}
	
	
	public void addUnitToRoad(int index, Unit unit){
		this.road[index].add(unit);
	}
	
	
	/*
	public void addNewUnit(){
		// 每當新稱一個unit後 要把這個unit加到vector裡
	}
	
	public void startUpdateUnitPos(){
			// add a thread to update location 
			// fighting()
	}	
	
	private void fighting(){
		// testCollision();
		// if(unit.isBreak())  then setPreposition(unit) and unit.goBreaked();
	}
	
	public void setNextStepPosition(Unit myUnit){
			// need to add a new update command
	}

	public void setPreStepPosition(Unit myUnit){
			// need to add a new update command
	}
	*/
}
