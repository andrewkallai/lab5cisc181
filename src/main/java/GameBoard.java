/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Sets up the board squares for the game board.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class GameBoard
{
    private int numRows;
    private int numColumns;
    private BoardSquare[][] squares;
    private BoardSquare blackHole; // used to create a special BoardSquare. Behavior handled in BoardSquare class.

    public GameBoard(int numRows, int numColumns)
    {
        this.numRows = numRows;
        this.numColumns = numColumns;

        squares = new BoardSquare[numRows][numColumns];
        setUpEmptyBoard();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public BoardSquare[][] getSquares() {
        return squares;
    }

    /**
     * Checks if the point is inbounds.
     * @param row: int for the row to check
     * @param column: int for the column to check
     * @return boolean for whether the row,column pair is inbounds
     */
    public boolean inBounds(int row, int column)
    {
        // accepts row index and column index to check if that point is inbounds
        // Point is in-bounds if point is less than number of columns and rows and the point is positive
        return (row < this.numRows && column < this.numColumns) && (row >= 0 && column >= 0);
    }

    /**
     * Sets up an empty board of BoardSquares.
     * @return void
     */
    private void setUpEmptyBoard()
    {
        for(int row = 0; row < numRows; row++)
        {
            for(int column = 0; column < numColumns; column++)
            {
                String color;
                if((column + row) % 2 == 0)
                {
                    color = "White";
                }
                else
                {
                    color = "Black";
                }

                squares[row][column] = new BoardSquare(color);
            }
        }

        blackHole = findRandomEmptySpace();
        blackHole.setBlackHole(true);
    }

    /**
     * Returns the location of an empty space on the game board.
     * @return BoardSquare
     */
    public BoardSquare findRandomEmptySpace()
    {
        int row = (int)(Math.random() * numRows);
        int column = (int)(Math.random() * numColumns);

        while(!squares[row][column].isEmpty() && !squares[row][column].isBlackHole())
        {
            row = (int)(Math.random() * numRows);
            column = (int)(Math.random() * numColumns);
        }

        //System.out.println("DEBUG:\nFound empty space a row: " + row + " column: " + column);
        return squares[row][column];
    }

    /**
     * Returns the makeup of the game board in a string format.
     * @return String
     */
    @Override
    public String toString(){
        StringBuilder boardString = new StringBuilder();
        boardString.append("Col :       ");

        for(int col = 0; col < squares[0].length; col++){
            boardString.append(col + "        ");
        }
        boardString.append("\n");
        for(int row = 0; row < squares.length; row++){
            boardString.append("Row : " + row + "   ");
            for(int col = 0; col < squares[row].length; col++){
                boardString.append(squares[row][col].toString() + "  ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }
}
