/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Sets up the methods for attacking pieces.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-4
 */
public interface Attacker {

    public abstract int getNumAttacks();
    public abstract void setNumAttacks(int attacks);

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
    public abstract boolean validAttackPath(int rowIndex1, int columnIndex1, int rowIndex2, int columnIndex2);
}
