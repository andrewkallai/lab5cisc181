/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Creates a PieceBuzz game piece for the game board. Extends the Piece.java class.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class PieceBuzz extends Piece{
    private int numAttacks;
    private int numTimesBeenAttacked;
    private boolean workingLaser;

    // 3 argument constructor
    public PieceBuzz(int numAttacks, int numTimesBeenAttacked, boolean workingLaser) {
        super('B',"- -", false, true);
        this.numAttacks = numAttacks;
        this.numTimesBeenAttacked = numTimesBeenAttacked;
        this.workingLaser = workingLaser;
    }

    // 7 argument constructor
    public PieceBuzz(char symbol, String teamColor, int numAttacks, int numTimesBeenAttacked, boolean workingLaser, boolean hidden, boolean original) {
        super(symbol,teamColor, hidden, original);
        this.numAttacks = numAttacks;
        this.numTimesBeenAttacked = numTimesBeenAttacked;
        this.workingLaser = workingLaser;
    }

    // Getters
    public int getNumAttacks() {
        return numAttacks;
    }
    public int getNumTimesBeenAttacked() {
        return numTimesBeenAttacked;
    }
    public boolean canAttack(){
        return workingLaser;
    }

    // Setters
    public void setWorkingLaser(boolean workingLaser) {
        this.workingLaser = workingLaser;
    }
    public void setNumAttacks(int numAttacks)  {
        this.numAttacks = numAttacks;
    }
    public void updateNumTimesBeenAttacked(){
        this.numTimesBeenAttacked += 1;
        this.workingLaser = false;
    }

    /**Prints out "To Infinity and Beyond!"
     * Implementation of the abstract speak method in Piece.
     * @return void
     */
    public void speak(){
        System.out.println("To Infinity and Beyond!");
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
     * Returns null to indicate that a PieceBuzz cannot be spawned.
     * Inherits features from spawn in Piece.
     * @return Piece
     */
    public Piece spawn(){
        return null;
    }

    /**
     * Confirms that the PieceBuzz can be created.
     * @return boolean
     */
    public boolean canSpawn(){
        return false;
    }

}
