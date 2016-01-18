package cof.yen.cdc;

public class Tower {
	private String type;
	private int x, y, serialNumber, host, atk, range, hp, moneyLevel, atkLevel;
	private CDC cdc;
	private Parameter parameter;
	private Player myHost = null;
	
	/*public Tower() {
		cdc = CDC.getInstance();
		parameter = Parameter.getInstance();
	}*/

	public Tower(Player myHost, int x, int y, int serialNumber, int host, int atk, int range, int hp) {
		cdc = CDC.getInstance();
		parameter = Parameter.getInstance();
		
		this.type = parameter.TOWER_TYPE;
		this.x = x;
		this.y = y;
		this.serialNumber = serialNumber;
		this.host = host;
		this.atk = atk;
		this.range = range;
		setHp( hp );		
		this.moneyLevel = 1;
		this.atkLevel = 1;
		this.myHost = myHost;
	}
	
	public void upgrade(String upgradeName){
		switch(upgradeName){
		case "hpRecover":
			hpRecover();
			break;
		case "moneyUpFaster":
			moneyUpFaster();
			break;
		case "towerAtkUp":
			towerAtkUp();
			break;
		default:
			assert false : "There is no such upgrade name.";
		}
	}
	
	public int autoAddHp(){
		int result = hp + parameter.AUTO_ADD_HP;
		if( result > parameter.INIT_TOWER_HP )
			result = parameter.INIT_TOWER_HP;
		setHp(result);
		return result;
	}
	
	public void hurt(int rivalAtk, int rivalClientNo){
		int result = hp - rivalAtk;
		if( result <= 0 ){
			result = 0;				// ¶ð·´¤F
			//SystemMsg.getInstance().outBreakTower(PositionHelper.getInstance().getPlayerEntryNo(this.host), rivalClientNo);
			changeHost( rivalClientNo );
		}
		setHp(result);
	}	
	
	public boolean isBroken(){
		if(this.hp <= 0)
			return true;
		return false;
	}
	
	private void changeHost(int newHost){
		cdc.pm.get(newHost).destroyOneTower();
		myHost.tm.removeBySerialNum(this.serialNumber);
	}
	
	private void hpRecover(){
		int cost = parameter.SKILL_HP_RECOVER_COST;
		if(cost > myHost.getMoney())
			return;
		myHost.decreaseMoney(cost);
		
		int result = hp + parameter.SKILL_HP_RECOVER;
		if( result > parameter.INIT_TOWER_HP )
			result = parameter.INIT_TOWER_HP;
		setHp(result);
	}
	
	private void moneyUpFaster(){
		if(this.moneyLevel >= parameter.MAX_TOWER_LEVEL)
			return;
		int cost = parameter.SKILL_MONEY_UP_FASTER_COST[this.moneyLevel - 1];
		if(cost > myHost.getMoney())
			return;
		myHost.decreaseMoney(cost); 
		setMoneyLevel(++this.moneyLevel);
	}
	
	private void towerAtkUp(){
		if(this.atkLevel >= parameter.MAX_TOWER_LEVEL)
			return;
		int cost = parameter.SKILL_ATK_UP_COST[this.atkLevel - 1];
		if(cost > myHost.getMoney())
			return;
		myHost.decreaseMoney(cost); 
		setAtkLevel(++this.atkLevel);
	}
	
	public boolean isInAtkRange(int opposeX, int opposeY){
		int x = this.x;
		int y = this.y;
		if( this.range >= Math.sqrt(Math.pow( x-opposeX, 2) + Math.pow( y-opposeY, 2)))
			return true;
		return false;
	}
/*---------------------------------------------------------------------------*/	
	
	public String getType() {
		return type;
	}

	public int getMoneyLevel() {
		return moneyLevel;
	}

	public void setMoneyLevel(int moneyLevel) {
		assert moneyLevel <= parameter.MAX_TOWER_LEVEL : "moneyLevel over max level";
		this.moneyLevel = moneyLevel;
		myHost.setMoneyUpRate(parameter.INIT_MONEY_UP_RATE * (int)Math.pow(2, moneyLevel-1));
	}

	public int getAtkLevel() {
		return atkLevel;
	}

	public void setAtkLevel(int atkLevel) {
		assert atkLevel <= parameter.MAX_TOWER_LEVEL : "atkLevel over max level";
		this.atkLevel = atkLevel;
		setAtk(parameter.INIT_TOWER_ATK * (int)Math.pow(2, atkLevel-1));
	}

	public void setType(String type) {
		this.type = type;
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

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getHost() {
		return host;
	}

	public void setHost(int host) {
		this.host = host;
	}

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
		cdc.addUpdateInfo(Encoder.getInstance().enSetTowerHp(this.host, this.serialNumber, this.hp));
	}	
}
