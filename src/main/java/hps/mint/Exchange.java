package hps.mint;

import java.util.ArrayList;
import java.lang.Math;
import java.util.List;

public class Exchange {

	static public ArrayList<Integer> compute(List<Integer> denoms, float N, List<Integer> exactCounts) {
		
		// copy only the first 50 elements into counts
		// TODO: instead of 100, make available only first 50. Need to accommodate in coin set print-outs as well 
		ArrayList<Integer> counts = new ArrayList<Integer>(100);
		for( int i=0; i<100; i++ ) counts.add(exactCounts.get(i));

		for( int n=0; n<50; n++ ) {
			counts.set(n, exactCounts.get(n));
			
			// pLTD = pay (less than dollar)
			// pMTD = pay (more than dollar)
			// Example: LTD sum(9)    5 coins: (5,1,1,1,1)
			//          MTD sum(109)  4 coins: (33,33,33,10)
			for( int p=0; p<100; p++ ) {
				int c = ((p-n) % 100 + 100) % 100;
				
				int minPayCount = exactCounts.get(p);
				if( p+100 < exactCounts.size() ) {      // check MTD
					minPayCount = Math.min(minPayCount, exactCounts.get(p+100));
				}
				
				counts.set(n, Math.min(counts.get(n), minPayCount+exactCounts.get(c)));
			}
		}
		for( int n=1; n<50; n++ ) {
			counts.set(50+n, counts.get(50-n));
		}
		
		return counts;
	}

	static public String getCoinsString(List<Integer> counts, List<Integer> exactCounts, List<Integer> denoms, int sum) {
		
		StringBuilder sb = new StringBuilder();
		for( int p=0; p<exactCounts.size(); p++ ) {   // iterate over p(Pay) values
			int minChange = p - sum + ( p - sum < 0 ? 100 : 0 );
			int minChangeCount = exactCounts.get(minChange);

			int c2 = minChange + 100;
			if( c2 < exactCounts.size() && exactCounts.get(c2) < minChangeCount ) {
				minChangeCount = exactCounts.get(c2);
				minChange = c2;
			}
			
			if( counts.get(sum) == exactCounts.get(p) + minChangeCount ) {
				List<Integer> pCoins = ExactChange.getCoins(exactCounts, denoms, p);
				List<Integer> cCoins = ExactChange.getCoins(exactCounts, denoms, minChange);
				sb.append("("+p+")");
				sb.append(ExactChange.coinsToString(pCoins));
				sb.append(":");
				sb.append("("+minChange+")");
				sb.append(ExactChange.coinsToString(cCoins));
				return sb.toString();
			}
		}
		return "";
	}
}
