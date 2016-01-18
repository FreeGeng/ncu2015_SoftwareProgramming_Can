package cof.hong.tcpsm;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import cof.yen.cdc.CDC;
import cof.can.udpbc.UDP_Broadcast_Client_Module;
import cof.server.interfaces.CentralDataCenter;

//此class負責讀取從client發送過來的訊息
public class ReadMessage implements Runnable{
	private DataInputStream intputStream;
	private String inputString;
	private Socket clientSocket;
	private TcpServer tcpServer;
	private Decoder decoder;
	
	public ReadMessage(Socket clientsocket, CentralDataCenter cdc) throws IOException{
		this.clientSocket = clientsocket;
		intputStream = new DataInputStream(clientSocket.getInputStream());
		tcpServer = TcpServer.getInstance();
		decoder = new Decoder(cdc);
	}
	
	public void run(){
		while(true){
			try {
				readMsgFromClient();
			} catch (IOException e) {
				//e.printStackTrace();
			}
		}
	}
	
	//讀從client發送過來的訊息
	public void readMsgFromClient() throws IOException{
		inputString = intputStream.readUTF();
		switch (decoder.splitInstruction(inputString)) {
			case "HAVE_STARTED":
				//收到"HAVE_STARTED"訊息就更新server的currentChooseNum數
				tcpServer.incrementCurrentChooseNum();
				//到達四位後，發訊息給所有client，代表遊戲即將開始
				if(tcpServer.getCurrentChooseNum() == 4){
					/*當TCPSM偵測到四位玩家都選擇完英雄後，代表四位玩家會開始進行遊戲，所以TCPSM
					 * 需要呼叫CDC的這個方法，用來通知CDC已經開始遊戲了。
					 */
					tcpServer.writeMessage.writeMsgToAllClient("START_GAME");
					tcpServer.cdc.startGame();
					UDP_Broadcast_Client_Module.getInstance().clientTableAlready(CDC.getInstance(), TcpServer.getInstance());
					
				}
				break;
			case "addUnit":
				decoder.decodeAddUnit(inputString);
				break;
			case "addMessenger":
				decoder.decodeAddMessenger(inputString);
				break;
			case "callTowerSkill":
				decoder.decodeCallTowerSkill(inputString);
				break;
			case "callTowerUpgrade":
				decoder.decodeCallTowerUpgrade(inputString);
				break;
			case "callCapitalCitySkill":
				decoder.decodeCallCapitalCitySkill(inputString);
				break;
			case "upgradeUnit":
				decoder.decodeUpgradeUnit(inputString);
		}
	}
}
