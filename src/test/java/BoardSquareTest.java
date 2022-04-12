import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardSquareTest{

    @Test
    public void testingBoardSpace()  {
        System.out.println("Testing Board Square Class");

        BoardSquare testSquare11 = new BoardSquare("Black");
        BoardSquare testSquare12 = new BoardSquare("White");

        // getColor
        assertEquals("Black",testSquare11.getSquareColor());
        assertEquals("White",testSquare12.getSquareColor());

        // isEmpty
        assertTrue(testSquare11.isEmpty());
        assertTrue(testSquare12.isEmpty());

        //toString
        assertEquals("-------",testSquare11.toString());
        assertEquals("-------",testSquare12.toString());

        //getPiece - none
        assertNull(testSquare11.getPiece());
        assertNull(testSquare12.getPiece());

        //setPiece
        // place some pieces on the GameBoard
        Piece kevin = new PieceMinion('M',"Blu",
                0,0,false,true);
        Piece bob = new PieceEvilMinion('E',"Red",1,
                1,4,false, true);
        testSquare11.setPiece(kevin);
        testSquare12.setPiece(bob);

        //toString
        assertEquals("-Blu M-",testSquare11.toString());
        assertEquals("-Red E-",testSquare12.toString());

        //getPiece - returns Piece but doesn't remove it from space
        assertSame(kevin,testSquare11.getPiece());
        assertSame(bob,testSquare12.getPiece());

        //isEmpty
        assertFalse(testSquare11.isEmpty());
        assertFalse(testSquare12.isEmpty());

        // removePiece - returns Piece and removes it from space
        assertSame(kevin,testSquare11.removePiece());
        assertSame(bob,testSquare12.removePiece());

        //isEmpty
        assertTrue(testSquare11.isEmpty());
        assertTrue(testSquare12.isEmpty());

        //toString
        assertEquals("-------",testSquare11.toString());
        assertEquals("-------",testSquare12.toString());
    }
}