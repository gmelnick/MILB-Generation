/***********************************************************************
  *  Name:    Gil Melnick
  *
  *********************************************************************/

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Batter {

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

    
    private String name;
    private int[] stats;
    
    /* Creates a new batter, reading from Stdin to get stats */
    public Batter() {
        name = StdIn.readString() + " " + StdIn.readString();
        stats = new int[HOMERUN + 1];
        for (int i=0; i < stats.length; i++)
            stats[i] = StdIn.readInt();
    }

    /* Creates a new batter with name "first last" and stats as 
       specified by the other ints */
       /*
    public Batter (String first, String last, int onbase, int position,
            int fielding, int speed, int ogb, int ofb, int walk, 
            int single, int singlep, int doubl, int triple, 
            int homerun)  {
        name = first + last;
        stats = new int[HOMERUN + 1];
        stats[ONBASE] = onbase; stats[POSITION] = position; 
        stats[FIELDING] = fielding; stats[SPEED] = speed; 
        stats[OGB] = ogb; stats[OFB] = ofb; stats[WALK] = walk; 
        stats[SINGLE] = single; stats[SINGLEP] = singlep; 
        stats[DOUBL] = doubl; stats[TRIPLE] = triple; 
        stats[HOMERUN] = homerun;
    }
    */
    /* Creates a new batter with name "first last" and stats as
       specified by array s. */
    public Batter (String first, String last, int[] s) {
        if (s.length != (HOMERUN + 1))
            throw new IllegalArgumentException();
        name = first + last;
        stats = new int[HOMERUN + 1];
        for (int i=0; i < stats.length; i++)
            stats[i] = s[i];
    }
        
    
    public void show() {
        StdOut.println(name + ", " + stats[POSITION] + "+" + 
                stats[FIELDING]);
        StdOut.println("On Base " + stats[ONBASE] + ", Speed " + 
                stats[SPEED]);
        StdOut.println("Strikeout: " + stats[OSO] + "-" + 
                (stats[OGB]-1));
        StdOut.println("Groundout: " + stats[OGB] + "-" + 
                (stats[OFB]-1));
        StdOut.println("Flyout: " + stats[OFB] + "-" + (stats[WALK]-1));
        StdOut.println("Walk: " + stats[WALK] + "-" + (stats[SINGLE]-1));
        StdOut.println("Single: " + stats[SINGLE] + "-" + 
                (stats[SINGLEP]-1));
        StdOut.println("Single+: " + stats[SINGLEP] + "-" + 
                (stats[DOUBL]-1));
        StdOut.println("Double: " + stats[DOUBL] + "-" + 
                (stats[TRIPLE]-1));
        StdOut.println("Triple: " + stats[TRIPLE] + "-" + 
                (stats[HOMERUN]-1));
        StdOut.println("HomeRun: " + stats[HOMERUN] + "-20");
        
    }
    
    public String getName() {
        return name;   
    }
    public int onBase() {
        return stats[0];
    }
    public int position() {
        return stats[1];
    }
    public int fielding() {
        return stats[2];
    }
    public int speed() {
        return stats[3];
    }
    /*
    public BIP bat() {
        int n = StdRandom.uniform(1, 21);
        if (n >= stats[11])
            return new BIP("homerun", 4);
        else if (n >= stats[10])
            return new BIP("triple", 3);
        else if (n >= stats[9])
            return new BIP("double", 2);
        else if (n >= stats[8]) 
            return new BIP("single+", 1);
        else if (n >= stats[7])
            return new BIP("single", 1);
        else if (n >= stats[6])
            return new BIP("walk", 1);
        else if (n >= stats[5])
            return new BIP("flyout", 0);
        else if (n >= stats[4])
            return new BIP("groundout", 0);
        else
            return new BIP("strikeout", 0);
        
    }
    public BIP batAgainst(Pitcher p) {
        int n = StdRandom.uniform(1, 21);
        BIP result;
        if (n + p.control() > this.onBase()) {
            result = p.bat();
        }
        else {
            result = this.bat();   
        }
        return result;
    }
    
    */
    public static void main(String[] args) {
        Batter b = new Batter();
        b.show();

    }
    
}