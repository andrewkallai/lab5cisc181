/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Creates a PieceMinion game piece for the game board. Extends the Piece.java class.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class PieceMinion extends Piece{
    protected int numRecruits;
    protected int numTimesSpawned;

    public static int MAX_NUM_SPAWNED = 3;

    public PieceMinion(int numRecruits, int numTimesSpawned) {
        super('M',"- -", false, true);
        this.numRecruits = numRecruits;
        this.numTimesSpawned = numTimesSpawned;
    }

    public PieceMinion(char symbol, String teamColor, int numRecruits, int numTimesSpawned, boolean hidden, boolean original) {
        super(symbol,teamColor, hidden, original);
        this.numRecruits = numRecruits;
        this.numTimesSpawned = numTimesSpawned;
    }

    public PieceMinion(){
        this(0,0);
    }

    public int getNumRecruits() {
        return numRecruits;
    }
    public int getNumTimesSpawned() {
        return numTimesSpawned;
    }


    public void setNumRecruits(int numRecruits) {
        this.numRecruits = numRecruits;
    }

    /**Prints out "Bello!"
     * Implementation of the abstract speak method in Piece.
     * @return void
     */
    public void speak(){
        System.out.println("Bello!");
    }

    /**
     * Confirms that the Piece has a valid path.
     * @param fromSquareRow: int
     * @param fromSquareCol: int
     * @param toSquareRow: int
     * @param toSquareCol: int
     * @return boolean
     */
    public boolean validMovePath(int fromSquareRow, int fromSquareCol,
                                 int toSquareRow, int toSquareCol) {
        // You will implement this method in a later step
        // each Piece will have a different valid path
        return true;
    }

    /**
     * Takes a PieceMinion and makes a copy of it: only hidden status, original status, numRecruits, and
     * numTimesSpawned are uniquely set.
     * Inherits features from spawn in Piece.
     * @return PieceBlueHen
     */
    public PieceMinion spawn(){ //was PieceMinion1
        return new PieceMinion(Character.toLowerCase(this.symbol),
                this.teamColor,1,
                0,
                false,
                false);
    }

    /**
     * Confirms that the PieceMinion can be created.
     * @return boolean
     */
    public boolean canSpawn(){
        return original && numTimesSpawned < MAX_NUM_SPAWNED;
    }

}