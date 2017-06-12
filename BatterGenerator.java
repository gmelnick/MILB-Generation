/***********************************************************************
  *  BatterGenerator.java
  *  Gil Melnick
  *********************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;


/* Reads data from StdIn with the following format:
   RK Lastname FirstInitial Team Pos G AB R H 2B 3B HR RBI BB SO SB CS AVG OBP SLG OPS
   Data of this format can easily be found on the mlb.com statistics page */

public class BatterGenerator {

	private final static int ONBASE = 0;
    private final static int POSITION = 1;
    private final static int FIELDING = 2;
    private final static int SPEED = 3;
    private final static int OSO = 4;
    private final static int OGB = 5;
    private final static int OFB = 6;
    private final static int WALK = 7;
    private final static int SINGLE = 8;
    private final static int SINGLEP = 9;
    private final static int DOUBL = 10;
    private final static int TRIPLE = 11;
    private final static int HOMERUN = 12;

    private final static int HIGHROLL = 20;

    /* Average OBP for the year, from 
       http://www.baseball-reference.com/leagues/MLB/bat.shtml */
    private final static int AVEOBP = 345; // year 2000

    
    private static int parsePosition(String position) {
    	if (position.equals("DH")) return 1;
    	if (position.equals("C")) return 2;
    	if (position.equals("1B")) return 3;
    	if (position.equals("2B")) return 4;
    	if (position.equals("3B")) return 5;
    	if (position.equals("SS")) return 6;
    	if (position.equals("LF")) return 7;
    	if (position.equals("CF")) return 8;
    	if (position.equals("RF")) return 9;
    	else return 1;
    	//throw new IllegalArgumentException(position + " is not a position");
    }

    private static int round(double value) {
    	int base = (int)value;
    	if (value - base < 0.5) return base;
    	else return base + 1;
    }
    private static double calcNumberResult(int outOfNumber, int resultType) {
    	if (outOfNumber < 0) throw new IllegalArgumentException();
    	if (outOfNumber == 0) return 0;
    	return resultType / (double)outOfNumber;
    }

    private static int calcGetsOnWith(double aveOBP, double obp) {
    	double rawOBP = (20 * Math.sqrt(obp/aveOBP) - 12.2);
    	double remainder = 10 * (rawOBP - (int)rawOBP);
    	if (remainder < 2) return 6;
    	if (remainder < 5) return 5;
    	if (remainder < 8) return 4;
		return 3;
    }

    private static int calcOB(double aveOBP, double obp) {
    	int value = (int)(20 * Math.sqrt(obp/aveOBP) - 12.2);
    	if (value < 3) value = 3;
    	return value;
    }

	public static void main(String args[]) {
		double aveOBP = Double.parseDouble(args[0]); // the average OBP for the given year
		int[] stats = new int[HOMERUN + 1];

		while (StdIn.hasNextLine()) {

			StdIn.readInt(); // rank is discarded
			String lastname = StdIn.readString(); // do something about the comma
			String firstInitial = StdIn.readString();
			StdIn.readString(); // team name is discarded
			String position = StdIn.readString();

			StdIn.readInt(); // games is discarded
			int atBats = StdIn.readInt(); 
			StdIn.readInt(); // runs discarded
			int hits = StdIn.readInt(); 
			int doubls = StdIn.readInt(); 
			int triples = StdIn.readInt(); 
			int homers = StdIn.readInt(); 
			StdIn.readInt(); // RBIs discarded
			int walks = StdIn.readInt(); 
			int strikeouts = StdIn.readInt(); 
			int stolenBases = StdIn.readInt(); 
			int caughtStealing = StdIn.readInt(); 

			double average = StdIn.readDouble(); 
			double obp = StdIn.readDouble(); 
			StdIn.readDouble(); // slugging percentage discarded
			StdIn.readDouble(); // OPS discarded

			int plateAppearances = atBats;// + walks; // not really true...

			int singles = hits - (doubls + triples + homers);
			stats[POSITION] = parsePosition(position);
			stats[FIELDING] = 1; /// temporary place holder
			stats[ONBASE] = calcOB(aveOBP, obp);
			stats[OSO] = 1;
			stats[OGB] = 1; /// temporary place holder
			stats[OFB] = 1; /// temporary place holder

			int reachedBase = hits + walks;


			stats[WALK] = calcGetsOnWith(aveOBP, obp);

			int reachRange = HIGHROLL + 1 - stats[WALK];
			//stats[SINGLE] = stats[WALK] + round(reachRange * calcNumberResult(reachedBase, walks));

			int hitRange = HIGHROLL + 1 - stats[SINGLE];

			stats[HOMERUN] = HIGHROLL + 1 - round(reachRange * calcNumberResult(reachedBase, homers));
			stats[TRIPLE] = stats[HOMERUN] - round(reachRange * calcNumberResult(reachedBase, triples));
			stats[DOUBL] = stats[TRIPLE] - round(reachRange * calcNumberResult(reachedBase, doubls));
			stats[SINGLE] = stats[DOUBL] - round(reachRange * calcNumberResult(reachedBase, singles));
			

			stats[SINGLEP] = stats[DOUBL]; /// temporary placeholder

			Batter batter = new Batter(firstInitial, lastname, stats);
			batter.show();
			StdOut.println();




		}


	}
}
