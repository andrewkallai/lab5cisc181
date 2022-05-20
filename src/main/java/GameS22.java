/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *     Creates the specific game being played this year as an extension of Game
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-4
 */

public class GameS22 extends Game
{
    private int numTeamOnePieces = teamOne.getTeamPieces().size();
    private int numTeamTwoPieces = teamOne.getTeamPieces().size();

    private int teamOneRecruitTarget = (int) (numTeamOnePieces * 0.7);
    private int teamTwoRecruitTarget = (int) (numTeamTwoPieces * 0.7);

    private int skipTurn = 0;

    public GameS22(int row, int column, Team teamOne, Team teamTwo)
    {
        super(row, column, teamOne, teamTwo);
    }

    /**
     * @return if there is a winner
     */
    @Override
    public boolean isAWinner() {
        return getWinner() != null;
    }

    public void setSkipTurn(int skipTurn)
    {
        this.skipTurn = skipTurn;
    }

    public int getSkipTurn()
    {
        return this.skipTurn;
    }

    /**
     * @return get the winner, if there is one
     */
    @Override
    public Team getWinner() {
        Team winner;

        if(isGameEnded())
        {
            if(teamOne.getTeamPieces().size() > 0)
            {
                winner = teamOne;
            }
            else if(teamTwo.getTeamPieces().size() > 0)
            {
                winner = teamTwo;
            }
            else if(teamOne.getNumPiecesCaptured() > (int) (numTeamOnePieces * 0.7))
            {
                winner = teamOne;
            }
            else if(teamTwo.getNumPiecesCaptured() > (int) (numTeamTwoPieces * 0.7))
            {
                winner = teamTwo;
            }
            else
            {
                winner = null;
            }
        }
        else
        {
            winner = null;
        }

        return winner;
    }

    /**
     * @return checks whether the game has ended or not
     */
    @Override
    public boolean isGameEnded()
    {
        //System.out.println("DEBUG:\nteamOne captured pieces: " + teamOne.getNumPiecesCaptured() + " Needs: " + teamOneRecruitTarget + " to win.");
        //System.out.println("DEBUG:\nteamTwo captured pieces: " + teamTwo.getNumPiecesCaptured() + " Needs: " + teamTwoRecruitTarget + " to win.");

        return teamOne.getTeamPieces().size() <= 0 || teamTwo.getTeamPieces().size() <= 0
                || teamOne.getNumPiecesCaptured() > teamOneRecruitTarget
                || teamTwo.getNumPiecesCaptured() > teamTwoRecruitTarget;
    }
}
