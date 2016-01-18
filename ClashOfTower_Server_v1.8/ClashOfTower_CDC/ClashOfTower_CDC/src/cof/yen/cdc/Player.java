package cof.yen.cdc;

public class Player {
	private int clientNo,  moneyUpRate, money, towerDestroyNum, deadUnitNum, heroQuantity, unitQuantity;
	private Parameter parameter;
	private PositionHelper ph = null;
	private SerialNumGenerator sng = null;
	public UnitManager um = null;
	public TowerManager tm = null;
	
		
	/*public Player() {
		um = new UnitManager();
		tm = new TowerManager();
	}*/
	
	public Player(int clientNo, int moneyUpRate, int money) {
		parameter = Parameter.getInstance();
		ph = PositionHelper.getInstance();
		sng = SerialNumGenerator.getInstance();
		um = new UnitManager();
		tm = new TowerManager();
		
		this.clientNo = clientNo;
		this.moneyUpRate = moneyUpRate;
		setMoney( money );
		this.towerDestroyNum = 0;
		this.deadUnitNum = 0;
		this.heroQuantity = 0;
		this.unitQuantity = 0;		
		
		initTower();
	}
	
	private void initTower(){
		int initPos[] = ph.getTowerPos(this.clientNo);
		Tower myTower = new Tower(this, initPos[0], initPos[1], sng.getNextSN(), this.clientNo, parameter.INIT_TOWER_ATK, parameter.INIT_TOWER_RANGE, parameter.INIT_TOWER_HP);
		this.tm.add(myTower);
	}
	
	public boolean isLose(){
		if( this.tm.isEmpty() )
			return true;
		return false;
	}
	
	public void increaseMoney(int amount){
		int result = this.money + amount;
		if(result > parameter.MAX_MONEY)
			result = parameter.MAX_MONEY;
		setMoney(result);
	}
	
	public void decreaseMoney(int cost){
		int result = this.money - cost;
		if(result < 0)
			result = 0;
		setMoney(result);
	}
	
	public void destroyOneTower(){
		this.towerDestroyNum++;
	}
	
	public void addOneMessenger(){
		decreaseMoney(parameter.MESSENGER_COST);
	}
	
	public void addOneUnit(int cost){
		assert (unitQuantity < parameter.MAX_UNIT_NUM) : "### Player ERROR： unitQuantity is over limit. ###"; 
		if(unitQuantity >= parameter.MAX_UNIT_NUM){
			System.out.println("### Player ERROR： unitQuantity is over limit. ###");
			return;
		}			
		this.unitQuantity++;
		decreaseMoney(cost);
	}
	
	public void removeOneUnit( int serialNumber ){
		unitQuantity--;
		deadUnitNum++;
		this.um.removeBySerialNum(clientNo, serialNumber);
		
		assert unitQuantity>=0;
		if(unitQuantity < 0)
			unitQuantity = 0;
	}
	
	public void addOneHero(int cost){
		assert (unitQuantity < parameter.MAX_UNIT_NUM && heroQuantity < parameter.MAX_HERO_NUM) : "### Player ERROR： unitQuantity or heroQuantity is over limit. ###"; 
		if(unitQuantity >= parameter.MAX_UNIT_NUM || heroQuantity >= parameter.MAX_HERO_NUM){
			System.out.println("### Player ERROR： unitQuantity or heroQuantity is over limit. ###");
			return;
		}			
		this.heroQuantity++;
		this.unitQuantity++;
		decreaseMoney(cost);		
	}

	public void removeOneHero( int serialNumber ){
		heroQuantity--;
		unitQuantity--;
		deadUnitNum++;
		CDC.getInstance().addTCPUpdateInfo(Encoder.getInstance().enNotifyHeroUnlocked(this.clientNo));
		this.um.removeBySerialNum(clientNo, serialNumber);
		
		assert unitQuantity>=0 && heroQuantity>=0;
		if(unitQuantity < 0)
			unitQuantity = 0;
		if(heroQuantity < 0)
			heroQuantity = 0;
	}
	
	public int autoAddMoney(){		// CDC 的 updateThread每單位時間 會呼叫這個方法
		increaseMoney(moneyUpRate);		// 每個單位時間  玩家會新增moneyUpRate這個數量的金錢   故moneyUpRate越大意謂金錢增加越快
		return this.money;
	}	

/*---------------------------------------------------------------------------*/	
	public Tower getTower(){
		if(this.isLose())
				return null;
		return this.tm.get(0);
	}	
	
	public Unit getUnit(int serialNumber){
		return this.um.getBySerialNum(serialNumber);
	}
	
	public int getMoneyUpRate() {
		return moneyUpRate;
	}

	public void setMoneyUpRate(int moneyUpRate) {
		moneyUpRate = (moneyUpRate < parameter.INIT_MONEY_UP_RATE)? parameter.INIT_MONEY_UP_RATE : moneyUpRate;
		this.moneyUpRate = moneyUpRate;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		money = (money < 0)? 0 : money;
		this.money = money;		
		CDC.getInstance().addUpdateInfo(Encoder.getInstance().enSetPlayerMoney(this.clientNo, this.money));
	}

	public int getClientNo() {
		return clientNo;
	}

	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}

	public int getDeadUnitNum() {
		return deadUnitNum;
	}

	public void setDeadUnitNum(int deadUnitNum) {
		this.deadUnitNum = deadUnitNum;
	}

	public int getHeroQuantity() {
		return heroQuantity;
	}

	public void setHeroQuantity(int heroQuantity) {
		this.heroQuantity = heroQuantity;
	}

	public int getUnitQuantity() {
		return unitQuantity;
	}

	public void setUnitQuantity(int unitQuantity) {
		this.unitQuantity = unitQuantity;
	}
	
	public int getTowerDestroyNum() {
		return towerDestroyNum;
	}

	public void setTowerDestroyNum(int towerDestroyNum) {
		this.towerDestroyNum = towerDestroyNum;
	}


}
