public class GameBoard
{
    private int numRows;
    private int numColumns;
    private BoardSquare[][] squares;

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

    // accepts row index and column index to check if that point is inbounds
    // Point is in-bounds if point is less than number of columns and rows and the point is positive
    public boolean inBounds(int row, int column)
    {
        return (row < this.numRows && column < this.numColumns) && (row >= 0 && column >= 0);
    }

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
    }

    public BoardSquare findRandomEmptySpace()
    {
        int row = (int)(Math.random() * numRows);
        int column = (int)(Math.random() * numColumns);

        while(!squares[row][column].isEmpty())
        {
            row = (int)(Math.random() * numRows);
            column = (int)(Math.random() * numColumns);
        }

        return squares[row][column];
    }

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
