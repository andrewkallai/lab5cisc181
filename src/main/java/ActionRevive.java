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
 * @since 2022-05-18
 */

public class ActionRevive extends Action
{
    Piece revivedPiece;
    ActionRevive(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex, Piece revivedPiece)
    {
        super(field, fromSquareRowIndex, fromSquareColumnIndex, toSquareRowIndex, toSquareColumnIndex);
        this.revivedPiece = revivedPiece;
    }

    @Override
    public void performAction() {
        // REGION New Board Square Modification
        if(field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].isBlackHole())
        {
            if(!field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].isDiscovered())
            {
                field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setDiscovered(true);
            }
            field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
            System.out.println("You entered a black hole! You have lost your spawned piece!");
            field.changeTurn();
        }
        // END REGION
        else
        {
            // New Action Modification
            // Adds a piece to the team, removes it from the deadPieces list, and sets it on the board (revives the piece)
            field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
            field.getCurrentTeam().addPieceToTeam(revivedPiece);
            field.getCurrentTeam().getDeadPieces().remove(revivedPiece);
            field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece(revivedPiece);
            field.changeTurn();
        }

        // New Rule Modifcation
        field.setSkipTurn(1);
    }
}
