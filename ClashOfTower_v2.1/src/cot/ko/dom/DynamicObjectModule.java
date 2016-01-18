package cot.ko.dom;

import java.awt.Component;
import java.awt.Point;
import java.util.Enumeration;
import java.util.Vector;

import javax.lang.model.element.Element;
import javax.swing.ImageIcon;

import cot.ko.uim.InformationCenter;
import cot.qin.sre.SceneRenderEngine;

public class DynamicObjectModule {
	public static DynamicObjectModule getInstance() {
		return instance;
	}

	private static DynamicObjectModule instance = new DynamicObjectModule();

	private InformationCenter ic = InformationCenter.getInstance();
	private SceneRenderEngine sre = SceneRenderEngine.getInstance();

	protected Component component;
	@SuppressWarnings("rawtypes")
	protected Vector sv1 = new Vector(), sv2 = new Vector(),
			sv3 = new Vector(), sv4 = new Vector(), msv = new Vector();
	protected SpriteVector dosv = new SpriteVector();
	protected EffectVector eosv = new EffectVector();
	protected TowerVector tosv = new TowerVector();

	private DynamicObjectModule() {
		msv.setSize(4);
		tosv.setSize(6);
	}

	@SuppressWarnings("unchecked")
	public void addUnit(int clientno, int index, String type, int level, int x,
			int y, String action, int direction) {
		Sprite tempSprite = null;

		if (type.equals("Human")) {
			switch (level) {
			case 0:
				Warrior w = new Warrior(component, x, y, direction, action);
				w.setID(index);
				tempSprite = w;
				break;
			case 1:
				Page p = new Page(component, x, y, direction, action);
				p.setID(index);
				tempSprite = p;
				break;
			case 2:
				Knight k = new Knight(component, x, y, direction, action);
				k.setID(index);
				tempSprite = k;
				break;
			case 3:
				CygnusKnight c = new CygnusKnight(component, x, y, direction,
						action);
				c.setID(index);
				tempSprite = c;
				break;
			default:
				break;
			}
		} else if (type.equals("Wolf")) {
			switch (level) {
			case 0:
				Wolf w = new Wolf(component, x, y, direction, action);
				w.setID(index);
				tempSprite = w;
				break;
			case 1:
				TimberWolf t = new TimberWolf(component, x, y, direction,
						action);
				t.setID(index);
				tempSprite = t;
				break;
			case 2:
				SnowWolf s = new SnowWolf(component, x, y, direction, action);
				s.setID(index);
				tempSprite = s;
				break;
			case 3:
				DevilWolf d = new DevilWolf(component, x, y, direction, action);
				d.setID(index);
				tempSprite = d;
				break;
			default:
				break;
			}
		} else if (type.equals("Ork")) {
			switch (level) {
			case 0:
				Ork o = new Ork(component, x, y, direction, action);
				o.setID(index);
				tempSprite = o;
				break;
			case 1:
				Berserker b = new Berserker(component, x, y, direction, action);
				b.setID(index);
				tempSprite = b;
				break;
			case 2:
				OrkRider or = new OrkRider(component, x, y, direction, action);
				or.setID(index);
				tempSprite = or;
				break;
			case 3:
				OrkKing ok = new OrkKing(component, x, y, direction, action);
				ok.setID(index);
				tempSprite = ok;
				break;
			default:
				break;
			}
		} else if (type.equals("Gerald")) {
			Gerald g = new Gerald(component, x, y, direction, action);
			g.setID(index);
			tempSprite = g;
		} else if (type.equals("Denas")) {
			Denas d = new Denas(component, x, y, direction, action);
			d.setID(index);
			tempSprite = d;
		} else if (type.equals("Ingvar")) {
			Ingvar i = new Ingvar(component, x, y, direction, action);
			i.setID(index);
			tempSprite = i;
		} else if (type.equals("Mailk")) {
			Mailk m = new Mailk(component, x, y, direction, action);
			m.setID(index);
			tempSprite = m;
		}

		switch (clientno) {
		case 0:
			sv1.add(tempSprite);
			break;
		case 1:
			sv2.add(tempSprite);
			break;
		case 2:
			sv3.add(tempSprite);
			break;
		case 3:
			sv4.add(tempSprite);
			break;
		default:
			break;
		}

		if (ic.getPlayerNumber() == clientno)
			ic.updateUnitNumber("+");

	}

	@SuppressWarnings("unchecked")
	public void addMessenger(int clientno, int x, int y) {
		Messenger m = null;
		if (clientno == 0 || clientno == 1)
			m = new Messenger(component, x, y, 1);
		else if (clientno == 2 || clientno == 3)
			m = new Messenger(component, x, y, 0);

		msv.setElementAt(m, clientno);
	}

	@SuppressWarnings("unchecked")
	public void initTowerPosition(int[][] playerITP) {
		Tower tempTower;
		int playerNum = ic.getPlayerNumber();
		
		for(int i=0; i<4; i++)
		{
			if( playerNum == playerITP[i][0])
			{
				if (playerITP[i][1] == 1050 && playerITP[i][2] == 2550) {
					sre.iniXY(new int[]{ (playerITP[i][1]-900)/100, (playerITP[i][2]-1200)/100});
				}else if (playerITP[i][1] == 2550 && playerITP[i][2] == 1050) {
					sre.iniXY(new int[]{ (playerITP[i][1]-1200)/100, (playerITP[i][2]-900)/100});
				}else {
					sre.iniXY(new int[]{ (playerITP[i][1]-1200)/100, (playerITP[i][2]-1200)/100});
				}
				
				tempTower = new Tower(component, new ImageIcon("res/tower/redtower.png").getImage(), 
						playerITP[i][1]-180, playerITP[i][2]-160);
				Tower pin = new Tower(component, new ImageIcon("res/tower/redpin.png").getImage(), 
						playerITP[i][1]-330, (playerITP[i][2]-337 ) );
				
				tosv.setElementAt(tempTower, playerITP[i][0]);
				tosv.setElementAt(pin, 5);
			}
			else 
			{
				tempTower = new Tower(component, new ImageIcon("res/tower/greentower.png").getImage(), 
						playerITP[i][1]-180, playerITP[i][2]-160);
				tosv.setElementAt(tempTower, playerITP[i][0]);
			}
		}
		
		tempTower = new Tower(component, new ImageIcon("res/tower/capital_city.png").getImage(), 
				2500-280, 2500-260);
		tosv.setElementAt(tempTower, 4);
	}

	public void updateUnit(int clientno, int index, int x, int y,
			int direction, String action) {
		Unit u = null;
		switch (clientno) {
		case 0:
			for (int i = 0; i < sv1.size(); i++) {
				u = (Unit) sv1.get(i);
				if (u.getID() == index)
					break;
			}
			break;
		case 1:
			for (int i = 0; i < sv2.size(); i++) {
				u = (Unit) sv2.get(i);
				if (u.getID() == index)
					break;
			}
			break;
		case 2:
			for (int i = 0; i < sv3.size(); i++) {
				u = (Unit) sv3.get(i);
				if (u.getID() == index)
					break;
			}
			break;
		case 3:
			for (int i = 0; i < sv4.size(); i++) {
				u = (Unit) sv4.get(i);
				if (u.getID() == index)
					break;
			}
			break;
		default:
			break;
		}

		if(u!=null)
		{
			u.setPosition(new Point(x, y));
			u.setState(direction, action);
		}
	}

	public void updateMessenger(int clientno, int x, int y) {
		Messenger m = (Messenger) msv.get(clientno);
		if(m != null)
			m.setPosition(new Point(x, y));
	}

	@SuppressWarnings("unchecked")
	public void addEffect(String effectName, int x, int y) {
		switch (effectName) {
		case "church3":
			Tornado t = new Tornado(component, x-300, y-150);
			eosv.add(t);
			break;
		case "church2":
			SmallBomb sb = new SmallBomb(component, x-300, y-190);
			eosv.add(sb);
			break;
		case "towerattack":
			BigBomb bb = new BigBomb(component, x-170, y-100);
			eosv.add(bb);
			break;
		default:
			break;
		}
	}

	public void removeUnit(int clientno, int id) {
		Unit u = null;
		switch (clientno) {
		case 0:
			for (int i = 0; i < sv1.size(); i++) {
				u = (Unit) sv1.get(i);
				if (u.getID() == id) {
					sv1.removeElementAt(i);
					break;
				}
			}
			break;
		case 1:
			for (int i = 0; i < sv2.size(); i++) {
				u = (Unit) sv2.get(i);
				if (u.getID() == id) {
					sv2.removeElementAt(i);
					break;
				}
			}
			break;
		case 2:
			for (int i = 0; i < sv3.size(); i++) {
				u = (Unit) sv3.get(i);
				if (u.getID() == id) {
					sv3.removeElementAt(i);
					break;
				}
			}
			break;
		case 3:
			for (int i = 0; i < sv4.size(); i++) {
				u = (Unit) sv4.get(i);
				if (u.getID() == id) {
					sv4.removeElementAt(i);
					break;
				}
			}
			break;
		default:
			break;
		}
		if (ic.getPlayerNumber() == clientno)
			ic.updateUnitNumber("-");
	}

	@SuppressWarnings("unchecked")
	public void removeMessenger(int clientno) {
		msv.setElementAt(null, clientno);
		if (clientno == ic.getPlayerNumber())
			ic.setMessengerState(false);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Vector getAllDynamicObjects() {
		dosv.removeAllElements();
		Object temp = null;

		for (Enumeration e = sv1.elements(); e.hasMoreElements();) {
			temp = e.nextElement();
			if (temp != null)
				dosv.add(temp);
		}

		for (Enumeration e = sv2.elements(); e.hasMoreElements();) {
			temp = e.nextElement();
			if (temp != null)
				dosv.add(temp);
		}

		for (Enumeration e = sv3.elements(); e.hasMoreElements();) {
			temp = e.nextElement();
			if (temp != null)
				dosv.add(temp);
		}

		for (Enumeration e = sv4.elements(); e.hasMoreElements();) {
			temp = e.nextElement();
			if (temp != null)
				dosv.add(temp);
		}

		for (Enumeration e = msv.elements(); e.hasMoreElements();) {
			temp = e.nextElement();
			if (temp != null)
				dosv.add(temp);
		}

		return dosv;
	}

	@SuppressWarnings("rawtypes")
	public Vector getAllEffectObjects() {
		return eosv;
	}

	@SuppressWarnings("rawtypes")
	public Vector getAllTowerObjects() {
		return tosv;
	}
}
