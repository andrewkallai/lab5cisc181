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
        else
        {
            field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
            field.getCurrentTeam().addPieceToTeam(revivedPiece);
            field.getCurrentTeam().getDeadPieces().remove(revivedPiece);
            field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece(revivedPiece);
            field.changeTurn();
        }

        field.setSkipTurn(1);
    }
}
