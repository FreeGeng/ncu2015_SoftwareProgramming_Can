package cof.hong.tcpsm;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import cof.yen.cdc.CDC;
import cof.can.udpbc.UDP_Broadcast_Client_Module;
import cof.server.interfaces.CentralDataCenter;

//��class�t�dŪ���qclient�o�e�L�Ӫ��T��
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
	
	//Ū�qclient�o�e�L�Ӫ��T��
	public void readMsgFromClient() throws IOException{
		inputString = intputStream.readUTF();
		switch (decoder.splitInstruction(inputString)) {
			case "HAVE_STARTED":
				//����"HAVE_STARTED"�T���N��sserver��currentChooseNum��
				tcpServer.incrementCurrentChooseNum();
				//��F�|���A�o�T�����Ҧ�client�A�N��C���Y�N�}�l
				if(tcpServer.getCurrentChooseNum() == 4){
					/*��TCPSM������|�쪱�a����ܧ��^����A�N��|�쪱�a�|�}�l�i��C���A�ҥHTCPSM
					 * �ݭn�I�sCDC���o�Ӥ�k�A�Ψӳq��CDC�w�g�}�l�C���F�C
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
