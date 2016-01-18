package cof.hong.tcpsm;

import java.io.DataOutputStream;
import java.io.IOException;

/*此class負責發送訊息給client
 *writeMsgToClient(int clientNo, String msg)：送給特定的client
 *writeMsgToAllClient(String msg)：送給所有的client
 */
public class WriteMessage{
	private DataOutputStream outputStream;
	private TcpServer tcpServer;
	public static WriteMessage wm = null;
	
	public static WriteMessage getInstance() throws IOException{
		if(wm == null)
			wm = new WriteMessage();
		return wm;
	}

	public WriteMessage() throws IOException  {
		tcpServer = TcpServer.getInstance();
	}
	
	public void writeMsgToClient(int clientNo, String msg) throws IOException{
		outputStream = new DataOutputStream(tcpServer.getClientInfoList().get(clientNo).getClientSocket().getOutputStream());
		outputStream.writeUTF(msg);
		outputStream.flush();
	}
	
	public void writeMsgToAllClient(String msg) throws IOException{
		for(int i = 0 ; i < tcpServer.getClientInfoList().size(); i++){
			outputStream = new DataOutputStream(tcpServer.getClientInfoList().get(i).getClientSocket().getOutputStream());
			outputStream.writeUTF(msg);
			outputStream.flush();
		}
	}
}
