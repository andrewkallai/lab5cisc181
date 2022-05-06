/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Sets up the fields and an abstract method for move, spawn, recruit, and attack actions.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-4
 */
public abstract class Action {
    protected GameS22 field;
    protected int fromSquareRowIndex;
    protected int fromSquareColumnIndex;
    protected int toSquareRowIndex;
    protected int toSquareColumnIndex;

    public Action(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex){
        this.field = field;
        this.fromSquareRowIndex = fromSquareRowIndex;
        this.fromSquareColumnIndex = fromSquareColumnIndex;
        this.toSquareRowIndex = toSquareRowIndex;
        this.toSquareColumnIndex = toSquareColumnIndex;
    }

    /**
     * Abstract implementation of method for actions implemented in sub-classes.
     */
    public abstract void performAction();
}
