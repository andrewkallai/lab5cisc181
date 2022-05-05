public class ActionMove extends Action{
    private GameS22 field;
    private int fromSquareRowIndex;
    private int fromSquareColumnIndex;
    private int toSquareRowIndex;
    private int toSquareColumnIndex;

    ActionMove(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex){
        super(field, fromSquareRowIndex, fromSquareColumnIndex, toSquareRowIndex, toSquareColumnIndex);
    }
    public void performAction(){
        field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece().speak();
        Piece hold = field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].getPiece();
        field.getBoardSquares()[fromSquareRowIndex][fromSquareColumnIndex].removePiece();
        field.getBoardSquares()[toSquareRowIndex][toSquareColumnIndex].setPiece(hold);
        field.changeTurn();
    }
}
