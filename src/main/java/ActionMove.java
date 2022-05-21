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
public class ActionMove extends Action
{
    ActionMove(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex){
        super(field, fromSquareRowIndex, fromSquareColumnIndex, toSquareRowIndex, toSquareColumnIndex);
    }

    /**
     * Calls a Piece's speak method, removes the given Piece from the "from" board square,
     * sets that Piece on the "to" board square, and changes the turn.
     */
    public void performAction(){
        // REGION - removes piece if it lands on the black hole // New Board Square Modification
        if(field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].isBlackHole())
        {
            if(!field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].isDiscovered())
            {
                field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setDiscovered(true);
            }

            Piece mover = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece();
            mover.speak();
            System.out.println("You entered a black hole! You have lost your piece!");
            field.getCurrentTeam().removePieceFromTeam(mover);
            field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].removePiece();
            field.changeTurn();
        }
        // END REGION
        else
        {
            field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
            Piece movingPiece = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece();
            field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].removePiece();
            field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece(movingPiece);
            //New Piece Modification
            if (movingPiece instanceof PieceAbominableSnowman) {
                ((PieceAbominableSnowman) movingPiece).decreaseMass();
                if (((PieceAbominableSnowman) movingPiece).getMass() == 0) {
                    field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].removePiece();
                }
            }
            field.changeTurn();
        }

        // New Rule Modification
        if(field.getSkipTurn() == 1)
        {
            field.setSkipTurn(0);
            System.out.println("Your previous action was a revive. Your turn will be skipped!");
            field.changeTurn();
        }
    }
}
