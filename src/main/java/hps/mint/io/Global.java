package hps.mint.io;

/**
 * 
 * Global Input File . 
 * This is a singleton class
 * All Input Parameters will be defined here
 * @author kunal
 *
 */
 
// To retrieve any value use
// Global instance = Global.getInstance();
// instance.N 

public class Global {
	
	private static final Global instance = new Global();
	
	//define all input parameters here
	//along with their input value
	
	
	/**
	 * N is the multiple for probability of occurrence of multiples of 5 w.r.t non-multiples of 5
	 * N should always be greater than 1. 
	 */
	public final float N = (float)4.3;
	
	
	private Global() {
        if (instance != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static Global getInstance() {
        return instance;
    }
}
