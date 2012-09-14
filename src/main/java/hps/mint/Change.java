package hps.mint;

import java.util.List;

public class Change {

	public static Solution compute(List<Integer> denoms, float N) {
		Solution s = new Solution(N);
		s.denoms = denoms;
		s.exactCounts = ExactChange.compute(denoms, N);
		s.exchangeCounts = Exchange.compute(denoms, N, s.exactCounts);
		s.exactScore = Change.score(s.exactCounts, N);
		s.exchangeScore = Change.score(s.exchangeCounts, N);

		return s;
	}

    public static float score(List<Integer> counts, float N) {
		float sum = 0.0F;
		for (int i = 0; i < 100; i++) {
			// can't do for-each loop because exact change might have 100 + max_denom elements
			sum += (i % 5 == 0) ? N * counts.get(i) : counts.get(i);
		}
		
		return sum;
	}
}
