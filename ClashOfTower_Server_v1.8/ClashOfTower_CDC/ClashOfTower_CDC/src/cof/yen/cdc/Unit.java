package cof.yen.cdc;

public class Unit {
	private String type, direction, action;
	private int  host, hp, atk, def, level, serialNumber, aisle, x, y, agi, breakValue; 
	private Parameter parameter = null;
	private Player myHost = null;
	
	/*public Unit(){
		// Do nothing
		parameter = Parameter.getInstance();
	}*/
	
	public Unit(Player myHost, String type, String direction, String action, int host, int hp,	int atk, int def, int level,
				int serialNumber, int aisle, int x,	int y, int agi) {
		this.parameter = Parameter.getInstance();
		
		this.myHost = myHost;
		this.type = type;
		this.direction = direction;
		this.action = action;
		this.host = host;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.level = level;
		this.serialNumber = serialNumber;
		this.aisle = aisle;
		this.x = x;
		this.y = y;
		this.agi = agi;
		this.breakValue = def;
		// coordinate 那邊的vector要加入這個unit
	}

	public void hurt(int rivalAtk){
		int result = hp - rivalAtk;
		if(result <= 0){
			result = 0;
		}
		setHp(result);
		
		if(this.isKilled()){
			if( isHero() )
				myHost.removeOneHero(this.serialNumber);
			else
				myHost.removeOneUnit(this.serialNumber);
			return;
		}
		
		result = breakValue - rivalAtk;		
		if( result < 0 )
			result = 0;
		setBreakValue(result);
	}
	
	private boolean isHero(){
		if(this.type == parameter.UNIT_TYPE_ORK || this.type == parameter.UNIT_TYPE_WARRIOR || this.type == parameter.UNIT_TYPE_WOLF)
			return false;
		return true;
	}
	
	public boolean isKilled(){
		if(this.hp <= 0)
			return true;
		return false;
	}
	
	public boolean isBreak(){
		if(breakValue <= 0)
			return true;
		return false;
	}
	
	public void goBreaked(){
		breakValue = def;
	}
		
	public Unit testCollision(Road road){			//	對面有一堆兵  打最早加入road 且射程內的敵兵
		int opposeX = 0;
		int opposeY = 0;
		
		for(int i=0 ; i<road.size() ; i++){
			Unit oppose = road.get(i);
			opposeX = oppose.getX();
			opposeY = oppose.getY();
			if( isInAtkRange(opposeX, opposeY) )
				return oppose;
		}		
		return null;
	}
	
	
	public boolean isInAtkRange(int opposeX, int opposeY){
		int x = this.x;
		int y = this.y;
		if( parameter.UNIT_ATK_DIST >= Math.sqrt(Math.pow( x-opposeX, 2) + Math.pow( y-opposeY, 2)))
			return true;
		return false;
	}

/*---------------------------------------------------------------------------*/	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getHost() {
		return host;
	}

	public void setHost(int host) {
		this.host = host;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getAisle() {
		return aisle;
	}

	public void setAisle(int aisle) {
		this.aisle = aisle;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAgi() {
		return agi;
	}

	public void setAgi(int agi) {
		this.agi = agi;
	}

	public int getBreakValue() {
		return breakValue;
	}

	public void setBreakValue(int breakValue) {
		this.breakValue = breakValue;
	}
	
	@Override
	public String toString() {
		return "Unit [type=" + type + ", direction=" + direction + ", action="
				+ action + ", host=" + host + ", hp=" + hp + ", atk=" + atk
				+ ", def=" + def + ", level=" + level + ", serialNumber="
				+ serialNumber + ", aisle=" + aisle + ", x=" + x + ", y=" + y
				+ ", agi=" + agi + ", breakValue=" + breakValue + "]";
	}
}
