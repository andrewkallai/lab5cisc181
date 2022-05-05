/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Creates a PieceBlueHen game piece for the game board. Extends the Piece.java class.
 * Implements Recruiter and Attacker interfaces.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class PieceBlueHen extends Piece implements Attacker, Recruiter{
    private int numAttacks;
    private int numRecruits;
    private boolean flies;

    final public int MAX_NUM_ATTACKS = 3;

    public PieceBlueHen (int numAttacks, int numRecruits){
        super('H',"NON", false, true);
        this.numAttacks = numAttacks;
        this.numRecruits = numRecruits;
        updateFly();
    }

    public PieceBlueHen (char symbol, String teamColor, int numAttacks, int numRecruits, boolean hidden, boolean original){
        super(symbol,teamColor, hidden, original);
        this.numAttacks = numAttacks;
        this.numRecruits = numRecruits;
        updateFly();
    }
/*
    public int getNumAttacks(){
        return this.numAttacks;
    }
    public int getNumRecruits(){
        return this.numRecruits;
    }
*/
    public boolean canFly()    {
        return this.flies;
    }
/*
    public void setNumAttacks(int numAttacks)    {
        this.numAttacks = numAttacks;
        updateFly();
    }

    public void setNumRecruits(int numRecruits)    {
        this.numRecruits = numRecruits;
    }
*/
    private void updateFly(){
        if (this.numAttacks < MAX_NUM_ATTACKS){
            this.flies = true;
        }
        else {
            this.flies = false;
        }
    }

    /**Prints out "Go UD!"
     * Implementation of abstract speak method in Piece.
     * @return void
     */
    public void speak(){
        System.out.println("Go UD!");
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
     * Takes a PieceBlueHen and makes a copy of it: only hidden status and original status are uniquely set.
     * Inherits features from spawn in Piece.
     * @return PieceBlueHen
     */
    public PieceBlueHen spawn()
    {
        PieceBlueHen copyHen =
                new PieceBlueHen(Character.toLowerCase(this.symbol),
                        this.teamColor,this.numAttacks,this.numRecruits,
                        false,false);//add things inside
        return copyHen;
    }

    /**
     * Confirms that the PieceBlueHen can be created.
     * @return boolean
     */
    public boolean canSpawn(){
        return true;
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
     * @param rowIndex1: int
     * @param columnIndex1: int
     * @param rowIndex2: int
     * @param columnIndex2: int
     * @return boolean
     */
    @Override
    public boolean validAttackPath(int rowIndex1, int columnIndex1, int rowIndex2, int columnIndex2){
        return true;
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
     * Confirms if the recruiting can be done.
     * The first two parameters represent the row index and column index of the board square
     * that contains the piece doing the recruiting.
     * The next two parameters represent the row index and column index of the piece being recruited.
     * @param rowIndex1: int
     * @param columnIndex1: int
     * @param rowIndex2: int
     * @param columnIndex2: int
     * @return boolean
     */
    @Override
    public boolean validRecruitPath(int rowIndex1, int columnIndex1, int rowIndex2, int columnIndex2){
        return true;
    }
}

