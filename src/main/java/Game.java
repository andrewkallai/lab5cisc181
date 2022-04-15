import java.util.*;

public class Game
{
    private GameBoard board;
    private Team teamOne;
    private Team teamTwo;
    private String turn;

    /**
     * Initializes the board with size numRows x numColumns and randomly places
     * each team's pieces on the board
     * @param numRows number of rows to create the game with
     * @param numColumns number of columns to create the game with
     */
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

    /**
     * 4 parameter constructor for Game class. Sets up game board and teams
     * @param numRows number of rows the game should have
     * @param numColumns number of columns the game should have
     * @param teamOne
     * @param teamTwo
     */
    public Game(int numRows, int numColumns, Team teamOne, Team teamTwo)
    {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        turn = teamOne.getTeamColor(); // get color of first team passed
        initializeGameBoard(numRows, numColumns);
    }

    // Getters
    public GameBoard getGameBoard() {
        return board;
    }

    public Team getCurrentTeam() // return the current team whose turn it is
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

    public Team getOpponentTeam() // return the team who does not have the turn
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

    public boolean isTurn(Team team) // checks if the team argument has the current turn
    {
        return team.getTeamColor().equals(turn);
    }

    public BoardSquare[][] getBoardSquares() // returns 2D array of BoardSquares
    {
        return board.getSquares();
    }

    /**
     * Changes the which team has the current turn
     */
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


    /**Returns information (positions, names) about Pieces on the game board in a string format.
     * @return String
     */
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
