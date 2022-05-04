public class GameS22 extends Game
{
    public GameS22(int row, int column, Team teamOne, Team teamTwo)
    {
        super(row, column, teamOne, teamTwo);
    }

    @Override
    public boolean isAWinner() {
        return getWinner() != null;
    }

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

    @Override
    public boolean isGameEnded()
    {
        boolean gameOver = false;
        if(teamOne.getTeamPieces().size() <= 0 || teamTwo.getTeamPieces().size() <= 0)
        {
            gameOver = true;
        }

        return gameOver;
    }
}
