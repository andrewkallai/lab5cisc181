/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Moves a given Piece from one square to the next.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-4
 */
public class ActionMove extends Action{
    private GameS22 field;
    private int fromSquareRowIndex;
    private int fromSquareColumnIndex;
    private int toSquareRowIndex;
    private int toSquareColumnIndex;

    ActionMove(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex){
        super(field, fromSquareRowIndex, fromSquareColumnIndex, toSquareRowIndex, toSquareColumnIndex);
    }

    /**
     * Calls a Piece's speak method, removes the given Piece from the "from" board square,
     * sets that Piece on the "to" board square, and changes the turn.
     */
    public void performAction(){
        field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
        Piece movingPiece = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece();
        field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].removePiece();
        field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece(movingPiece);
        field.changeTurn();
    }
}
