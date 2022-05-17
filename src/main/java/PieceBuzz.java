/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Creates a PieceBuzz game piece for the game board. Extends the Piece.java class. Implements Attacker interface.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class PieceBuzz extends Piece implements Attacker{
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
    /*
    public int getNumAttacks() {
        return numAttacks;
    }
     */
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
    /*
    public void setNumAttacks(int numAttacks)  {
        this.numAttacks = numAttacks;
    }
     */
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

    @Override
    public int getNumAttacks(){
        return this.numAttacks;
    }
    @Override
    public void setNumAttacks(int attacks){
        this.numAttacks = attacks;
    }

    /**
     * Confirms if the attacking can be done.
     * The first two parameters represent the row index and column index of the board square
     * that contains the piece attacking.
     * The next two parameters represent the row index and column index of the piece being attacked.
     * @param fromRow: int
     * @param fromColumn: int
     * @param toRow: int
     * @param toColumn: int
     * @return boolean
     */
    @Override
    public boolean validAttackPath(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        boolean validPath = false;

        // checks if both the row and column in the from and to square are different.
        // If they are, it is not a valid path since Buzz can only move row wise (any number) OR column wise (+- 2)
        if(Math.abs(fromRow - toRow) > 0 && Math.abs(fromColumn - toColumn) > 0)
        {
            validPath = false;
        }
        else
        {
            if(Math.abs(fromRow - toRow) > 0)
            {
                validPath = toColumn == fromColumn + 2 || toColumn == fromColumn - 2;
            }
            else if(Math.abs(fromColumn - toColumn) > 0 && fromRow == toRow)
            {
                validPath = true;
            }
        }

        return validPath;
    }

    /**
     * Confirms that the Piece has a valid path.
     * @param fromRow: int
     * @param fromColumn: int
     * @param toRow: int
     * @param toColumn: int
     * @return boolean
     */
    public boolean validMovePath(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        return true; // Buzz can move anywhere on the board
    }

    @Override
    public boolean validSpawnPath(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        return false; // Buzz can not spawn
    }

}
