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
public class BoardSquare
{
    private boolean isEmpty;

    // REGION
    // black hole variables
    private boolean isBlackHole;
    private boolean isDiscovered;
    // ENDREGION
    private Piece representPiece;
    private String color;

    // Default constructor. Creates an empty square with argument color
    public BoardSquare(String color){
        this.color = color;
        isEmpty = true;
        isBlackHole = false;
        isDiscovered = false;
    }

    // Getters
    public Piece getPiece(){
        return this.representPiece;
    }
    public boolean isEmpty(){
        return this.isEmpty;
    }
    public String getSquareColor(){
        return this.color;
    }

    // REGION
    // black hole methods // New Board Square Modification
    public boolean isBlackHole()
    {
        return this.isBlackHole;
    }
    public boolean isDiscovered()
    {
        return this.isDiscovered;
    }
    // END REGION

    /**
     * Puts the piece given in the argument on a square.
     * @param gamePiece Game Piece to place on board square
     */
    public void setPiece(Piece gamePiece){
        representPiece = gamePiece;
        isEmpty = false;
    }

    // REGION
    // black hole methods // New Board Square Modifications
    public void setBlackHole(boolean isBlackHole)
    {
        this.isBlackHole = isBlackHole;
    }

    public void setDiscovered(boolean discovered)
    {
        this.isDiscovered = discovered;
    }
    // END REGION

    /**
     * Removes a Piece from the board square.
     * @return the Piece removed
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
        if(isBlackHole && isDiscovered) // prints black hole when discovered (piece lands on square) // New Board Square Modifications
        {
            return "~~~O~~~";
        }
        else if (representPiece == null){
            return "-------";
        }
        else{
            return "-" + representPiece.toString() + "-";
        }
    }
}
