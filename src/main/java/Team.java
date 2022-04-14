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
                this.pieces.remove(i);
            }
        }
    }

    public void addPieceToTeam(Piece gamePiece) {
            gamePiece.setTeamColor(getTeamColor());
            this.pieces.add(0, gamePiece);
    }
    @Override
    public String toString(){
        String holdString = "";
        for(int index = 0;index<this.pieces.size();index++){
            holdString = holdString + pieces.get(index).toString() + " ";
        }
        return "Team " + this.teamColor + " Pieces :\n" + holdString;
    }
}
