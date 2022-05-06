/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Recruits a Piece from an opponent's team.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-5
 */
public class ActionRecruit extends Action
{
    ActionRecruit(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex){
        super(field, fromSquareRowIndex, fromSquareColumnIndex, toSquareRowIndex, toSquareColumnIndex);
    }

    /**
     * Calls a recruited Piece's speak method, removes the recruited Piece from the opponent's team,
     * adds the recruited Piece to the current Team, and changes the turn.
     */
    public void performAction(){
        field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
        Piece recruitedPiece = field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].getPiece();
        field.getOpponentTeam().removePieceFromTeam(recruitedPiece);
        field.getCurrentTeam().addPieceToTeam(recruitedPiece);
        field.changeTurn();
    }
}
