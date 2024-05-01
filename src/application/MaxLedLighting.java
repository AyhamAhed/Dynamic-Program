package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.control.TextArea;

public class MaxLedLighting {

	/*
	  
	 
//    public static void main(String[] args) {
//        int n = 6;
//        int[] LEDs = {1,4,2,6,5,3};
//
//        // Test case
//        Pair result = maxLedLighting(n, LEDs);
//
//        // Display the result
//        System.out.println("1. Expected Result:");
//        System.out.println("Maximum number of LEDs that can be lit: " + result.maxLitLEDs);
//        System.out.println("Selected pairs: " + result.pairs);
//
//        System.out.println("\n2. DP Table:");
//        printDPTable(n, LEDs);
//
//        System.out.println("\n3. LEDs that give the expected result:");
//        printLEDs(result.pairs);
//
//        System.out.println("\n4. Demonstrate Result:");
//        demonstrateResult(result.pairs, LEDs);
//    }

//    // Class to represent the result, including the maximum number of lit LEDs and the selected pairs
//    static class Pair {
//        int maxLitLEDs;
//        ArrayList<PairInt> pairs;
//
//        Pair(int maxLitLEDs, ArrayList<PairInt> pairs) {
//            this.maxLitLEDs = maxLitLEDs;
//            this.pairs = pairs;
//        }
//    }
//
//    // Class to represent pairs of source and LED
//    static class PairInt {
//        int source;
//        int LED;
//
//        PairInt(int source, int LED) {
//            this.source = source;
//            this.LED = LED;
//        }
//
//        @Override
//        public String toString() {
//            return "(S:" + source + ", L:" + LED + ")";
//        }
//    }
	
	*/

    // Function to find the maximum number of lit LEDs and the selected pairs
    public Pairs maxLedLighting(int n, int[] LEDs) {
        int[][] dp = new int[n + 1][n + 1]; // 2d array , DP size is (n+1 * n+1) n = num of leds
        
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (LEDs[j - 1] == i) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int maxLitLEDs = dp[n][n];
        ArrayList<PairInt> pairs = new ArrayList<>();

        int i = n, j = n;
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i - 1][j]) {//above?
                i--;
            } else if (dp[i][j] == dp[i][j - 1]) {//right?
                j--;
            } else {
                pairs.add(new PairInt(i, j));//then its from i-1 , j-1
                i--;
                j--;
            }
        }

        Collections.reverse(pairs);
        return new Pairs(maxLitLEDs, pairs);
    }


	// Print the DP table with source values for each LED
    public TextArea printDPTable(int n, int[] LEDs) {
        int[][] dp = new int[n + 1][n + 1];
        String[][] sourceTable = new String[n + 1][n + 1];

        // Initialize first column numbers as source values
        for (int i = 1; i <= n; i++) {
            sourceTable[i][0] = String.valueOf(i); 
        }

        // Initialize LED values in the first row
        for (int j = 1; j <= n; j++) {
            sourceTable[0][j] = String.valueOf(LEDs[j - 1]);
        }
        //LEDs is array of permutation 
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (LEDs[j - 1] == i) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    sourceTable[i][j] = "↖";
                } else {
                    int left = dp[i][j - 1];
                    int above = dp[i - 1][j];

                    if(left == above) {
                    	 dp[i][j] = left;
                    	sourceTable[i][j] = "←↑";
                    }
                    else if (left > above) {
                        dp[i][j] = left;
                        sourceTable[i][j] = "←";
                    } else {
                        dp[i][j] = above;
                        sourceTable[i][j] = "↑";
                    }
                }
            }
        }

//        // Print the DP table with source values for each LED
//        System.out.println("DP Table with Source and LED:");
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= n; j++) {
//                if (i == 0 && j == 0) {
//                    System.out.print("\t");
//                } else if (i == 0) {
//                    System.out.print(sourceTable[i][j] + "\t");
//                } else if (j == 0) {
//                    System.out.print(sourceTable[i][j] + "\t");
//                } else {
//                    System.out.print(dp[i][j] + sourceTable[i][j] + "\t");
//                }
//            }
//            System.out.println();
//        }
        
     // Print the DP table with source values for each LED to the TextArea
        TextArea textArea = new TextArea();
        textArea.appendText("DP Table with Source and LED:\n");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    textArea.appendText("\t");
                } else if (i == 0) {
                    textArea.appendText(sourceTable[i][j] + "\t");
                } else if (j == 0) {
                    textArea.appendText(sourceTable[i][j] + "\t");
                } else {
                    textArea.appendText(dp[i][j] + sourceTable[i][j] + "\t");
                }
            }
            textArea.appendText("\n");
        }
        return textArea;
    }

    // Print LEDs that give the expected result
    public void printLEDs(ArrayList<PairInt> pairs) {
        for (PairInt pair : pairs) {
            System.out.println(pair);
        }
    }

    // Demonstrate the result
    public void demonstrateResult(ArrayList<PairInt> pairs, int[] LEDs) {
        int[] demonstration = new int[LEDs.length];

        for (PairInt pair : pairs) {
            demonstration[pair.LED - 1] = pair.source;
        }

        for (int i = 0; i < demonstration.length; i++) {
            System.out.println("LED at position " + (i + 1) + " is from source " + demonstration[i]);
        }
    }
}
