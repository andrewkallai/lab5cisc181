/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Creates a PieceEvilMinion game piece for the game board. Extends the PieceMinion.java class.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class PieceEvilMinion extends PieceMinion {
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
    public int getNumAttacks() {
        return numAttacks;
    }

    public boolean canAttack()
    {
        return hungry;
    }

    // Setters
    public void setNumAttacks(int numAttacks) {
        this.numAttacks = numAttacks;
    }

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
}
