import java.util.*;

public class Game
{
    private GameBoard board;
    private Team teamOne;
    private Team teamTwo;
    private String turn;

    private void initializeGameBoard(int numRows, int numColumns)
    {
        board = new GameBoard(numRows, numColumns);
        ArrayList<Piece> teamOnePieces = teamOne.getTeamPieces();
        ArrayList<Piece> teamTwoPieces = teamTwo.getTeamPieces();

        for(Piece teamOnePiece : teamOnePieces)
        {
            board.findRandomEmptySpace().setPiece(teamOnePiece);
        }

        for(Piece teamTwoPiece : teamTwoPieces)
        {
            board.findRandomEmptySpace().setPiece(teamTwoPiece);
        }
    }

    public Game(int numRows, int numColumns, Team team1, Team team2)
    {
        this.teamOne = team1;
        this.teamTwo = team2;
        turn = team1.getTeamColor(); // get color of first team passed
        initializeGameBoard(numRows, numColumns);
    }

    public GameBoard getGameBoard() {
        return board;
    }

    public Team getCurrentTeam()
    {
        Team currentTeam;
        if(teamOne.getTeamColor().equals(turn))
        {
            currentTeam = teamOne;
        }
        else
        {
            currentTeam = teamTwo;
        }

        return currentTeam;
    }

    public Team getOpponentTeam()
    {
        Team opponentTeam;
        if(teamOne.getTeamColor().equals(turn))
        {
            opponentTeam = teamTwo;
        }
        else
        {
            opponentTeam = teamOne;
        }

        return opponentTeam;
    }

    public boolean isTurn(Team team)
    {
        return team.getTeamColor().equals(turn);
    }

    public BoardSquare[][] getBoardSquares()
    {
        return board.getSquares();
    }

    public void changeTurn()
    {
        if(teamOne.getTeamColor().equals(turn))
        {
            turn = teamTwo.getTeamColor();
        }
        else
        {
            turn = teamOne.getTeamColor();
        }
    }

    @Override
    public String toString()
    {
        StringBuilder retString = new StringBuilder();
        retString.append("Game Board:\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getGameBoard().toString())
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getCurrentTeam().toString() + "\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getOpponentTeam().toString() + "\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\nIt is Team " + getCurrentTeam().getTeamColor() + "'s turn\n");
        return retString.toString();
    }
}
