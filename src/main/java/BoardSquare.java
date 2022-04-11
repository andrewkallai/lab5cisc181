public class BoardSquare {
    /*
    Create a class named BoardSquare with the following:
property to represent whether the space is empty or not  (true/false)
property of type Piece to represent the Piece that is located in this square
property to represent color of the board square(string)
constructor that accepts the color and sets this property – the square should not have a Piece by default (empty)
accessors - getPiece, isEmpty, getSquareColor
mutator – setPiece which accepts a Piece and sets the Piece member field and updates the empty property
mutator – removePiece which has no parameters and returns the Piece that is on this square, this method must update the Piece to null and empty member fields  (Note: Think about how you can return the Piece while also setting the piece property to null)
toString() method – accepts no parameters, returns a String
if no Piece on this square should return:  "-------" (7 dashes)
if there is a Piece on this square should return: "-"  followed by the Piece’s toString() followed by "-"
Create a new test file the same way you have for all your previous labs. Read the test cases in BoardSquareTest.java file on Canvas. Copy and paste these test cases to test your code.
     */
    private boolean isEmpty;
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
    /*
    public Piece removePiece{
        return setPiece()
    }
     */
    @Override
    public String toString(){
        if (representPiece == NullType){
            return "-------";
        }
        else{
            return "-" + representPiece.toString() + "-";
        }
    }
}
