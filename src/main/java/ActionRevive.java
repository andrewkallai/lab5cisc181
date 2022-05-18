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
    ActionRevive(GameS22 field, int fromSquareRowIndex, int fromSquareColumnIndex, int toSquareRowIndex, int toSquareColumnIndex)
    {
        super(field, fromSquareRowIndex, fromSquareColumnIndex, toSquareRowIndex, toSquareColumnIndex);
    }

    @Override
    public void performAction() {

    }
}
