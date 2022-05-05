/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Creates a PieceEvilMinion game piece for the game board. Extends the PieceMinion.java class.
 * Implements Recruiter and Attacker interfaces.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class PieceEvilMinion extends PieceMinion implements Attacker, Recruiter{
    private int numAttacks;
    private boolean hungry;

    public static int MAX_NUM_ATTACKS = 4;

    // 7 Argument Constructor
    public PieceEvilMinion(char symbol, String teamColor, int numRecuits, int numAttacks,
                           int numTimesSpawned, boolean hidden, boolean original)
    {
        super(symbol, teamColor, numRecuits, numTimesSpawned, hidden, original);
        this.numAttacks = numAttacks;
        updateHungry();
    }

    // Default empty constructor
    public PieceEvilMinion()
    {
        this('E', "NON", 0, 0, 0, false, true);
    }

    // Getters
    /*
    public int getNumAttacks() {
        return numAttacks;
    }
     */

    public boolean canAttack()
    {
        return hungry;
    }

    // Setters
    /*
    public void setNumAttacks(int numAttacks) {
        this.numAttacks = numAttacks;
    }
     */

    public void updateHungry()
    {
        this.hungry = this.numAttacks < MAX_NUM_ATTACKS;
    }

    /**Prints out "Roar!".
     * Implementation of the abstract speak method in Piece.
     * @return void
     */
    public void speak()
    {
        System.out.println("Roar!");
    }

    /**
     * Confirms that the Piece has a valid path.
     * @return boolean
     */
    public boolean validMovePath()
    {
        return true;
    }

    /**
     * Returns a PieceEvilMinion with unique properties for numRecruits, numAttacks, numTimesSpawned, hidden,
     * and original.
     * Inherits features from spawn in Piece.
     * @return PieceEvilMinion
     */
    public PieceEvilMinion spawn()
    {
        this.numTimesSpawned += 1;
        return new PieceEvilMinion(Character.toLowerCase(this.symbol),
                this.teamColor, 1, 0, 0, false, false);
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
