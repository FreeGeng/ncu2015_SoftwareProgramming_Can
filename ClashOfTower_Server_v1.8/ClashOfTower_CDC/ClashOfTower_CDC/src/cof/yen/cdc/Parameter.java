package cof.yen.cdc;

public class Parameter {
	public static Parameter parameter = null;
	
	
	// Game
	final int MAX_PLAYER_NUM = 4;
	final int MAX_GAME_TIME = 10 * 60; //　(sec)....   20minutes
	final int UPDATE_MONEY_INTERVAL = 1;
	final int UPDATE_HP_INTERVAL = 1;
	final int END_SCORE_TOWER = 1000;
	final int ERROR_MSG = -11111;
	final int MAX_MONEY = 999999;
	
	
	// UpdateThreaed
	final int UPDATE_INTERVAL_1000MS = 1000;		// update money, hp, atk
	final int UPDATE_INTERVAL_200MS = 200;			// update x, y, direction, action
	
	
	
	// Coordinate
	final int MAX_MAP_LENGTH = 5000;
	final int AISLE_UP = 1;
	final int AISLE_RIGHT = 2;
	final int AISLE_DOWN = 3;
	final int AISLE_LEFT = 4;
	final int AISLE_NUM = 4;
	final int MAP_BLOCK_SIZE = 100;
	final int MARGIN_X_LEFT = 10 * MAP_BLOCK_SIZE;
	final int MARGIN_X_RIGHT = 40  * MAP_BLOCK_SIZE;
	final int MARGIN_Y_UP = 10 * MAP_BLOCK_SIZE;
	final int MARGIN_Y_DOWN = 40 * MAP_BLOCK_SIZE;
	final int MARGIN_X_LEFT_BLOCK = 10;
	final int MARGIN_X_RIGHT_BLOCK = 40;
	final int MARGIN_Y_UP_BLOCK = 10;
	final int MARGIN_Y_DOWN_BLOCK = 40;
	final int ROAD_NUM = AISLE_NUM * 2;
	final int OFFSET = 50;
	final int[][] CORNER_POS= { {MARGIN_X_LEFT + OFFSET , MARGIN_Y_UP + OFFSET}, 
								{MARGIN_X_RIGHT + OFFSET, MARGIN_Y_UP + OFFSET},  
								{MARGIN_X_RIGHT + OFFSET, MARGIN_Y_DOWN + OFFSET},
								{MARGIN_X_LEFT + OFFSET ,MARGIN_Y_DOWN + OFFSET} };
	final int[] CORNER_UP = {CORNER_POS[0][0], CORNER_POS[0][1]};
	final int[] CORNER_RIGHT = {CORNER_POS[1][0], CORNER_POS[1][1]};
	final int[] CORNER_DOWN = {CORNER_POS[2][0], CORNER_POS[2][1]};
	final int[] CORNER_LEFT = {CORNER_POS[3][0], CORNER_POS[3][1]};
	
	
	// Player
	final int INIT_MONEY = 1000;
	final int INIT_MONEY_UP_RATE = 15;
	final int MAX_HERO_NUM = 1;
	final int MAX_UNIT_NUM = 30;
	
		
	
	// Unit
	final int[] INIT_WARRIOR_HP = 	{-999, 70, 	90,  110,	150}; 		// 3種等級   等級1~3   所以index0的地方不給初始值
	final int[] INIT_WARRIOR_ATK = 	{-999, 10, 	15,  20,	50}; 		
	final int[] INIT_WARRIOR_DEF = 	{-999, 15, 	20,	 30,	80}; 		
	final int[] INIT_WARRIOR_AGI = 	{-999, 20, 	20,	 15,	15}; 		
	final int[] INIT_WARRIOR_COST = {-999, 100, 150, 200, 	350}; 		
	final int[] INIT_WOLF_HP 	= {-999, 70,  90,	 110,	150};
	final int[] INIT_WOLF_ATK 	= {-999, 10,  15,	 20,	30};
	final int[] INIT_WOLF_DEF 	= {-999, 15,  20,	 15,	20};
	final int[] INIT_WOLF_AGI 	= {-999, 30,  40,	 40,	45};
	final int[] INIT_WOLF_COST 	= {-999, 100, 150,  250, 	350};	
	final int[] INIT_ORK_HP =	{-999, 70,	90,	 110,	150	};
	final int[] INIT_ORK_ATK = 	{-999,  10,	15,	 20	,	60	};
	final int[] INIT_ORK_DEF = 	{-999, 15,	20,	 40	,	60	};
	final int[] INIT_ORK_AGI = 	{-999, 10,	 5,	 25	,	5	};
	final int[] INIT_ORK_COST = {-999, 150,	200, 350, 	500	};	
	final int[] INIT_HERO_HP = 	{100, 100, 	100,  100};		//4種英雄  但是沒有等級之分
	final int[] INIT_HERO_ATK = {25,   25, 	 25,   25};		// Hero 0~3   直接切割最後一個char來判斷要取哪一個初始值
	final int[] INIT_HERO_DEF = {30,   30, 	 30,   30};		// 若type是Hero的話  直接拿level這個屬性來記錄是選0~3之中的哪個英雄
	final int[] INIT_HERO_AGI = { 8, 	5, 	  7,    5};	
	final int[] INIT_HERO_COST= {800, 800, 	800,  800};	
//	final int[] INIT_WARRIOR_HP = 	{-999, 50, 	70,  100,	150}; 		// 3種等級   等級1~3   所以index0的地方不給初始值
//	final int[] INIT_WARRIOR_ATK = 	{-999, 10, 	20,  30,	50}; 		
//	final int[] INIT_WARRIOR_DEF = 	{-999, 10, 	20,	 30,	80}; 		
//	final int[] INIT_WARRIOR_AGI = 	{-999, 20, 	20,	 15,	15}; 		
//	final int[] INIT_WARRIOR_COST = {-999, 100, 150, 200, 	350}; 		
//	final int[] INIT_WOLF_HP 	= {-999, 30,  50,	 50,	70};
//	final int[] INIT_WOLF_ATK 	= {-999, 10,  15,	 15,	30};
//	final int[] INIT_WOLF_DEF 	= {-999, 10,  15,	 15,	20};
//	final int[] INIT_WOLF_AGI 	= {-999, 30,  40,	 40,	45};
//	final int[] INIT_WOLF_COST 	= {-999, 100, 150,  250, 	350};	
//	final int[] INIT_ORK_HP =	{-999, 80,	80,	 100,	200	};
//	final int[] INIT_ORK_ATK = 	{-999,  5,	20,	 40	,	60	};
//	final int[] INIT_ORK_DEF = 	{-999, 15,	30,	 40	,	60	};
//	final int[] INIT_ORK_AGI = 	{-999, 10,	 5,	 25	,	5	};
//	final int[] INIT_ORK_COST = {-999, 150,	200, 350, 	500	};	
//	final int[] INIT_HERO_HP = 	{200, 180, 	150,  180};		//4種英雄  但是沒有等級之分
//	final int[] INIT_HERO_ATK = {50,   60, 	 60,   55};		// Hero 0~3   直接切割最後一個char來判斷要取哪一個初始值
//	final int[] INIT_HERO_DEF = {56,   50, 	 35,   30};		// 若type是Hero的話  直接拿level這個屬性來記錄是選0~3之中的哪個英雄
//	final int[] INIT_HERO_AGI = { 8, 	5, 	  7,    5};	
//	final int[] INIT_HERO_COST= {800, 800, 	800,  800};	
	// INIT_UNIT_ATTRI_SET 把所有屬性變為一個集合，WARRIOR代號index0  WOLF代號1  ORK代號2  HERO代號3
	final int[][][] INIT_UNIT_ATTRI_SET = 
		{{INIT_WARRIOR_HP	, INIT_WARRIOR_ATK	, INIT_WARRIOR_DEF	, INIT_WARRIOR_AGI	, INIT_WARRIOR_COST }, 
		 {INIT_WOLF_HP		, INIT_WOLF_ATK		, INIT_WOLF_DEF		, INIT_WOLF_AGI		, INIT_WOLF_COST	}, 
		 {INIT_ORK_HP		, INIT_ORK_ATK		, INIT_ORK_DEF		, INIT_ORK_AGI		, INIT_ORK_COST		}, 
		 {INIT_HERO_HP		, INIT_HERO_ATK		, INIT_HERO_DEF		, INIT_HERO_AGI		, INIT_HERO_COST	}};
	//final int[][] INIT_UNIT_POS = { 	{0,  0}, {0, 1},	// 4 player, 2 direction, 2 dimension pos
	//									{5,  5}, {5, 6},	// 要再改
	//									{1,  1}, {1, 2},
	//									{8,  8}, {8, 9}	}; 
	final int MAX_UNIT_LEVEL = 4;
	final int[] UNIT_UPGRADE_COST = {500, 800, 1000};
	final int UNIT_MOVE_DIST = 20;  //移動20pixel/200ms      
	final int UNIT_BREAK_DIST = 160;	// 擊退
	final int UNIT_ATK_DIST = 60;
	final String UNIT_TYPE_WARRIOR = "Human" ;
	final String UNIT_TYPE_WOLF = "Wolf" ;
	final String UNIT_TYPE_ORK = "Ork" ;
	final String[] UNIT_TYPE_HERO = {"Gerald", "Denas", "Mailk", "Ingvar"};
	final String UNIT_DIRECTION_RIGHT = "right"; 
	final String UNIT_DIRECTION_LEFT = "left"; 
	final String UNIT_ACTION_WALK = "walk";
	final String UNIT_ACTION_ATTACK = "attack";
	final String UNIT_ACTION_ATTACKTOWER = "attackTower";;
	final String UNIT_ACTION_BREAK = "break";
	
	
	
	// Tower
	final int[][] INIT_TOWER_POS_LEFTUP= {{2500, 1000}, {4000, 2500}, {2500 ,4000}, {1000, 2500}};  //左上角基準點  初始化告訴client畫塔時才用，其他都用另一個
	final int[][] INIT_TOWER_POS= { {INIT_TOWER_POS_LEFTUP[0][0]+OFFSET, INIT_TOWER_POS_LEFTUP[0][1]+OFFSET}, 
									{INIT_TOWER_POS_LEFTUP[1][0]+OFFSET, INIT_TOWER_POS_LEFTUP[1][1]+OFFSET}, 
									{INIT_TOWER_POS_LEFTUP[2][0]+OFFSET, INIT_TOWER_POS_LEFTUP[2][1]+OFFSET}, 
									{INIT_TOWER_POS_LEFTUP[3][0]+OFFSET, INIT_TOWER_POS_LEFTUP[3][1]+OFFSET} };
	final int INIT_TOWER_HP = 1000;
	final int INIT_TOWER_RANGE = 220;
	final int INIT_TOWER_ATK = 50;
	final int AUTO_ADD_HP = 10;
	final int SKILL_MONEY_UP_FASTER = 2;		// 2倍 
	final int[] SKILL_MONEY_UP_FASTER_COST = {500, 1000, 2000, 5000};	//升到最高級為5等  故1升到5需要四個等級的錢
	final int SKILL_HP_RECOVER = 100;
	final int SKILL_HP_RECOVER_COST = 500;
	final int SKILL_ATK_UP = 2;				// 2倍
	final int[] SKILL_ATK_UP_COST = {500, 1000, 1500, 2000};
	final int MAX_TOWER_LEVEL = 5;			// 從1等開始算 最高5等
	final int TOWER_ATK_DIST = 100;
	final String TOWER_TYPE = "Tower";
	
	
	
	// Capital City
	//final int[][] INIT_MSGER_POS = {{1, 1}, {2, 2}, {3, 3}, {4, 4}}; 
	final int[] CAPITAL_POS = {2550, 2550};
	final int MESSENGER_COST = 500;
	final int CAPITAL_SKILL_CASTLE1 = -2;	// moneyLevel降兩等 
	final int CAPITAL_SKILL_CASTLE2 = 1000;	// 隨機玩家扣錢1000
	final int CAPITAL_SKILL_CASTLE3 = 1;		//atkLevel加一等
	final int CAPITAL_SKILL_CHURCH1 = 1000;
	final int CAPITAL_SKILL_CHURCH2 = 99999;
	final int CAPITAL_SKILL_CHURCH3 = 300;
	/*final int[][][] EFFECT_CHURCH2_POS = 
		{	
			{{1050, }, {1050, 2250}, {1050, }, {1050, }, {1050, }, { ,1050}, { ,1050}, { ,1050}, { ,1050}},
			{{2850, 1050}, {3150, 1050}, {3450, 1050}, {3750, 1050}, {4050, 1050}, {4050, 1350}, {4050, 1650}, {4050, 1950}, {4050, 2250}},
			{{}, {}, {}, {}, {}, {}, {}, {}, {}},
			{{}, {}, {}, {}, {}, {}, {}, {}, {}}
		}; */
	
	
	
	// Encoder
	final int NO_CONFIGURE_INT = -99999;
	final String NO_CONFIGURE = "NoConfigure";
	final String SET_EFFECT_TOWERATK = "towerattack";
	final String SET_EFFECT_CHURCH2 = "church2";
	final String SET_EFFECT_CHURCH3 = "church3";
	
	
	
	
	public static Parameter getInstance(){
		if(parameter == null)
			parameter = new Parameter();
		return parameter;
	}
}
