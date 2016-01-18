package cof.hong.tcpcm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JOptionPane;

import cot.ko.dom.DynamicObjectModule;
import cot.ko.dom.RenderThread;
import cot.ko.uim.InformationCenter;
import cot.ko.uim.MainFrame;

public class TcpClient implements Runnable {
	private int port = 1234;
	private Socket clientSocket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	public static TcpClient tcpClient = null;
	// 嚙踝蕭嚙踝蕭嚙緬server嚙緻嚙盤嚙諉迎蕭嚙確嚙踝蕭
	private String inputString;
	// 嚙踝蕭嚙踝蕭嚙諛己嚙踝蕭client嚙瞇嚙踝蕭
	private int clientNo;
	private int messagecount = 1;
	private ConnectFrame connectFrame;
	private ChooseHeroFrame chooseHeroFrame;
	private Encoder coder = Encoder.getInstance();
	private String heroName = null;

	private RenderThread rt = RenderThread.getInstance();
	private InformationCenter ic = null;
	private DynamicObjectModule dom = null;
	private MainFrame mf = null;

	public static TcpClient getInstance() throws IOException {
		if (tcpClient == null) {
			tcpClient = new TcpClient();
		}
		return tcpClient;
	}

	public void run() {
		connectFrame = new ConnectFrame();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			connectServer("10.115.49.152");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// connect to server
	public void connectServer(String serverIP) throws IOException {
		clientSocket = new Socket();
		try {
			clientSocket.connect(new InetSocketAddress(serverIP, port));
			inputStream = new DataInputStream(clientSocket.getInputStream());
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			getMsgFromServer();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "can not connect to server",
					"connect error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// 讀嚙踝蕭嚙緬server嚙緻嚙箴嚙諉迎蕭嚙確嚙踝蕭
	public void getMsgFromServer() {
		try {
			while (true) {
				inputString = inputStream.readUTF();
				System.out.println(inputString);
				if (messagecount == 1) {
					if (inputString.equals("LIMITED_CONNECT")) {
						showOptionPane();
						connectFrame.connectFrame.dispose();
						break;
					} else {
						// 嚙踝蕭嚙踝蕭嚙諛己嚙踝蕭client嚙瞇嚙踝蕭
						clientNo = Integer.valueOf(inputString);
						// ic.setPlayerNumber(clientNo);
						messagecount++;
						connectFrame.connectSuccess();
					}
				}
				switch (inputString) {
				case "CHOOSE_HERO":
					turnChooseHero();
					break;
				case "START_GAME":
					turnStartGame();
					break;
				// }
				default:

					String msgArray[] = inputString.split("#");

					int directionAU = 0;

					switch (msgArray[0]) {

					case "initTowerPosition":

						String subMsgArrayITP[] = msgArray[1].split("-");

						int playerITP[][] = new int[4][3];

						for (int i = 0; i < 4; i++) {

							for (int j = 0; j < 3; j++) {

								playerITP[i][j] = Integer
										.parseInt(subMsgArrayITP[i]

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

					case "addMessenger":

						String subMsgArrayAM[] = msgArray[1].split(";");

						dom.addMessenger(Integer.parseInt(subMsgArrayAM[0]),

						Integer.parseInt(subMsgArrayAM[1]),

						Integer.parseInt(subMsgArrayAM[2]));

						break;

					case "removeMessenger":

						dom.removeMessenger(Integer.parseInt(msgArray[1]));

						break;

					case "sentMessengerArriveMsg":

						ic.sentMessengerArriveMsg(Integer.parseInt(msgArray[1]));

						break;

					case "sentEndScore":

						String subMsgArraySES[] = msgArray[1].split("-");

						int playerSES[][] = new int[4][2];

						for (int i = 0; i < 4; i++) {

							for (int j = 0; j < 2; j++) {

								playerSES[i][j] = Integer
										.parseInt(subMsgArraySES[i]

										.split(";")[j]);

							}

						}

						ic.setEndScore(playerSES);

						break;

					case "notifyHeroUnlocked":

						ic.setHeroState(Integer.parseInt(msgArray[1]), false);

						break;

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 嚙緣嚙範嚙緻嚙確嚙踝蕭嚙踝蕭server
	public void writeMsgToServer(String msg) throws IOException {
		outputStream.writeUTF(msg);
	}

	// 嚙編嚙線嚙磕嚙盤嚙罵嚙瘡嚙褕，嚙踝蕭嚙碼嚙踝蕭嚙豌佗蕭嚙踝蕭
	public void showOptionPane() {
		try {
			clientSocket.close();
			JOptionPane.showMessageDialog(null,
					"Reached the maximum number of connections",
					"connect error", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void turnChooseHero() {
		connectFrame.connectFrame.dispose();
		chooseHeroFrame = new ChooseHeroFrame(this);
	}

	public void turnStartGame() {
		chooseHeroFrame.chooseHeroFrame.dispose();

		ic = InformationCenter.getInstance();
		dom = DynamicObjectModule.getInstance();
		ic.setHeroName(heroName);
		ic.setPlayerNumber(clientNo);
		mf.getInstance();
		rt.startRenderThread();
		ic.startCoolDownTimer();
		// gameFrame = new GameFrame(this);
		/*
		 * TCPCM嚙罵嚙瘢嚙編UIM嚙踝蕭Callback嚙踝蕭k - turnStartGame()
		 * 嚙瘢嚙編嚙踝蕭UIM嚙罵嚙踝蕭嚙踝蕭嚙趣本嚙踝蕭颩
		 * ^嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭(ChooseHeroFrame)嚙璀嚙踝蕭茪嚙踝蕭嚙踝蕭嚙篌嚙窮嚙誕迎蕭嚙瘠嚙踝蕭嚙踝蕭嚙踝蕭
		 * (GameFrame)嚙璀 嚙罷嚙締嚙箠嚙踝蕭C嚙踝蕭嚙璀GameFrame嚙諒恬蕭|嚙瞌嚙瘤嚙踝蕭嚙踝蕭嚙瘠嚙踝蕭嚙踝蕭嚙踝蕭
		 */
	}

	public void setHeroName(String heroName) {
		this.heroName = heroName;
	}

	// called by UIM嚙璀嚙踝蕭嚙窮嚙箭嚙瘠嚙踝蕭嚙踝蕭嚙踝蕭嚙磕嚙踝蕭啎F嚙瑾嚙諉新嚙踝蕭嚙踝蕭嚙踝蕭嚙踝蕭嚙�
	public void addUnit(String type, int level, int clientno, int aisle)
			throws IOException {
		outputStream.writeUTF(coder.enAddUnit(type, level, clientno, aisle));
	}

	/*
	 * Called by
	 * UIM嚙瘠嚙踝蕭嚙窮嚙箭嚙瘠嚙踝蕭嚙踝蕭嚙踝蕭嚙磕嚙踝蕭啎F嚙瑾嚙踝蕭洈怚B嚙緻嚙瞌嚙踝蕭嚙窮嚙諍前嚙賠一嚙踝蕭嚙誕迎蕭(Messenger)嚙璀
	 * TCPCM嚙豎要嚙瞇嚙線嚙踝蕭嚙窮嚙編嚙磕嚙瑾嚙踝蕭洈怴v嚙踝蕭嚙確嚙踝蕭嚙編嚙碼嚙踝蕭嚙瑾嚙諉恬蕭嚙瞌嚙衛傳送嚙踝蕭TCPSM
	 */
	public void addMessenger(int clientNo) throws IOException {
		outputStream.writeUTF(coder.enAddMessenger(clientNo));
	}

	/*
	 * Called by
	 * UIM嚙瘠嚙踝蕭嚙窮嚙緻嚙褊了嚙稻嚙踢的改蕭嚙踝蕭嚙踝蕭ATCPCM嚙豎要嚙瞇嚙線嚙踝蕭嚙窮嚙緻嚙踝蕭嚙稻嚙踝蕭嚙踝蕭嚙踝蕭v嚙踝蕭嚙確嚙踝蕭嚙編嚙碼嚙踝蕭嚙瑾嚙諉恬蕭嚙瞌嚙衛傳送嚙踝蕭TCPSM嚙璀
	 * 嚙踝蕭TCPSM嚙踝蕭嚙趣此嚙踝蕭嚙瞌嚙踝蕭i嚙踝蕭挼X嚙璀嚙瘤嚙諸哨蕭茬o嚙瞌嚙瑾嚙踝蕭嚙稻嚙踝蕭o嚙褊改蕭嚙踝蕭嚙踝蕭嚙踝蕭嚙瞌
	 */
	public void callTowerSkill(int clientNo) throws IOException {
		outputStream.writeUTF(coder.enCallTowerSkill(clientNo));
	}

	/*
	 * Called by UIM嚙瘠嚙緩嚙踝蕭嚙稻嚙踢有三嚙諍升級梧蕭嚙瘢(
	 * 嚙稷嚙踝蕭hpRecover嚙畿嚙踝蕭嚙踝蕭嚙豌改蕭moneyUpfaster嚙畿嚙踝蕭嚙踝蕭嚙磕嚙踝蕭towerAtkUp)嚙璀
	 * 嚙踝蕭嚙窮嚙踝蕭嚙稻嚙踝蕭i嚙踝蕭伔嚙踝蕭_嚙踝蕭嚙璀TCPCM嚙豎要嚙瞇嚙線嚙踝蕭嚙窮嚙誕伐蕭嚙稻嚙踝蕭伔襲獊嚙緞嚙踝蕭嚙確嚙踝蕭嚙編嚙碼嚙踝蕭嚙瑾嚙諉恬蕭嚙瞌嚙衛傳送嚙踝蕭TCPSM嚙璀嚙踝蕭TCPSM嚙踝蕭嚙趣此嚙踝蕭嚙瞌
	 * 嚙踝蕭i嚙踝蕭挼X嚙璀嚙瘤嚙諸哨蕭茬o嚙瞌嚙瑾嚙踝蕭嚙稻嚙踝蕭伔讀嚙踝蕭嚙踝蕭O
	 */
	public void callTowerUpgrade(int clientNo, String updateName)
			throws IOException {
		outputStream.writeUTF(coder.enCallTowerUpgrade(clientNo, updateName));
	}

	/*
	 * Called by UIM嚙瘠嚙緩嚙踝蕭嚙踝蕭嚙踝蕭嚙緣嚙諄歹蕭嚙踝蕭Church嚙瞎Capital嚙璀嚙磊嚙踝蕭嚙確嚙踝蕭嚙瘡嚙踝蕭嚙踝項
	 * (嚙磕嚙踝蕭castle1嚙畿castle2嚙畿castle3嚙畿church1嚙畿church2嚙畿church3)嚙瘠
	 * 嚙踝蕭嚙窮嚙誕用歹蕭嚙踝蕭嚙緣嚙踝蕭嚙瘡嚙踝蕭嚙踝蕭嚙賭中嚙瑾嚙諍選項嚙踝蕭ATCPCM嚙豎要嚙瞇嚙線嚙踝蕭嚙窮嚙踝蕭嚙瘤嚙磐嚙諉歹蕭嚙踝蕭嚙踝項嚙緞嚙踝蕭嚙確嚙踝蕭嚙編嚙碼嚙踝蕭嚙踝蕭嚙瞌嚙衛傳送嚙踝蕭TCPSM嚙璀
	 * 嚙踝蕭TCPSM嚙踝蕭嚙趣此嚙踝蕭嚙瞌嚙踝蕭i嚙踝蕭挼X嚙璀嚙瘤嚙諸哨蕭茬o嚙瞌嚙瑾嚙諉使用歹蕭嚙踝蕭嚙豬能的嚙踝蕭嚙瞌
	 */
	public void callCapitalCitySkill(String skillName) throws IOException {
		outputStream
				.writeUTF(coder.enCallCapitalCitySkill(clientNo, skillName));
	}

	/*
	 * Called by
	 * UIM嚙瘠嚙踝蕭嚙窮嚙緯嚙踝蕭嚙褕級迎蕭嚙褊作嚙褕，TCPCM嚙豎要嚙瞇嚙緻嚙諉升級迎蕭嚙褊作嚙編嚙碼嚙踝蕭嚙踝蕭嚙瞌嚙衛送嚙踝蕭TCPSM
	 * TCPSM嚙踝蕭嚙踝蕭嚙璀嚙瞇嚙踝蕭嚙諸碼
	 */
	public void upgradeUnit(int level) throws IOException {
		outputStream.writeUTF(coder.enUpgradeUnit(clientNo, level));
	}
}
