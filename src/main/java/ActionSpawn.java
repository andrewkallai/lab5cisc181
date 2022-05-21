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
    ActionSpawn(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex){
        super(field, fromSquareRowIndex, fromSquareColumnIndex, toSquareRowIndex, toSquareColumnIndex);
    }

    /**
     * Calls a Piece's speak method, calls the same Piece's spawn method, adds the spawned Piece to the current Team,
     * sets that spawned Piece on the "to" board square, and changes the turn.
     */
    public void performAction(){

        // REGION // REMOVES PIECE IF IT SPAWNS ON A BLACK HOLE
        // Board Square Modification
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
            field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
            Piece spawnedPiece = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().spawn();
            field.getCurrentTeam().addPieceToTeam(spawnedPiece);
            field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece(spawnedPiece);
            Piece oldPiece = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece();
            //New Piece Modification
            if (oldPiece instanceof PieceAbominableSnowman) {
                ((PieceAbominableSnowman) oldPiece).decreaseMass();
                ((PieceAbominableSnowman) oldPiece).decreaseMass();
                if (((PieceAbominableSnowman) oldPiece).getMass() == 0) {
                    field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].removePiece();
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
