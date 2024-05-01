package application;

import java.util.ArrayList;


// Class to represent the result, including the maximum number of lit LEDs and the selected pairs
public class Pairs {

	 int maxLitLEDs;
     ArrayList<PairInt> pairs;
     
     public Pairs() {
     }

    public Pairs(int maxLitLEDs, ArrayList<PairInt> pairs) {
         this.maxLitLEDs = maxLitLEDs;
         this.pairs = pairs;
     }

	@Override
	public String toString() {
		return "The Result of maxLitLEDs=" + maxLitLEDs + ", pairs=" + pairs ;
	}
    
    
	
}
