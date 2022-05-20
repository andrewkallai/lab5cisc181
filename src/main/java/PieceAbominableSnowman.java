/**
 * <h1>New Piece Modification</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Creates a PieceAbominableSnowman for the game.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class PieceAbominableSnowman extends Piece implements Attacker, Recruiter{
    private int numAttacks;
    private int numRecruits;
    private int mass;

    public PieceAbominableSnowman (int numAttacks, int numRecruits){
        super('S',"NON", false, true);
        this.numAttacks = numAttacks;
        this.numRecruits = numRecruits;
        this.mass = 100;
    }

    public PieceAbominableSnowman (char symbol, String teamColor, int numAttacks, int numRecruits, boolean hidden, boolean original, int mass){
        super(symbol,teamColor, hidden, original);
        this.numAttacks = numAttacks;
        this.numRecruits = numRecruits;
        this.mass = mass;
    }

    public int getMass()
    {
        return this.mass;
    }
/*
    public boolean canSpawn(){
        return true;
    }*/

    public void decreaseMass(){
        this.mass = this.mass - 10;
    }
    @Override
    public int getNumAttacks(){
        return this.numAttacks;
    }
    @Override
    public void setNumAttacks(int attacks){
        this.numAttacks = attacks;
    }

    @Override
    public int getNumRecruits() {
        return this.numRecruits;
    }
    @Override
    public void setNumRecruits(int recruits) {
        this.numRecruits = recruits;
    }

    /**Prints out "RAAAAWWWWR!"
     * Implementation of abstract speak method in Piece.
     * @return void
     */
    public void speak(){
        System.out.println("RAAAAWWWWR!");
    }

    /**
     * Takes a PieceAbominableSnowman and makes a new one: only hidden status and original status are uniquely set.
     * Mass is by default 80 for non-original snowmen.
     * Inherits features from spawn in Piece.
     * @return PieceBlueHen
     */
    public PieceAbominableSnowman spawn()
    {
        PieceAbominableSnowman makeSnowman =
                new PieceAbominableSnowman(Character.toLowerCase(this.symbol),
                        this.teamColor,this.numAttacks,this.numRecruits,
                        false,false, 80);//add things inside
        return makeSnowman;
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
        boolean validMove = false;
        if((fromColumn == toColumn && (toRow == fromRow + 1  || toRow == fromRow - 1)) ||
                (fromRow == toRow && (toColumn == fromColumn + 1 || toColumn == fromColumn -1)))
            {
                validMove = true;
            }
        return validMove;
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
        boolean validMove = false;
        if((fromColumn == toColumn && (toRow < fromRow + 3  || toRow > fromRow - 3)) ||
                (fromRow == toRow && (toColumn < fromColumn + 3 || toColumn > fromColumn -3)))
        {
            validMove = true;
        }
        return validMove;
    }

    /**
     * Confirms if the attack move can be done given the path.
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
        boolean validMove = false;
        if((fromColumn == toColumn && (toRow < fromRow + 3  || toRow > fromRow - 3)) ||
                (fromRow == toRow && (toColumn < fromColumn + 3 || toColumn > fromColumn -3)))
        {
            validMove = true;
        }
        return validMove;
    }

    /**
     * Confirms if the new Piece can be spawned given the path.
     * @param fromRow row of the square containing the piece that is performing the action
     * @param fromColumn column of the square that should contain the piece that is performing the action
     * @param toRow row of the square the action should be performed on
     * @param toColumn column of the square the action should be performed on
     * @return
     */
    @Override
    public boolean validSpawnPath(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        return true;
    }

}