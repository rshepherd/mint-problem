package hps.mint;

import java.util.ArrayList;
import java.lang.Math;

public class Exchange extends Mint {

	public Exchange(float N) {
		super(N);
	}
	
	public boolean compute() {
		System.out.println("INFO: Exchange.compute()");
		
		ExactChange ecn = new ExactChange(this.N);
		ecn.cachedDenoms = this.cachedDenoms;
		ecn.compute();

		// copy only the first 50 elements into counts
		// TODO: instead of 100, make available only first 50. Need to accommodate in coin set print-outs as well 
		this.cachedCounts = new ArrayList<Integer>(100);
		for( int i=0; i<100; i++ ) this.cachedCounts.add(ecn.cachedCounts.get(i));

		for( int n=0; n<50; n++ ) {
			this.cachedCounts.set(n, ecn.cachedCounts.get(n));
			
			// pLTD = pay (less than dollar)
			// pMTD = pay (more than dollar)
			// Example: LTD sum(9)    5 coins: (5,1,1,1,1)
			//          MTD sum(109)  4 coins: (33,33,33,10)
			for( int p=0; p<100; p++ ) {
				int c = ((p-n) % 100 + 100) % 100;
				
				int minPayCount = ecn.cachedCounts.get(p);
				if( p+100 < ecn.cachedCounts.size() ) {      // check MTD
					minPayCount = Math.min(minPayCount, ecn.cachedCounts.get(p+100));
				}
				
				this.cachedCounts.set(n, Math.min(this.cachedCounts.get(n), minPayCount+ecn.cachedCounts.get(c)));
			}
		}
		for( int n=1; n<50; n++ ) {
			this.cachedCounts.set(50+n, this.cachedCounts.get(50-n));
		}
		
		return true;
	}

}
