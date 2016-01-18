package cof.hong.tcpsm;

import java.net.Socket;

//此class紀錄client端的資訊，包括client代號、IP、socket
public class ClientInfo {
	private int clientNo;
	private String clientIp;
	private Socket clientSocket;
	
	public ClientInfo(int clientNo, String clientIp, Socket clientSocket){
		this.clientNo = clientNo;
		this.clientIp = clientIp;
		this.clientSocket = clientSocket;
	}
	
	public int getClientNo(){
		return clientNo;
	}
	
	public String getClientIp(){
		return clientIp;
	}
	
	public Socket getClientSocket(){
		return clientSocket;
	}
}
