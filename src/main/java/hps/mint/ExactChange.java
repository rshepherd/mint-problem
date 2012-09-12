package hps.mint;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class ExactChange extends Mint {
	
	public ExactChange(float N) {
		super(N);
	}
	
	// !!! Assumes sorted (in decreasing order) cachedDenoms
	public boolean compute() {
		System.out.println("INFO: ExactChage.compute()");
		
		// Initialize map (sorted assumption)
		if( this.cachedDenoms == null )
			System.out.println("cachedDenoms is null");
		this.cachedCounts = new ArrayList<Integer>(Collections.nCopies(100 + this.cachedDenoms.get(0), Integer.MAX_VALUE));
		for( Integer i : this.cachedDenoms )
			if( i < this.cachedCounts.size() )  this.cachedCounts.set(i, 1);
		this.cachedCounts.set(0, 0);
		if( this.cachedCounts.size() > 100 )
			this.cachedCounts.set(100, 0);
		
		// Fill map
		for( int i=0; i<this.cachedCounts.size(); i++ ) {
			for( Integer j : this.cachedDenoms ) {
				if( j <= i ) {
					int possibleMin = this.cachedCounts.get(i-j) + 1;
					if( this.cachedCounts.get(i) > possibleMin )
						this.cachedCounts.set(i, possibleMin);
				}
			}
		}
		
		return true;
	}
	
	public List<Integer> getCoins(int sum) {
		List<Integer> coins = new ArrayList<Integer>(10);
		
		while( sum % 100 > 0 )
		{
			for( Integer d : cachedDenoms )
			{
				if( sum < d )  { continue; }
				if( cachedCounts.get(sum-d) == cachedCounts.get(sum)-1 )
				{
					coins.add(d);
					sum -= d;
					break;
				}
			}
		}
		
		return coins;
	}
	
	public void print() {
		for( int i=0; i<cachedCounts.size(); i++ )
		{
			StringBuilder sb = new StringBuilder(i + ": " + cachedCounts.get(i) + ":[");
			for( Integer d : getCoins(i) )
				sb.append(d+" ");
			sb.replace(sb.length()-1, sb.length(), "]");
			System.out.println(sb);
		}
	}
	
}
