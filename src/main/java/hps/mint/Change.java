package hps.mint;

import java.util.List;

public class Change {
	public Solution compute(List<Integer> denoms, float N) {
		
		Solution s = new Solution(N);
		s.denoms = denoms;
		s.exactCounts = ExactChange.compute(denoms, N);
		s.exchangeCounts = Exchange.compute(denoms, N, s.exactCounts);
		
		s.exactScore = Mint.score(s.exactCounts, N);
		s.exchangeScore = Mint.score(s.exchangeCounts, N);
		
		return s;
	}
}
