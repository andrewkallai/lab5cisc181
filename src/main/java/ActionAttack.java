/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Attacks a given Piece and removes it, or makes a new EvilMinion if an EvilMinion is attacking it's own team's minion.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-5
 */
public class ActionAttack extends Action
{
    ActionAttack(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex){
        super(field, fromSquareRowIndex, fromSquareColumnIndex, toSquareRowIndex, toSquareColumnIndex);
    }

    /**
     * Calls an attacking Piece's speak method, removes the attacked Piece from the "to" square,
     * removes the attacked Piece from the opponent's team, removes the attacking Piece from the "from" square,
     * moves the attacker to the "to" square, and changes the turn.
     *
     * If an evil minion, call the From Piece’s speak method, remove the attacked Piece from the board,
     * remove the attacked Piece from the current team, create a new Evil Minion Piece, add the new piece to current team
     * place the new piece on the board where the Minion was, and change the turn.
     */
    public void performAction() {
        Piece attacker = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece();
        //PieceEvilMinion evilPiece = (PieceEvilMinion) attacker;
        Piece attacked = field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].getPiece();
        //PieceMinion minion = (PieceMinion) attacked;

        if ((attacker instanceof PieceEvilMinion) && (attacked instanceof PieceMinion) && attacker.getTeamColor().equals(attacked.getTeamColor()))
        {
            //field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
            attacker.speak();
            //Piece attackedPiece = field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].getPiece();
            field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].removePiece();
            field.getOpponentTeam().removePieceFromTeam(attacked);
            //Piece newPiece = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().spawn();
            Piece newPiece = attacker.spawn();
            PieceEvilMinion newEvilMinion = (PieceEvilMinion) newPiece;
            field.getCurrentTeam().addPieceToTeam(newEvilMinion);
            field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece(newEvilMinion);
            field.changeTurn();
        }
        else {
            if (attacker instanceof PieceAbominableSnowman) {
                ((PieceAbominableSnowman) attacker).decreaseMass();
                if (((PieceAbominableSnowman) attacker).getMass() == 0) {
                    field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].removePiece();
                    //field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece (movingPiece);
                }
            }
            //field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
            attacker.speak();
            //Piece attackedPiece = field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].getPiece();
            field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].removePiece();
            field.getOpponentTeam().removePieceFromTeam(attacked);
            //Piece attackingPiece = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece();
            field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].removePiece();
            field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece(attacker);
            field.changeTurn();
        }

        if(field.getSkipTurn() == 1)
        {
            field.setSkipTurn(0);
            System.out.println("Your previous action was a revive. Your turn will be skipped!");
            field.changeTurn();
        }
    }
}
