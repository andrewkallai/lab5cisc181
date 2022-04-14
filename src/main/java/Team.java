import java.util.ArrayList;

public class Team {
    private String teamColor;
    private ArrayList<Piece> pieces;

    public Team(String teamColor, ArrayList<Piece> pieces)
    {
        this.teamColor = teamColor;
        this.pieces = pieces;
    }

    public String getTeamColor()
    {
        return this.teamColor;
    }

    public ArrayList<Piece> getTeamPieces()
    {
        return this.pieces;
    }

    public void removePieceFromTeam(Piece gamePiece)
    {
        for (int i=0;i<this.pieces.size();i++){
            if (this.pieces.get(i) == gamePiece){
                this.pieces.set(i, null);
            }
        }
    }

    public void addPieceFromTeam(Piece gamePiece)
    {
        for (int i=0;i<this.pieces.size();i++){
            if (this.pieces.get(i) == null) {
                gamePiece.setTeamColor(this.teamColor);
                this.pieces.set(i, gamePiece);
            }
            else{
                //make the array longer
            }
        }
    }
    //toString loop in loop
}
