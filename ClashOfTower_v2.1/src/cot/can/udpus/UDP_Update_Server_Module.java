package cot.can.udpus;

import java.net.*;

import cot.ko.dom.DynamicObjectModule;
import cot.ko.uim.InformationCenter;

public class UDP_Update_Server_Module {
	int port;// connection port
	private DynamicObjectModule dom = DynamicObjectModule.getInstance();
	private InformationCenter ic = InformationCenter.getInstance();

	public UDP_Update_Server_Module(int openPortNum) {
		port = openPortNum; // set the port
	}

	public void initUDPServer() throws Exception {
		run(); // execute the server
	}

	public void run() throws Exception {
		final int msgSize = 8000; // message MAX size 20000.
		byte buffer[] = new byte[msgSize]; // set the message buffer

		while (true) {
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			// set the receive UDP server
			DatagramSocket socket = new DatagramSocket(port);
			socket.receive(packet); // receive packet��
			// transfer the receive message to String��
			String receiveMsg = new String(buffer, 0, packet.getLength());
			System.out.println(receiveMsg);
			String msgArray[] = receiveMsg.split("_");
			for (int i = 0; i < msgArray.length; i++) {

				judgeCommand(msgArray[i]);
			}
			socket.close();
		}
	}

	// Responsible for decoder
	public void judgeCommand(String receiveMsg) {
		int directionAU = 0;// addUnit direction
		int directionSUP = 0;// setUnitProperty direction
		String msgArray[] = receiveMsg.split("#");
		switch (msgArray[0]) {
		case "initTowerPosition":
			String subMsgArrayITP[] = msgArray[1].split("-");
			int playerITP[][] = new int[4][3];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {
					playerITP[i][j] = Integer.parseInt(subMsgArrayITP[i]
							.split(";")[j]);
				}
			}
			dom.initTowerPosition(playerITP);
			break;
		case "removeUnit":
			String subMsgArrayRU[] = msgArray[1].split(";");
			dom.removeUnit(Integer.parseInt(subMsgArrayRU[0]),
					Integer.parseInt(subMsgArrayRU[1]));
			break;
		case "addUnit":
			String subMsgArrayAU[] = msgArray[1].split(";");
			if (subMsgArrayAU[7].equals("right")) {
				directionAU = 0;
			} else {
				directionAU = 1;
			}
			dom.addUnit(Integer.parseInt(subMsgArrayAU[0]),
					Integer.parseInt(subMsgArrayAU[1]), subMsgArrayAU[2],
					Integer.parseInt(subMsgArrayAU[3]),
					Integer.parseInt(subMsgArrayAU[4]),
					Integer.parseInt(subMsgArrayAU[5]), subMsgArrayAU[6],
					directionAU);
			break;
		case "setUnitProperty":
			String subMsgArraySUP[] = msgArray[1].split(";");
			if (subMsgArraySUP[4].equals("right")) {
				directionSUP = 0;
			} else {
				directionSUP = 1;
			}
			dom.updateUnit(Integer.parseInt(subMsgArraySUP[0]),
					Integer.parseInt(subMsgArraySUP[1]),
					Integer.parseInt(subMsgArraySUP[2]),
					Integer.parseInt(subMsgArraySUP[3]), directionSUP,
					subMsgArraySUP[5]);
			break;
		case "addMessenger":
			String subMsgArrayAM[] = msgArray[1].split(";");
			dom.addMessenger(Integer.parseInt(subMsgArrayAM[0]),
					Integer.parseInt(subMsgArrayAM[1]),
					Integer.parseInt(subMsgArrayAM[2]));
			break;
		case "removeMessenger":
			dom.removeMessenger(Integer.parseInt(msgArray[1]));
			break;
		case "setMessengerProperty":
			String subMsgArraySMP[] = msgArray[1].split(";");
			dom.updateMessenger(Integer.parseInt(subMsgArraySMP[0]),
					Integer.parseInt(subMsgArraySMP[1]),
					Integer.parseInt(subMsgArraySMP[2]));
			break;
		case "sentMessengerArriveMsg":
			ic.sentMessengerArriveMsg(Integer.parseInt(msgArray[1]));
			break;
		case "sentEndScore":
			String subMsgArraySES[] = msgArray[1].split("-");
			int playerSES[][] = new int[4][2];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 2; j++) {
					playerSES[i][j] = Integer.parseInt(subMsgArraySES[i]
							.split(";")[j]);
				}
			}
			ic.setEndScore(playerSES);
			break;
		case "setPlayerMoney":
			String subMsgArraySPM[] = msgArray[1].split(";");
			ic.updateMoney(Integer.parseInt(subMsgArraySPM[0]),
					Integer.parseInt(subMsgArraySPM[1]));
			break;
		case "setTowerHp":
			String subMsgArraySTH[] = msgArray[1].split(";");
			ic.updateTowerHP(Integer.parseInt(subMsgArraySTH[0]),
					Integer.parseInt(subMsgArraySTH[2]));
			break;
		case "setGameTime":
			ic.updateGameTime(msgArray[1]);
			break;
		case "setEffect":
			String subMsgArraySE[] = msgArray[1].split(";");
			for (int i = 0; i < subMsgArraySE.length; i++) {
				System.out.println(subMsgArraySE[i]);
			}
			dom.addEffect(subMsgArraySE[0], Integer.parseInt(subMsgArraySE[1]),
					Integer.parseInt(subMsgArraySE[2]));
			break;
		case "notifyHeroUnlocked":
			ic.setHeroState(Integer.parseInt(msgArray[1]), false);
			break;
		}

	}

}
