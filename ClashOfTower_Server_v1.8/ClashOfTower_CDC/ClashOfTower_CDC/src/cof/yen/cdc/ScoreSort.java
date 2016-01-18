package cof.yen.cdc;

import java.util.Comparator;

public class ScoreSort implements Comparator<EndScore> {
	@Override
	public int compare(EndScore o1, EndScore o2) {
		return o2.getScore() - o1.getScore();
	}
}