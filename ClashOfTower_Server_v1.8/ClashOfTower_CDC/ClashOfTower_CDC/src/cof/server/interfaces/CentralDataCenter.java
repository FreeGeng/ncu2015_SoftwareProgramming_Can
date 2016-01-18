package cof.server.interfaces;
import java.util.ArrayList;

public interface CentralDataCenter {
	public void addPlayer(int clientNo);
	public void addUnit(String type, int level, int clientno, int aisle);
	public void upgradeUnit(int clientNo, int level);
	public void addMessenger(int clientNo);
	public void startGame();
	public void callCapitalCitySkill (int clientNo, String skillName);
	public void callTowerSkill (int clientNo);
	public void callTowerUpgrade (int clientNo, String updateName);
	public ArrayList<String> getUpdateInfo();
}