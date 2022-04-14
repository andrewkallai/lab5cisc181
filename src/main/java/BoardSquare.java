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

    public Piece removePiece(){
        Piece holdReturn = representPiece;
        representPiece = null;
        isEmpty = true;
        return holdReturn;
    }

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
