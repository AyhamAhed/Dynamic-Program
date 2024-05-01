package application;

import java.util.Arrays;

public class Lines {
    int n ;
    int[] permutation;
    static int  size = 0;
    
    
    
    public Lines() {
	}
    
	public Lines(int n, int[] permutation) {
		this.n = n;
		this.permutation = permutation;
		size++;
	}



	@Override
	public String toString() {
		return "Num Of Leds with permutation [n=" + n + ", permutation=" + Arrays.toString(permutation) + "]";
	}
    
    
}

