/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Sets up a board square on a board. Includes adding and removing pieces to the square.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class BoardSquare {
    private boolean isEmpty = true;
    private Piece representPiece;
    private String color;
    public BoardSquare(String color){
        this.color = color;
    }
    public Piece getPiece(){
        return representPiece;
    }
    public boolean isEmpty(){
        return isEmpty;
    }
    public String getSquareColor(){
        return this.color;
    }

    public void setPiece(Piece gamePiece){
        representPiece = gamePiece;
        isEmpty = false;
    }

    /**
     * Removes a Piece from the board square.
     * @return Piece
     */
    public Piece removePiece(){
        Piece holdReturn = representPiece;
        representPiece = null;
        isEmpty = true;
        return holdReturn;
    }


    /**Returns information about the Piece on the board square, else indicates that the square is empty.
     * @return String
     */
    @Override
    public String toString(){
        if (representPiece == null){
            return "-------";
        }
        else{
            return "-" + representPiece.toString() + "-";
        }
    }
}
