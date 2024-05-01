package application;

//Class to represent pairs of source and LED
public class PairInt {

	  int source;
      int LED;

      PairInt(int source, int LED) {
          this.source = source;
          this.LED = LED;
      }

      @Override
      public String toString() {
          return "(S:" + source + ", L:" + LED + ")";
      }
      
}
