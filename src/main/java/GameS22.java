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
    private String winType;

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

    // New Rule Modification & New Objective Modification
    public void setSkipTurn(int skipTurn)
    {
        this.skipTurn = skipTurn;
    }

    public int getSkipTurn()
    {
        return this.skipTurn;
    }
    public void setWinType(String winType)
    {
        this.winType = winType;
    }
    public String getWinType()
    {
        return this.winType;
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
            // New Objective Modification // Adds check for which team captured 70% of the other team's pieces.
            else if(teamOne.getNumPiecesCaptured() > teamOneRecruitTarget)
            {
                winner = teamOne;
            }
            else if(teamTwo.getNumPiecesCaptured() > teamTwoRecruitTarget)
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
        // New Objective Modification // Checks for type of objective completed and sets the winType accordingly.
        if(teamOne.getTeamPieces().size() <= 0 || teamTwo.getTeamPieces().size() <= 0)
        {
            this.winType = "elimination";
        }
        else if(teamOne.getNumPiecesCaptured() > teamOneRecruitTarget || teamTwo.getNumPiecesCaptured() > teamTwoRecruitTarget)
        {
            this.winType = "pacifist";
        }

        return teamOne.getTeamPieces().size() <= 0 || teamTwo.getTeamPieces().size() <= 0
                || teamOne.getNumPiecesCaptured() > teamOneRecruitTarget
                || teamTwo.getNumPiecesCaptured() > teamTwoRecruitTarget;
    }
}
