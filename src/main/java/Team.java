public class Team {
    private String teamColor;
    private Piece[] ArrayList;
    public Team(String teamColor, Piece[] ArrayList){
        this.teamColor = teamColor;
        this.ArrayList = ArrayList;
    }
    public String getTeamColor(){
        return this.teamColor;
    }
    public Piece[] getTeamPieces(){
        return this.ArrayList;
    }
    public void removePieceFromTeam(Piece gamePiece){
        for (int i=0;i<this.ArrayList.length;i++){
            if (this.ArrayList[i] == gamePiece){
                this.ArrayList[i] = null;
            }
        }
    }
    public void addPieceFromTeam(Piece gamePiece){
        for (int i=0;i<this.ArrayList.length;i++){
            if (this.ArrayList[i] == null) {
                gamePiece.setTeamColor(this.teamColor);
                this.ArrayList[i] = gamePiece;
            }
            else{
                //make the array longer
            }
        }
    }
    //toString loop in loop
}
