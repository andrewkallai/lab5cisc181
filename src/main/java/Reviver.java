/**
 * <h1>Part of New Extended Piece Modification</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Sets up the methods for reviving pieces.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-18
 */
public interface Reviver {
    public abstract int getNumRevives();
    public abstract void setNumRevives(int revives);

    /**
     * Confirms if the reviving can be done.
     * The first two parameters represent the row index and column index of the board square
     * that contains the piece doing the reviving.
     * The next two parameters represent the row index and column index of the piece being revived.
     * @param rowIndex1: int
     * @param columnIndex1: int
     * @param rowIndex2: int
     * @param columnIndex2: int
     * @return boolean
     */
    public abstract  boolean validRevivePath(int rowIndex1, int columnIndex1, int rowIndex2, int columnIndex2);
}
