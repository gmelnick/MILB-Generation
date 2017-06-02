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

    private static int calcGetsOnWith(double aveOBP, double obp) {
    	double rawOBP = (20 * Math.sqrt(obp/aveOBP) - 12.2);
    	double remainder = 10 * (rawOPB - (int)rawOPB);
    	if (remainder < 2) return 6;
    	if (remainder < 5) return 5;
    	if (remainder < 8) return 4;
		return 3;
    }

    private static int calcOBP(double aveOBP, double obp) {
    	return (int)(20 * Math.sqrt(obp/aveOBP) - 12.2);
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

			int plateAppearances = atBats + walks; // not really true...

			stats[ONBASE] = calcOBP(aveOBP, obp);
			stats[OSO] = 1;

			stats[WALK] = calcGetsOnWith(aveOBP, obp);




		}


	}
}
