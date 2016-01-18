package cot.ko.uim;

import java.util.Timer;
import java.util.TimerTask;

public class InformationCenter {
	public static InformationCenter getInstance() {
		return instance;
	}

	private static InformationCenter instance = new InformationCenter();

	private final int MAX_UNIT_NUMBER = 30,

			MESSENGER_COST = 500,

			WARRIOR_COST = 100, PAGE_COST = 150, KNIGHT_COST = 200, CYGNUSKNIGHT_COST = 350,

			WOLF_COST = 100, TIMBERWOLF_COST = 150, SNOWWOLF_COST = 250, DEVILWOLF_COST = 350,

			ORK_COST = 150, BERSERKER_COST = 200, ORKRIDER_COST = 350, ORKKING_COST = 500,

			HERO_COST = 800,

			TOWERRECOVER_COST = 500,

			TOWERFIRE_CDTIME = 90;

	private int MyClientNo = 0, TowerHealth, PlayerMoney, UnitNumber = 0, TowerFireCD;

	private int[] UnitLevel, TowerLevel;

	private int[] HumanCost = { WARRIOR_COST, PAGE_COST, KNIGHT_COST, CYGNUSKNIGHT_COST };
	private int[] WolfCost = { WOLF_COST, TIMBERWOLF_COST, SNOWWOLF_COST, DEVILWOLF_COST };
	private int[] OrkCost = { ORK_COST, BERSERKER_COST, ORKRIDER_COST, ORKKING_COST };
	private int[] MoneyUpgradeCost = { 500, 1000, 2000, 5000 };
	private int[] AtkUpgradeCost = { 500, 1000, 1500, 2000 };
	private int[] UnitUpgradeCost = { 500, 800, 1000 };

	private boolean isHeroSend, isMessengerSend, isMessengerArrived;

	private String GameTime, HeroName = "Gerald";

	private TowerRecoverButton trb;
	private AtkUpgradeButton aub;
	private MoneyUpgradeButton mub;

	private UnitUpgradeButton uub;

	private MessengerButton mb;
	private HumanButton hb;
	private WolfButton wb;
	private OrkButton ob;
	private HeroButton herob;
	private TowerFireButton tfb;

	private MailButton mailb;

	private GameTimeText gtt;
	private PlayerMoneyText pmt;
	private TowerHealthText tht;
	private TotalUnitText tut;

	private Timer cdTimer;

	private InformationCenter() {
		// TowerRecoverButton
		trb = TowerRecoverButton.getInstance();
		trb.setInformationCenter(this);

		// AtkUpgradeButton
		aub = AtkUpgradeButton.getInstance();
		aub.setInformationCenter(this);

		// MoneyUpgradeButton
		mub = MoneyUpgradeButton.getInstance();
		mub.setInformationCenter(this);

		// UnitUpgradeButton
		uub = UnitUpgradeButton.getInstance();
		uub.setInformationCenter(this);

		// MessengerButton
		mb = MessengerButton.getInstance();
		mb.setInformationCenter(this);

		// HumanButton
		hb = HumanButton.getInstance();
		hb.setInformationCenter(this);
		hb.setCurrentLevel(0);

		// WolfButton
		wb = WolfButton.getInstance();
		wb.setInformationCenter(this);
		wb.setCurrentLevel(0);

		// OrkButton
		ob = OrkButton.getInstance();
		ob.setInformationCenter(this);
		ob.setCurrentLevel(0);

		// HeroButton
		herob = HeroButton.getInstance();
		herob.setInformationCenter(this);

		// TowerFireButton
		tfb = TowerFireButton.getInstance();
		tfb.setInformationCenter(this);

		// MailButton
		mailb = MailButton.getInstance();
		mailb.setInformationCenter(this);

		// GameTimeText & TowerHealthText & PlayerMoneyText & TotalUnitText
		gtt = GameTimeText.getInstance();
		tht = TowerHealthText.getInstance();
		pmt = PlayerMoneyText.getInstance();
		tut = TotalUnitText.getInstance();

		// Initial UnitLevel
		UnitLevel = new int[3];
		for (int i = 0; i < UnitLevel.length; i++) {
			UnitLevel[i] = 0;
		}

		// Initial TowerLevel
		TowerLevel = new int[2];
		for (int i = 0; i < TowerLevel.length; i++) {
			TowerLevel[i] = 0;
		}
	}

	public void setPlayerNumber(int clientNo) {
		this.MyClientNo = clientNo;
	}

	public int getPlayerNumber() {
		return MyClientNo;
	}

	public void setHeroName(String name) {
		this.HeroName = name;
	}

	public String getHeroName() {
		return HeroName;
	}

	public void updateTowerHP(int clintno, int hp) {
		if (clintno == MyClientNo) {
			this.TowerHealth = hp;
			tht.setText(Integer.toString(hp));
		}
		
		if (hp<=0)
			lockAllUI();
	}

	public int getTowerHp() {
		return TowerHealth;
	}

	public void updateMoney(int clientno, int money) {
		if (clientno == MyClientNo) {
			this.PlayerMoney = money;
			pmt.setText(Integer.toString(money));
		}
		
		if (TowerHealth> 0)
			updateAllUI();
	}

	public int getMoney() {
		return PlayerMoney;
	}

	public void updateGameTime(String time) {
		this.GameTime = time;
		gtt.setText(time);
		// if (time.equals(00:00))...
	}

	public String getGameTime() {
		return GameTime;
	}

	public void updateUnitNumber(String command) {
		if (command.equals("+"))
			UnitNumber++;
		else
			UnitNumber--;
		tut.setText(Integer.toString(UnitNumber));
		// if (number>=30)...
	}

	public int getUnitNumber() {
		return UnitNumber;
	}

	public void setUnitLevel(String type, int level) {
		if (level < 4) {
			switch (type) {
			case "Human":
				this.UnitLevel[0] = level;
				hb.setCurrentLevel(level);
				break;
			case "Wolf":
				this.UnitLevel[1] = level;
				wb.setCurrentLevel(level);
				break;
			case "Ork":
				this.UnitLevel[2] = level;
				ob.setCurrentLevel(level);
				break;
			default:
				break;
			}
		}
	}

	public int getUnitLevel(String type) {
		switch (type) {
		case "Human":
			return UnitLevel[0];
		case "Wolf":
			return UnitLevel[1];
		case "Ork":
			return UnitLevel[2];
		default:
			return 0;
		}
	}

	public void setTowerLevel(String type, int level) {
		switch (type) {
		case "TowerATK":
			this.TowerLevel[0] = level;
			if (level < 4)
				aub.setCosttext(Integer.toString(AtkUpgradeCost[TowerLevel[0]]));
			else
				aub.setCosttext("MAX");
			break;
		case "Money":
			this.TowerLevel[1] = level;
			if (level < 4)
				mub.setCosttext(Integer.toString(MoneyUpgradeCost[TowerLevel[1]]));
			else
				mub.setCosttext("MAX");
			break;
		default:
			break;
		}
	}

	public int getTowerLevel(String type) {
		switch (type) {
		case "TowerATK":
			return TowerLevel[0];
		case "Money":
			return TowerLevel[1];
		default:
			return 0;
		}
	}

	public void startCoolDownTimer() {
		if (cdTimer == null) {
			cdTimer = new Timer();
			TowerFireCD = TOWERFIRE_CDTIME;
			// .................

			TimerTask task = new TimerTask() {
				public void run() {
					TowerFireCD -= 1;// delay = 1 sec
					tfb.setProgress(TowerFireCD);

					if (TowerFireCD <= 0)
						stopCoolDownTimer();
				}
			};

			cdTimer.schedule(task, 1000, 1000);
		}
	}

	public void stopCoolDownTimer() {
		if (cdTimer != null) {
			cdTimer.cancel();
			cdTimer = null;
		}
	}

	public int getCDTime() {
		return TowerFireCD;
	}

	public void setHeroState(int clientno, boolean isHeroSend) {
		if (clientno == MyClientNo)
			this.isHeroSend = isHeroSend;
	}

	public boolean getHeroState() {
		return isHeroSend;
	}

	public void setMessengerState(boolean isMessengerSend) {
		this.isMessengerSend = isMessengerSend;
	}

	public boolean getMessengerState() {
		return isMessengerSend;
	}

	public void sentMessengerArriveMsg(int clientno) {
		if (clientno == MyClientNo)
			setCapitalCitySystem(true);
	}

	public void setCapitalCitySystem(boolean isMessengerArrived) {

		this.isMessengerArrived = isMessengerArrived;

		// set MailButton
		if (isMessengerArrived)
			mailb.setVisible(true);
		else
			mailb.setVisible(false);
	}

	public boolean getCapitalCitySystem() {
		return isMessengerArrived;
	}

	public void updateAllUI() 
	{
		// MessengerButton
		if (!isMessengerSend && PlayerMoney >= MESSENGER_COST)
			mb.setButtonUnlock();
		else
			mb.setButtonLock();
	
		// HumanButton
		if (hb.getIsReleased()) 
		{
			if (UnitNumber < MAX_UNIT_NUMBER && PlayerMoney >= HumanCost[ UnitLevel[0] ])
				hb.setButtonUnlock();
			else
				hb.setButtonLock();
		}
	
		// WolfButton
		if (wb.getIsReleased()) 
		{
			if (UnitNumber < MAX_UNIT_NUMBER && PlayerMoney >= WolfCost[ UnitLevel[1] ])
				wb.setButtonUnlock();
			else
				wb.setButtonLock();
		}
		
		// OrkButton
		if (ob.getIsReleased()) 
		{
			if (UnitNumber < MAX_UNIT_NUMBER && PlayerMoney >= OrkCost[UnitLevel[2]])
				ob.setButtonUnlock();
			else
				ob.setButtonLock();
		}
	
		// HeroButton
		if (herob.getIsReleased()) 
		{
			if (UnitNumber < MAX_UNIT_NUMBER && !isHeroSend && PlayerMoney >= HERO_COST)
				herob.setButtonUnlock();
			else
				herob.setButtonLock();
		}
	
		// TowerRecoverButton
		if (PlayerMoney >= TOWERRECOVER_COST)
			trb.setButtonUnlock();
		else
			trb.setButtonLock();
	
		// AtkUpgradeButton
		if(TowerLevel[0] < 4)
		{
			if (PlayerMoney >= AtkUpgradeCost[ TowerLevel[0] ])
				aub.setButtonUnlock();
			else
				aub.setButtonLock();
		}
		
		// MoneyUpgradeButton
		if(TowerLevel[1] < 4)
		{
			if (PlayerMoney >= MoneyUpgradeCost[TowerLevel[1]])
				mub.setButtonUnlock();
			else
				mub.setButtonLock();
		}
		
		// UnitUpgradeButton
		if (uub.getType() != null) 
		{
			switch (uub.getType()) 
			{
				case "Human":
					if (UnitLevel[0] < 3) 
					{
						if (PlayerMoney >= UnitUpgradeCost[UnitLevel[0]])
							uub.setButtonUnlock();
						else
							uub.setButtonLock();
					}
					break;
				case "Wolf":
					if (UnitLevel[1] < 3) 
					{
						if (PlayerMoney >= UnitUpgradeCost[UnitLevel[1]])
							uub.setButtonUnlock();
						else
							uub.setButtonLock();
					}
					break;
				case "Ork":
					if (UnitLevel[2] < 3) 
					{
						if (PlayerMoney >= UnitUpgradeCost[UnitLevel[2]])
							uub.setButtonUnlock();
						else
							uub.setButtonLock();
					}
					break;
				default:
					break;
			}
		}
	}
	
		
	public void lockAllUI() 
	{
		mb.setButtonLock();
		hb.setButtonLock();
		wb.setButtonLock();
		ob.setButtonLock();
		herob.setButtonLock();
		trb.setButtonLock();
		aub.setButtonLock();
		mub.setButtonLock();
		uub.setButtonLock();
		tfb.setClickEnable(false);
		stopCoolDownTimer();
	}

	// Mes
	// #######################################################
	public void setEndScore(int[][] endscore) 
	{
		EndScorePanel esp = EndScorePanel.getInstance();
		esp.client0.setText(Integer.toString(endscore[0][0]));
		esp.client1.setText(Integer.toString(endscore[1][0]));
		esp.client2.setText(Integer.toString(endscore[2][0]));
		esp.client3.setText(Integer.toString(endscore[3][0]));
		
		esp.clientNo0Score.setText(Integer.toString(endscore[0][1]));
		esp.clientNo1Score.setText(Integer.toString(endscore[1][1]));
		esp.clientNo2Score.setText(Integer.toString(endscore[2][1]));
		esp.clientNo3Score.setText(Integer.toString(endscore[3][1]));
		
		MainFrame mf = MainFrame.getInstance();
		mf.ChangeScreen("GameOver");
	}
}
