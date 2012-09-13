package hps.mint;

import java.util.List;

public class Solution
{
    public enum Problem {
        EXACT, EXCHANGE
    }

    public List<Integer> denoms;
    public List<Integer> exactCounts;
    public List<Integer> exchangeCounts;
    public float         N;
    public float         exactScore;
    public float         exchangeScore;

    public Solution(float N) {
    	this.N = N;
    	this.exactScore = Float.MAX_VALUE;
    	this.exchangeScore = Float.MAX_VALUE;
    }
    
    public void print()
    {
        StringBuilder denomStr = new StringBuilder();
        //denomStr.append("1, ");
        for (Integer d : denoms)
        {
            denomStr.append(d);
            denomStr.append(", ");
        }
        denomStr.append("100");

        System.out.println("Denom\t\t" + denomStr + "\nNumber\t\t" + N + "\nScore\t\t" + exactScore + "/" + exchangeScore);
        
        for( int sum=0; sum<100; sum++ ) {
        	System.out.println(sum + ": " + ExactChange.coinsToString(ExactChange.getCoins(exactCounts, denoms, sum)));;
        }
        for( int sum=0; sum<100; sum++ ) {
        	;  // print exchange numbers here
        }
    }
    
    
}
