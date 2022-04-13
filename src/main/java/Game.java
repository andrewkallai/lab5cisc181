import java.util.Collections;

public class Game
{
    private GameBoard gameBoard;
    private Team team1;
    private Team team2;
    private String turn;

    private void initializeGameBoard(int numRows, int numColumns)
    {
        gameBoard = new GameBoard(numRows, numColumns);
        // set up each Team's pieces at random squares
    }

    public Game(int numRows, int numColumns, Team team1, Team team2)
    {
        this.team1 = team1;
        this.team2 = team2;
        turn = team1.name // get name of first team passed
        initializeGameBoard(numRows, numColumns);
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Team getCurrentTeam()
    {
        // Return team whose has the current turn
    }

    public Team getOpponentTeam()
    {
        // return the other team
    }

    public boolean isTurn(Team team)
    {
        // return if argument team has the current turn
    }

    public BoardSquare getBoardSquares()
    {
        // get 2d array of board squares
    }

    public void changeTurn()
    {
        // change current team's turn
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
