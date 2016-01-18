import java.io.IOException;

import cot.can.udpus.UDP_Update_Server_Module;
import cof.hong.tcpcm.TcpClient;


public class Main 
{
	private static TcpClient tc; 
	private static UDP_Update_Server_Module udpus;
	
	public static void main(String[] args) throws InterruptedException
	{
		try {
			tc = TcpClient.getInstance();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread thread = new Thread(tc);
		thread.start();
		
		udpus = new UDP_Update_Server_Module(8888);
		try {
			udpus.initUDPServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
