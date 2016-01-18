package cof.hong.tcpsm;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import cof.server.interfaces.CentralDataCenter;
import cof.yen.cdc.CDC;

public class TcpServer{
	
	private ServerSocket server;
	private Socket socket;
	private int ServerPort = 1234;
	//紀錄目前連線的client個數
	private int currentClientNum = 0;
	//紀錄分配給client的編號
	private int clientNo = 0;
	//存放每個client的資訊(IP、clientNo、socket連線)
	private ClientInfo clientInfo;
	//將每個ClientInfo紀錄在ArrayList中
	private ArrayList<ClientInfo> clientInfoList = new ArrayList<ClientInfo>();
	//將client的IP位址紀錄下來，給UDP廣播時使用
	private ArrayList<String> clientIpTable = new ArrayList<String>();
	public static TcpServer tcpServer = null;
	public WriteMessage writeMessage;
	//紀錄目前client所按下"start"按鈕次數
	private int currentChooseNum = 0; 
	private Thread acceptThread;
	public CentralDataCenter cdc; 
	
	//TcpServer singleton
	public static TcpServer getInstance() throws IOException {
		if (tcpServer == null) {
			tcpServer = new TcpServer();
		}
		return tcpServer;
	}

	public TcpServer() throws IOException {
		cdc = CDC.getInstance();
		//開啟server
		server = new ServerSocket(ServerPort);
		System.out.println("Server has already opened.");
		System.out.println("waiting for connections....");
		startAcceptThread();
	}
	
	public void startAcceptThread(){
		acceptThread = new Thread(new Runnable() {
			public void run() {
				try {
					writeMessage = new WriteMessage();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				while (true) {
					try {
						//接受每筆連線
						socket = server.accept();
						//更新目前連線個數
						currentClientNum++;
						recordClientInfo();
						if(currentClientNum < 5){
							new Thread(new ReadMessage(socket,cdc)).start();
							//送出client代號給該請求連線的client
							writeMessage.writeMsgToClient(clientInfo.getClientNo(), Integer.toString(clientInfo.getClientNo()));
							
							//TCPSM需要呼叫CDC的這個方法，並且將剛剛分配的ClientNo作為引數，將引數丟入此方法之中
							cdc.addPlayer (clientInfo.getClientNo());
							
							if(currentClientNum == 4){
								//當人數到達4人，發送"CHOOSE_HERO"訊息給所有連線的client
								writeMessage.writeMsgToAllClient("CHOOSE_HERO");
							}
						}else{
							/*連線超過四筆時的情況，發送"LIMITED_CONNECT"給該請求連線的client
							 *並更新client代號及當下的連線數，並將該筆client資料從ArrayList中移除
							 */
							writeMessage.writeMsgToClient(clientInfo.getClientNo(), "LIMITED_CONNECT");
							clientNo--;
							currentClientNum--;
							clientInfoList.remove(clientInfoList.size() - 1);
							clientIpTable.remove(clientIpTable.size() - 1);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		acceptThread.start();
	}
	
	//將每筆client的資訊紀錄至clientInfoList中
	public void recordClientInfo(){
		clientInfo = new ClientInfo(clientNo, socket.getInetAddress().toString(),socket);
		//System.out.println(clientInfo.getClientIp());
		clientInfoList.add(clientInfo);
		clientIpTable.add(clientInfo.getClientIp());
		//更新client的代號
		clientNo++;
	}
	
	//called by UDPBC，取得四位玩家的ip address
	public ArrayList<String> getIPTable(){
		return clientIpTable;
	}
	
	public ArrayList<ClientInfo> getClientInfoList(){
		return clientInfoList;
	}
	
	//當client端選完英雄並按下start後，更新currentChooseNum的值
	public void incrementCurrentChooseNum(){
		currentChooseNum++;
	}
	
	//回傳目前已按下"start"按鈕的次數
	public int getCurrentChooseNum(){
		return currentChooseNum;
	}
	
}
