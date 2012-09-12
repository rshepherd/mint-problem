package hps.mint;
import java.util.Collections;
import java.util.ArrayList;


public class ExactChange extends Mint {
	
	public ExactChange(float N) {
		super(N);
	}
	
	// !!! Assumes sorted (in decreasing order) cachedDenoms
	public boolean compute() {
		System.out.println("INFO: ExactChage.compute()");
		
		// Initialize map (sorted assumption)
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
	
	
	
}