/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Spawns a new Piece on an open board square.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-5
 */
public class ActionSpawn extends Action
{
    private GameS22 field;
    private int fromSquareRowIndex;
    private int fromSquareColumnIndex;
    private int toSquareRowIndex;
    private int toSquareColumnIndex;

    ActionSpawn(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex){
        super(field, fromSquareRowIndex, fromSquareColumnIndex, toSquareRowIndex, toSquareColumnIndex);
    }

    /**
     * Calls a Piece's speak method, calls the same Piece's spawn method, adds the spawned Piece to the current Team,
     * sets that spawned Piece on the "to" board square, and changes the turn.
     */
    public void performAction(){
        field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
        Piece spawnedPiece = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().spawn();
        field.getCurrentTeam().addPieceToTeam(spawnedPiece);
        field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece(spawnedPiece);
        field.changeTurn();
    }
}
