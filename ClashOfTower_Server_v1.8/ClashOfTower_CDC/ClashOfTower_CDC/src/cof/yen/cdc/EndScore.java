package cof.yen.cdc;

public class EndScore {
	private int clientNo, score;

	public EndScore(int clientNo, int score) {
		this.clientNo = clientNo;
		this.score = score;
	}

	public int getClientNo() {
		return clientNo;
	}

	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
