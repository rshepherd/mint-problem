package hps.mint;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

abstract class Mint {
	
	protected float N = 1.0F;
	protected ArrayList<Integer> cachedCounts;
	protected ArrayList<Integer> cachedDenoms;
	
	public Mint(float N) {
		this.N = N;
	}
	
	public void solve() {
		//TODO: add timer (-Yuriy)
		
		//TODO: add permutations (-Yuriy)
		
		System.out.println("INFO: Mint.solve()");
		
		List<Integer> denoms = new ArrayList<Integer>();
		denoms.add(1);
		denoms.add(5);
		denoms.add(10);
		denoms.add(25);
		
		this.cachedDenoms = new ArrayList<Integer>(denoms);
		
		// sort in decreasing order
		Collections.sort(cachedDenoms, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return -1 * a.compareTo(b);
            }
        });
		
		// uses cachedDenoms to set cachedCounts
		compute();
	}
	
	abstract public boolean compute();
	
	/*
	 * This function should print the sets of coins needed for a each sum value.
	 */
	public void print() {
		for( int i=0; i<cachedCounts.size(); i++ )
			System.out.println( i + ": " + cachedCounts.get(i) );
	}
	
}
