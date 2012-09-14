package hps.mint;

import hps.mint.Mint;
import java.io.*;
import java.util.List;


public class Main
{
	// These would contain the best solutions
	private static Solution exactChangeSolution;
	private static Solution exchangeSolution;

	public static void main(String[] args)
	{
		float weight = 4.0F;

		System.out.println("Executing " + ". N = " + weight);

		Mint mint;
		System.out.println("creating problem: exact");
		mint = new Mint(weight);
		Solution s = mint.solve();

		s.print();
	}

	// This would be invoked by different threads to report
	// a solution for a denomination set.
	// It would evaluate each passed Solution object looking
	// for the best solution thus far for both problems.
	public  synchronized static void judge(Solution s){

		if( s.exactScore > 0 ){// solution contains a valid exact score

			// First Solution is the best solution
			if(exactChangeSolution == null){
				exactChangeSolution = s;
			}
			else{
				//new solutions is better than previous solution 
				if(s.exactScore < exactChangeSolution.exactScore){
					exactChangeSolution = s;
				}
			}
		}

		if(s.exchangeScore > 0){

			if(exchangeSolution == null){
				exchangeSolution = s;
			}else{
				//new solutions is better than previous solution 
				if(s.exchangeScore < exchangeSolution.exchangeScore){
					exchangeSolution = s;
				}
			}
		}

	}

	//check for Best Solution
	// Would be invoked when all solutions for all denominations
	// have been calculated and passed to the judge(...) method.
	// It prints the contents of exactChangeSolution and
	// exchangeSolution in the specified format to the output file.
	public static void stop(){
		// Create output File
		try{
			// Create file 
			FileWriter fstream = new FileWriter("rky-mint-output.txt.txt");
			BufferedWriter out = new BufferedWriter(fstream);
			
			//Print EXACT_CHANGE_NUMBER:
			out.write("EXACT_CHANGE_NUMBER: \n");
			out.write("COIN_VALUES: "+formatDenoms(exactChangeSolution.denoms) + "\n");
			
			//Iterate through all values 
			for(int i =0 ; i < 100 ; i++){
				out.write(i+": "+formatDenoms(ExactChange.getCoins(exactChangeSolution.exactCounts, exactChangeSolution.denoms, i))+"\n");
			}
			
			//Print EXCHANGE_NUMBER
			out.write("EXCHANGE_NUMBER: \n");
			out.write("COIN_VALUES: "+formatDenoms(exchangeSolution.denoms) + "\n");
			
			for( int i=0; i<100; i++ ) {
	        	System.out.println(i + ": " + formatCoinsString(exchangeSolution.exchangeCounts, exchangeSolution.exactCounts, exchangeSolution.denoms, i) );
	        }
			
			//Close the output stream
			out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static String formatDenoms(List<Integer> denoms) {
		
		Integer d1 = denoms.get(0);
		Integer d2 = denoms.get(1);
		Integer d3 = denoms.get(2);
		Integer d4 = denoms.get(3);
		Integer d5 = denoms.get(4);

		return (d1+","+d2+","+d3+","+d4+","+d5);

	}
	
	static public String formatCoinsString(List<Integer> counts, List<Integer> exactCounts, List<Integer> denoms, int sum) {
		
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
				sb.append(ExactChange.coinsToString(pCoins));
				sb.append(";");
				sb.append(ExactChange.coinsToString(cCoins));
				return sb.toString();
			}
		}
		return "";
	}

}
