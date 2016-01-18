

import java.io.IOException;
import cof.hong.tcpsm.TcpServer;
import cof.yen.cdc.CDC;


public class ServerMain {

	public static void main(String[] args) {		// ¥ýnew CDC ¡÷ new TCP ¡÷ new UDP

		CDC cdc = CDC.getInstance();
		TcpServer ts = null;
		try {
			ts = TcpServer.getInstance();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		
		
		/*PositionHelper ph = PositionHelper.getInstance();
		Parameter par = Parameter.getInstance();
		Coordinate co = Coordinate.getInstance();
		SystemMsg sm = SystemMsg.getInstance();*/
		
		
		
		
		//new TestDrive(cdc);
/*
		cdc.addPlayer(3);	
		cdc.addPlayer(5);	
		cdc.addPlayer(99);	
		cdc.addPlayer(66);
		cdc.addMessenger(3);
		Timer a = new Timer();
		a.schedule(new ActionTimer(cdc), 1000, 10);
		*/
		
/*
		System.out.println(isAtCapital(2500, 2500));
		System.out.println(isAtCapital(2550, 2550));
		System.out.println(isAtCapital(2550, 2551));
		*/
		
		
		
		/*
		int[] a=findRandomAtkAisle();
		sm.out(a[0] +" " +a[1]);
		*/
/*
		cdc.addPlayer(3);	
		cdc.addPlayer(5);	
		cdc.addPlayer(99);	
		cdc.addPlayer(66);	
		System.out.println(findTowerAtkRoad(3)[0] +" "+ findTowerAtkRoad(3)[1]);
		System.out.println(findTowerAtkRoad(5)[0] +" "+ findTowerAtkRoad(5)[1]);
		System.out.println(findTowerAtkRoad(99)[0] +" "+ findTowerAtkRoad(99)[1]);
		System.out.println(findTowerAtkRoad(66)[0] +" "+ findTowerAtkRoad(66)[1]);
		*/
		
		
/*
		cdc.addPlayer(3);	
		cdc.addPlayer(5);	
		cdc.addPlayer(99);	
		cdc.addPlayer(66);	
		HashMap<Integer, int[]> a = cdc.getInitTowerPos();
		for(int key: a.keySet())
			System.out.println(key+": "+a.get(key)[0] +" "+ a.get(key)[1]);
		*/
		
/*
		cdc.addPlayer(3);	
		cdc.addPlayer(5);	
		cdc.addPlayer(99);	
		cdc.addPlayer(66);	
		System.out.println(cdc.utility.aisle2RoadIndex(3, 1));
		System.out.println(cdc.utility.aisle2RoadIndex(3, 2));
		System.out.println(cdc.utility.aisle2RoadIndex(5, 2));
		System.out.println(cdc.utility.aisle2RoadIndex(5, 3));
		System.out.println(cdc.utility.aisle2RoadIndex(99, 3));
		System.out.println(cdc.utility.aisle2RoadIndex(99, 4));
		System.out.println(cdc.utility.aisle2RoadIndex(66, 4));
		System.out.println(cdc.utility.aisle2RoadIndex(66, 1));
		*/
		
		
		/*
		int[][] re = par.CORNER_POS;
		for(int i=0;i<re.length;i++){
			System.out.println(re[i][0] +" " +re[i][1]);
		}
		*/
		
		/*
		System.out.println(co.isAtTower(2500, 1000));
		System.out.println(co.isAtTower(4000, 2500));
		System.out.println(co.isAtTower(2500, 4000));
		System.out.println(co.isAtTower(1000, 2500));
		System.out.println(co.isAtTower(1000, 200));
		*/
		
		/*
		Unit aUnit = new Unit();		
		System.out.println(aUnit.isInAtkRange(1000, 1000, 1000, 2000));
		System.out.println(aUnit.isInAtkRange(1000, 1000, 1000, 1050));
		System.out.println(aUnit.isInAtkRange(1000, 1000, 1000, 1000));
		System.out.println(aUnit.isInAtkRange(1000, 1000, 1000, 1020));
		System.out.println(aUnit.isInAtkRange(1000, 1000, 1000, 1001));
		*/
		
		
/*
		cdc.addPlayer(3);	
		cdc.addPlayer(5);	
		cdc.addPlayer(99);	
		cdc.addPlayer(66);	
		System.out.println(ph.getClientNoByEntryNo(0));
		System.out.println(ph.getClientNoByEntryNo(1));
		System.out.println(ph.getClientNoByEntryNo(2));
		System.out.println(ph.getClientNoByEntryNo(3));
		System.out.println(ph.getClientNoByEntryNo(4));
		*/
		
		
/*
		cdc.addPlayer(3);	
		cdc.addPlayer(5);	
		cdc.addPlayer(99);	
		cdc.addPlayer(66);
		//cdc.getPlayer(5).tm.remove(0);
		//cdc.getPlayer(99).tm.remove(0);
		//cdc.getPlayer(66).tm.remove(0);
		cdc.addUnit("Ork", 1, 3, 1);
		Timer a = new Timer();
		a.schedule(new ActionTimer(cdc), 1000, 10);
		cdc.addUnit("Warrior", 1, 99, 4);
		*/
		
		
		
		/*
		cdc.addPlayer(1);
		cdc.addUnit("Ork", 1, 1, 2);
		cdc.getPlayer(1).um.get(0).setX(1000);
		cdc.getPlayer(1).um.get(0).setY(4000);
		coor.setRoadNextPos(7, cdc.getPlayer(1).um.get(0));
		System.out.println(cdc.getPlayer(1).um.get(0).getX());
		System.out.println(cdc.getPlayer(1).um.get(0).getY());
		*/
		
		
		/*
		System.out.println(coor.isAtCorner(1000, 4000));
		System.out.println(coor.isAtCorner(4100, 4000));
		System.out.println(coor.isAtCorner(1000, 1100));
		System.out.println(coor.isAtCorner(4100, 1000));
		*/
		
		
		
		/*
		System.out.println(cd.isLegalRoad(1000, 1005));
		System.out.println(cd.isLegalRoad(1045, 4045));
		System.out.println(cd.isLegalRoad(4024, 4089));
		System.out.println(cd.isLegalRoad(4037, 1043));
		System.out.println(cd.isLegalRoad(1041, 3200));
		System.out.println(cd.isLegalRoad(4077, 2045));
		System.out.println(cd.isLegalRoad(3292, 1027));
		System.out.println(cd.isLegalRoad(1973, 4013));
		System.out.println(cd.isLegalRoad(2087, 2001));
		System.out.println(cd.isLegalRoad(5000, 2045));
		System.out.println(cd.isLegalRoad(994, 2041));
		*/
		
		
/*
		cdc.addPlayer(3);	
		cdc.getPlayer(3).getTower().setAtkLevel(3);
		cdc.cc.skill(3, "castle3");
		cdc.cc.skill(3, "castle3");
		cdc.cc.skill(3, "castle3");
		cdc.cc.skill(3, "castle3");
		System.out.println(cdc.getPlayer(3).getTower().getAtk());
		*/
		
		
		
/*
		cdc.addPlayer(3);	
		cdc.addPlayer(5);	
		cdc.addPlayer(99);	
		cdc.addPlayer(66);	
		cdc.getPlayer(3).setMoney(5000);
		cdc.getPlayer(5).setMoney(5000);
		cdc.getPlayer(99).setMoney(5000);
		cdc.getPlayer(66).setMoney(5000);
		cdc.cc.skill(3, "castle2");
		System.out.println(cdc.getPlayer(3).getMoney());
		System.out.println(cdc.getPlayer(5).getMoney());
		System.out.println(cdc.getPlayer(99).getMoney());
		System.out.println(cdc.getPlayer(66).getMoney());
				*/
		
		
		/*
		cdc.addPlayer(3);	
		cdc.addPlayer(5);	
		cdc.addPlayer(99);	
		cdc.addPlayer(66);	
		cdc.getPlayer(3).getTower().setMoneyLevel(5);
		cdc.getPlayer(5).getTower().setMoneyLevel(5);
		cdc.getPlayer(99).getTower().setMoneyLevel(5);
		cdc.getPlayer(66).getTower().setMoneyLevel(5);
		cdc.cc.skill(3, "castle1");
		System.out.println(cdc.getPlayer(3).getMoneyUpRate());
		System.out.println(cdc.getPlayer(5).getMoneyUpRate());
		System.out.println(cdc.getPlayer(99).getMoneyUpRate());
		System.out.println(cdc.getPlayer(66).getMoneyUpRate());
		*/
		
		/*
		cdc.updateInfo.add("1");
		cdc.updateInfo.add("2");
		cdc.updateInfo.add("3");
		ArrayList<String> arrayList = cdc.getUpdateInfo();
		System.out.println(cdc.updateInfo.size());
		System.out.println(arrayList.get(0));
		System.out.println(arrayList.get(1));
		System.out.println(arrayList.get(2));
		*/
		
		
		/*
		cdc.addPlayer(3);	
		cdc.addPlayer(5);	
		cdc.addPlayer(99);	
		cdc.addPlayer(66);	
		cdc.getPlayer(3).setMoney(par.MAX_MONEY);
		cdc.getPlayer(3).getTower().upgrade("moneyUpFaster");;
		System.out.println(cdc.getPlayer(3).getMoneyUpRate());
		System.out.println(cdc.getPlayer(3).getTower().getAtk());
		*/
		
		
		/*
		cdc.addPlayer(3);			
		cdc.pm.get(3).tm.get(0).changeHost(3);
		System.out.println(cdc.pm.get(3).isDead());
		*/
				
		
		/*
		cdc.addPlayer(5);
		cdc.addPlayer(12);
		cdc.addPlayer(9);
		cdc.addPlayer(1);
		cdc.addUnit(par.UNIT_TYPE_WARRIOS, 1, 5, par.AISLE_UP);
		cdc.addUnit(par.UNIT_TYPE_WARRIOS, 2, 5, par.AISLE_RIGHT);
		cdc.addUnit(par.UNIT_TYPE_ORK, 1, 12, par.AISLE_DOWN);
		cdc.addUnit(par.UNIT_TYPE_ORK, 3, 12, par.AISLE_RIGHT);
		cdc.addUnit(par.UNIT_TYPE_WOLF, 2, 9, par.AISLE_DOWN);
		cdc.addUnit(par.UNIT_TYPE_WOLF, 3, 9, par.AISLE_LEFT);
		cdc.addUnit(par.UNIT_TYPE_HERO[1], -1, 1, par.AISLE_UP);
		cdc.addUnit(par.UNIT_TYPE_HERO[2], -1, 1, par.AISLE_LEFT);
		for(int key : cdc.pm.keySet()){
			for(int i=0;i<cdc.pm.get(key).um.size();i++){
				System.out.println(cdc.pm.get(key).um.get(i).toString());
			}
			System.out.println();
		}
		System.out.println(cdc.pm.get(5).getMoney());
		System.out.println(cdc.pm.get(12).getMoney());
		System.out.println(cdc.pm.get(9).getMoney());
		System.out.println(cdc.pm.get(1).getMoney());
		*/
		
//		ArrayList<EndScore> arrayList = new ArrayList<EndScore>();
//		arrayList.add(new EndScore(5, 15));
//		arrayList.add(new EndScore(12, 90));
//		arrayList.add(new EndScore(12, 90));
//		SentEndScore score =new SentEndScore(arrayList);
//		System.out.println(score.toString());
		
		
		
		/*
		cdc.addPlayer(5);
		cdc.addPlayer(11);
		cdc.addPlayer(2);
		cdc.addPlayer(70);
		System.out.println(ph.getUnitDirection(5, par.AISLE_RIGHT) +" "+ ph.getUnitDirection(5, par.AISLE_UP));
		System.out.println(ph.getUnitDirection(11, par.AISLE_RIGHT) +" "+ ph.getUnitDirection(11, par.AISLE_DOWN));
		System.out.println(ph.getUnitDirection(2, par.AISLE_DOWN) +" "+ ph.getUnitDirection(2, par.AISLE_LEFT));
		System.out.println(ph.getUnitDirection(70, par.AISLE_UP) +" "+ ph.getUnitDirection(70, par.AISLE_LEFT));
		*/
		
		
		/*
		cdc.addPlayer(26);
		cdc.addPlayer(33);
		cdc.addPlayer(7);
		int x = cdc.pm.get(26).tm.get(0).getX();
		int y = cdc.pm.get(26).tm.get(0).getY();
		System.out.println(x +" "+ y);
		x = cdc.pm.get(33).tm.get(0).getX();
		y = cdc.pm.get(33).tm.get(0).getY();
		System.out.println(x +" "+ y);
		x = cdc.pm.get(7).tm.get(0).getX();
		y = cdc.pm.get(7).tm.get(0).getY();
		System.out.println(x +" "+ y);
		*/
		
				
		/*
		cdc.addPlayer(0);
		cdc.addPlayer(1);
		cdc.addPlayer(2);
		cdc.addPlayer(3);
		System.out.println(PositionHelper.getInstance().getTowerPos(0)[0] + " " +PositionHelper.getInstance().getTowerPos(0)[1] );
		System.out.println(PositionHelper.getInstance().getTowerPos(1)[0] + " " +PositionHelper.getInstance().getTowerPos(1)[1] );
		System.out.println(PositionHelper.getInstance().getTowerPos(2)[0] + " " +PositionHelper.getInstance().getTowerPos(2)[1] );
		System.out.println(PositionHelper.getInstance().getTowerPos(3)[0] + " " +PositionHelper.getInstance().getTowerPos(3)[1] );
		*/
		
		
		/*
		System.out.println(SerialNumGenerator.getInstance().getNextSN());
		System.out.println(SerialNumGenerator.getInstance().getNextSN());
		System.out.println(SerialNumGenerator.getInstance().getNextSN());
		*/
		
		
		/*	CDC cdc = CDC.getInstance();
		cdc.addPlayer(0);
		cdc.addPlayer(1);
		cdc.addPlayer(2);
		cdc.addPlayer(3);
		System.out.println(cdc.getCurPlayerNum());*/
		
		
		/*	
	    CDC cdc = CDC.getInstance();
		for(int i=0;i<5;i++){
			int[] result = cdc.alg.getNextTowerPos();
			System.out.println(result[0] +" "+ result[1]);
		}*/
		
		
		/*CDC cdc = CDC.getInstance();
		PlayerManager pm = cdc.pm;
		pm.put(0, new Player(0, 10, 1000));
		pm.put(1, new Player(1, 10, 1000));
		pm.put(2, new Player(2, 10, 1000));
		pm.put(3, new Player(3, 10, 1000));		
		pm.get(0).tm.add(new Tower(0, 0, 1000, 0, 0, 0, 0));
		pm.get(1).tm.add(new Tower(0, 0, 1001, 1, 0, 0, 0));
		pm.get(2).tm.add(new Tower(0, 0, 1002, 2, 0, 0, 0));
		pm.get(3).tm.add(new Tower(0, 0, 1003, 3, 0, 0, 0));		
		pm.get(0).setDeadUnitNum(15);;
		pm.get(1).setDeadUnitNum(100);;
		pm.get(2).setDeadUnitNum(32);;
		pm.get(3).setDeadUnitNum(99);;	
		pm.get(1).tm.get(0).changeHost(3);;
		pm.get(2).tm.get(0).changeHost(3);
		pm.get(3).tm.get(0).changeHost(0);		
		ArrayList<EndScore> arrayList = cdc.getEndScore();
		for(int i=0;i<arrayList.size();i++)
			System.out.println(arrayList.get(i).getClientNo() +": "+arrayList.get(i).getScore());*/
		
		
		/*PlayerManager pm = new PlayerManager();
		pm.put(0, new Player(0, 10, 1000));
		pm.remove(0);
		System.out.println(pm.size());
		System.out.println(pm.get(0).getClientNo());*/
		
		
		/*CapitalCity a = new CapitalCity();
		a.put(0, new Messenger(0, 0, 0, 1001));
		System.out.println(a.size());
		a.remove(0);
		System.out.println(a.size());
		System.out.println(a.get(0));*/
		
		
/*		CDC cdc = CDC.getInstance();
		Tower t = new Tower();
		cdc.pm.put(0, new Player(0, 5, 1000));
		cdc.pm.put(1, new Player(1, 5, 1000));
		cdc.pm.get(0).tm.add(t);
		System.out.println(t);
		System.out.println(t.getHost());
		System.out.println(cdc.pm.get(0).tm.size());
		System.out.println(cdc.pm.get(1).tm.size());
		System.out.println(cdc.pm.get(0).tm.get(0));
		t.changeHost(1);
		System.out.println(t);
		System.out.println(t.getHost());
		System.out.println(cdc.pm.get(0).tm.size());
		System.out.println(cdc.pm.get(1).tm.size());
		System.out.println(cdc.pm.get(1).tm.get(0));*/
		
	}
}