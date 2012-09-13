package hps.mint.io;

/**
 * This is singlton solution file.
 * It encapsulates the data structure used for solution.
 * It contains all parameters which are part of solution.
 * @author kunal
 *
 */

import java.util.List;
import java.util.ArrayList;

//this call is not made singleton
//because in certain cases we may want to create multiple instances 
//and compare them




public class MasterSolution {

	//define all parameters here which are the part of solution.
	//all terms are used as per the given specs in the question
	
	
	//this array contains denominations for exact change number
	//it will always have only 5 values
	// all values must be less than 99
	private ArrayList<Integer> denominations_ecn = new ArrayList<Integer>();
	
	
	//this array contains denomination for exchange number
	//it will always have only 5 values
	// all values must be less than 99
	private ArrayList<Integer> denominations_ex = new ArrayList<Integer>();
	
	//Score is sum of the costs of all non-multiples of 5 + sum of N * costs of the multiples of 5
	public double score_ecn = 0.0;
	public double score_ex = 0.0; 

	
	//This ArrayList is represents all cents values from
	// 0 - 99 
	public final ArrayList<Cent> counts_ex = new ArrayList<Cent>(100);
	public final ArrayList<Cent> counts_ecn = new ArrayList<Cent>(100);
	
	
	//getters and setters
	
	public ArrayList<Integer> getDenominations_ecn(){
		return denominations_ecn;
	}
	
	public void setDenominations_ecn(ArrayList<Integer> denominations_ecn) throws IllegalAccessException{
		//to check its validity
		
		if(denominations_ecn.size() >5){
			throw new IllegalAccessException("Cannot have more than 5 denominations");
		}
		
		//all values in ArrayList should be less than 99
		for(Integer denomination_ecn : denominations_ecn){
			if(denomination_ecn > 99){
				throw new IllegalArgumentException("deomination for exact change number cannot be greater than 99");
			}
		}
		
		this.denominations_ecn = denominations_ecn;
	}
	
	
	public ArrayList<Integer> getDenominations_ex(){
		return denominations_ex;
	}
	
	public void setDenominations_ex(ArrayList<Integer> denominations_ex) throws IllegalAccessException{
		
		//to check its validity
		if(denominations_ex.size() >5){
			throw new IllegalAccessException("Cannot have more than 5 denominations");
		}
		
		//all values in ArrayList should be less than 99
		for(Integer denomination_ex : denominations_ecn){
			if(denomination_ex > 99){
				throw new IllegalArgumentException("deomination for exchange number cannot be greater than 99");
			}
		}
		
		this.denominations_ex = denominations_ex;
	}
	
	
	// utility functions
	public void computeScore_ecn(){
		
		double sum = 0.0;
		
		Global instance = Global.getInstance();
		
		
		for(int i = 1 ; i < counts_ecn.size(); i++){
			if(i%5 == 0){
				sum += instance.N * counts_ecn.get(i).cost_ecn;
			}else{
				sum += counts_ecn.get(i).cost_ecn;
			}
		}
		
		score_ecn = sum;
	}
	
	public void computeScore_ex(){
		
		double sum = 0.0;

		Global instance = Global.getInstance();
		
		for(int i = 1 ; i < counts_ex.size(); i++){
			if(i%5 == 0){
				sum += instance.N * counts_ex.get(i).cost_ex;
			}else{
				sum += counts_ex.get(i).cost_ex;
			}
		}

		score_ex = sum;
	}
	
	
	@Override public String toString() {
		
		String result = new String();
	    String NEW_LINE = System.getProperty("line.separator");
		
	    result += "Denominations for Exact Change Number are "+denominations_ecn + NEW_LINE;
	    result += "Denominations for Exchange Number are "+denominations_ex + NEW_LINE;
	    result += "Score for Exact change Number = "+score_ecn + NEW_LINE;
	    result += "Score for Exchange Number = "+score_ex + NEW_LINE;
	    
		return result;
	
	}


	
}

/**
 * This class encapsulates  a single value
 * @author kunal
 *
 */
class Cent {
    //this represents the exact change number required to assemble current cent value 
    int cost_ecn;
    //this represents the exchange number required of current cent value 
    int cost_ex;
}


