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
	//�����ثe�s�u��client�Ӽ�
	private int currentClientNum = 0;
	//�������t��client���s��
	private int clientNo = 0;
	//�s��C��client����T(IP�BclientNo�Bsocket�s�u)
	private ClientInfo clientInfo;
	//�N�C��ClientInfo�����bArrayList��
	private ArrayList<ClientInfo> clientInfoList = new ArrayList<ClientInfo>();
	//�Nclient��IP��}�����U�ӡA��UDP�s���ɨϥ�
	private ArrayList<String> clientIpTable = new ArrayList<String>();
	public static TcpServer tcpServer = null;
	public WriteMessage writeMessage;
	//�����ثeclient�ҫ��U"start"���s����
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
		//�}��server
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
						//�����C���s�u
						socket = server.accept();
						//��s�ثe�s�u�Ӽ�
						currentClientNum++;
						recordClientInfo();
						if(currentClientNum < 5){
							new Thread(new ReadMessage(socket,cdc)).start();
							//�e�Xclient�N�����ӽШD�s�u��client
							writeMessage.writeMsgToClient(clientInfo.getClientNo(), Integer.toString(clientInfo.getClientNo()));
							
							//TCPSM�ݭn�I�sCDC���o�Ӥ�k�A�åB�N�����t��ClientNo�@���޼ơA�N�޼ƥ�J����k����
							cdc.addPlayer (clientInfo.getClientNo());
							
							if(currentClientNum == 4){
								//��H�ƨ�F4�H�A�o�e"CHOOSE_HERO"�T�����Ҧ��s�u��client
								writeMessage.writeMsgToAllClient("CHOOSE_HERO");
							}
						}else{
							/*�s�u�W�L�|���ɪ����p�A�o�e"LIMITED_CONNECT"���ӽШD�s�u��client
							 *�ç�sclient�N���η�U���s�u�ơA�ñN�ӵ�client��ƱqArrayList������
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
	
	//�N�C��client����T������clientInfoList��
	public void recordClientInfo(){
		clientInfo = new ClientInfo(clientNo, socket.getInetAddress().toString(),socket);
		//System.out.println(clientInfo.getClientIp());
		clientInfoList.add(clientInfo);
		clientIpTable.add(clientInfo.getClientIp());
		//��sclient���N��
		clientNo++;
	}
	
	//called by UDPBC�A���o�|�쪱�a��ip address
	public ArrayList<String> getIPTable(){
		return clientIpTable;
	}
	
	public ArrayList<ClientInfo> getClientInfoList(){
		return clientInfoList;
	}
	
	//��client�ݿ粒�^���ë��Ustart��A��scurrentChooseNum����
	public void incrementCurrentChooseNum(){
		currentChooseNum++;
	}
	
	//�^�ǥثe�w���U"start"���s������
	public int getCurrentChooseNum(){
		return currentChooseNum;
	}
	
}
