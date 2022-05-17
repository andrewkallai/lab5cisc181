/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Creates a PieceMinion game piece for the game board. Extends the Piece.java class. Implements Recruiter interface.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class PieceMinion extends Piece implements Recruiter{
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
/*
    public int getNumRecruits() {
        return numRecruits;
    }
    */
    public int getNumTimesSpawned() {
        return numTimesSpawned;
    }

/*
    public void setNumRecruits(int numRecruits) {
        this.numRecruits = numRecruits;
    }
*/
    /**Prints out "Bello!"
     * Implementation of the abstract speak method in Piece.
     * @return void
     */
    public void speak(){
        System.out.println("Bello!");
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

    @Override
    public int getNumRecruits() {
        return this.numRecruits;
    }
    @Override
    public void setNumRecruits(int recruits) {
        this.numRecruits = recruits;
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
        boolean validPath = false;

        // checks if both the row and column in the from and to square are different.
        // If they are, it is not a valid path since Minion can only move row wise (any number) OR column wise (+- 2)
        if(Math.abs(fromRow - toRow) > 0 && Math.abs(fromColumn - toColumn) > 0)
        {
            validPath = false;
        }
        else
        {
            if(Math.abs(fromRow - toRow) > 0)
            {
                validPath = true;
            }
            else if(Math.abs(fromColumn - toColumn) > 0 && fromRow == toRow)
            {
                validPath = toColumn == fromColumn + 2 || toColumn == fromColumn - 2;
            }
        }

        return validPath;
    }

    /**
     * Confirms if the recruiting can be done.
     * The first two parameters represent the row index and column index of the board square
     * that contains the piece doing the recruiting.
     * The next two parameters represent the row index and column index of the piece being recruited.
     * @param fromRow: int
     * @param fromColumn: int
     * @param toRow: int
     * @param toColumn: int
     * @return boolean
     */
    @Override
    public boolean validRecruitPath(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        boolean validPath = false;

        // checks if both the row and column in the from and to square are different.
        // If they are, it is not a valid path since Minion can only move row wise (any number) OR column wise (+- 2)
        if(Math.abs(fromRow - toRow) > 0 && Math.abs(fromColumn - toColumn) > 0)
        {
            validPath = false;
        }
        else
        {
            if(Math.abs(fromRow - toRow) > 0)
            {
                validPath = true;
            }
            else if(Math.abs(fromColumn - toColumn) > 0 && fromRow == toRow)
            {
                validPath = toColumn == fromColumn + 2 || toColumn == fromColumn - 2;
            }
        }

        return validPath;
    }

    @Override
    public boolean validSpawnPath(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        return true;
    }

}