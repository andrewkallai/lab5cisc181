import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    public static String boardString(int numRows, int numColumns){
        StringBuilder boardString = new StringBuilder();
        boardString.append("Col :       ");

        for(int col = 0; col < numColumns; col++){
            boardString.append(col + "        ");
        }
        boardString.append("\n");
        for(int row = 0; row < numRows; row++){
            boardString.append("Row : " + row + "   ");
            for(int col = 0; col < numColumns; col++){
                boardString.append("-------" + "  ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    @Test
    public void testingBoard()  {
        System.out.println("Testing Board Class");

        int numRows = 6;
        int numCols = 10;
        GameBoard testGameBoard = new GameBoard(numRows,numCols);
        // print the GameBoard all spaces should be empty
        System.out.println(testGameBoard.toString());
        assertEquals(boardString(numRows,numCols),testGameBoard.toString());

        assertEquals(numRows,testGameBoard.getNumRows());
        assertEquals(numCols,testGameBoard.getNumColumns());

        assertTrue(testGameBoard.inBounds(0,0));
        assertTrue(testGameBoard.inBounds(numRows-1, numCols-1));
        assertFalse(testGameBoard.inBounds(numRows, numCols));
        assertFalse(testGameBoard.inBounds(-1,-1));

        // place some pieces on the GameBoard
        Piece kevin = new PieceMinion('M',"Blu",
                0,0,false,true);
        testGameBoard.getSquares()[0][0].setPiece(kevin);
        Piece bob = new PieceEvilMinion('E',"Red",1,
                1,4,false, true);
        testGameBoard.getSquares()[numRows-1][numCols-1].setPiece(bob);
        Piece youdee = new PieceBlueHen('H',"Red",3,
                9,false,true);
        testGameBoard.getSquares()[2][1].setPiece(youdee);
        Piece buzz = new PieceBuzz('B',"Gre",2,1,
                true,false,true);
        testGameBoard.getSquares()[3][2].setPiece(buzz);
        System.out.println(testGameBoard.toString());

        // testing random empty space method
        BoardSquare emptySpace = testGameBoard.findRandomEmptySpace();
        assertTrue(emptySpace.isEmpty());
        assertNull(emptySpace.getPiece());
        emptySpace.setPiece(buzz);
        emptySpace = testGameBoard.findRandomEmptySpace();

        assertTrue(emptySpace.isEmpty());
        assertNull(emptySpace.getPiece());
        emptySpace.setPiece(kevin);
        System.out.println(testGameBoard.toString());

        //spawn a Piece and place on the GameBoard
        Piece stuart = bob.spawn();
        assertTrue(stuart instanceof  PieceEvilMinion);
        testGameBoard.getSquares()[numRows-3][numCols-3].setPiece(stuart);
        System.out.println(testGameBoard.toString());
    }

}