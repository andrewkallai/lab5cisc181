public abstract class Action {
    private GameS22 field;
    private int fromSquareRowIndex;
    private int fromSquareColumnIndex;
    private int toSquareRowIndex;
    private int toSquareColumnIndex;

    public Action(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex){
        this.field = field;
        this.fromSquareRowIndex = fromSquareRowIndex;
        this.fromSquareColumnIndex = fromSquareColumnIndex;
        this.toSquareRowIndex = toSquareRowIndex;
        this.toSquareColumnIndex = toSquareColumnIndex;
    }

    public abstract void performAction();
}
