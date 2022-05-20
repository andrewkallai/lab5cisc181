/**
 * <h1>New Extended Piece Modification</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Creates a PieceNurseBlueHen game piece for the game board.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-18
 */
public class PieceNurseBlueHen extends PieceBlueHen
{
    private int numRevives;

    public PieceNurseBlueHen (int numRevives, int numAttacks){
        super('N',"NON", numAttacks, 0, false, true);
        this.numRevives = numRevives;
    }

    public PieceNurseBlueHen(char symbol, String teamColor, boolean hidden, boolean original, int numAttacks, int numRevives)
    {
        super(symbol, teamColor, numAttacks, 0, hidden, original);
        this.numRevives = numRevives;
    }

    public PieceNurseBlueHen(int numRevives)
    {
        super(0, 0);
        this.numRevives = numRevives;
    }

    public int getNumRevives() {
        return this.numRevives;
    }

    public void setNumRevives(int recruits) {
        this.numRevives = recruits;
    }

    /**
     * Confirms that the Piece can revive another Piece.
     * @param fromRow: int
     * @param fromColumn: int
     * @param toRow: int
     * @param toColumn: int
     * @return boolean
     */
    public boolean validRevivePath(int fromRow, int fromColumn, int toRow, int toColumn) {
        return true;
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
        boolean validMove = false;
        if((toRow == fromRow + 1 || toRow == fromRow - 1 || toRow == fromRow)
                && (toColumn == fromColumn + 1 || toColumn == fromColumn - 1 || toColumn == fromColumn))
        {
            validMove = true;
        }
        return validMove;
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
    public boolean validSpawnPath(int fromRow, int fromColumn, int toRow, int toColumn)
    {
        return true;
    }
}
