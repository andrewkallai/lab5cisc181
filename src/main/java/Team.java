import java.util.ArrayList;
import java.util.Collections;
/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Sets up the properties that belong to an arbitrary Team. Can change team color and a team's Pieces.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 */
public class Team {
    private String teamColor;
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> deadPieces = new ArrayList<Piece>();
    private int numPiecesCaptured = 0;

    // default constructor to create a team with a color and arraylist of pieces
    public Team(String teamColor, ArrayList<Piece> pieces)
    {
        this.teamColor = teamColor;
        this.pieces = pieces;
    }

    // getters
    public String getTeamColor()
    {
        return this.teamColor;
    }

    public ArrayList<Piece> getTeamPieces()
    {
        return this.pieces;
    }

    // New Action Modification
    // Adds an ArrayList that keeps track of dead pieces.
    public ArrayList<Piece> getDeadPieces()
    {
        return this.deadPieces;
    }

    public int getNumPiecesCaptured()
    {
        return this.numPiecesCaptured;
    }

    /**
     * Removes a Piece from the array of Pieces held by a team. Used for attacks.
     * @param gamePiece to remove from a team
     * @return void
     */
    public void removePieceFromTeam(Piece gamePiece)
    {
        this.deadPieces.add(gamePiece);
        this.pieces.remove(gamePiece);
    }

    /**
     * Removes a Piece from the array of Pieces held by a team. Used for recruiting.
     * @param gamePiece to remove from a team
     * @return void
     */
    public void recruitPieceFromTeam(Piece gamePiece)
    {
        if(!gamePiece.isRecruited()) // only increment numPiecesCaptured if the piece has not been captured before. TODO : THIS NEEDS TO BE TESTED
        {
            numPiecesCaptured++;
        }
        gamePiece.setRecruited(true); // ensures that players can not keep re-recruiting the same piece to win the game
        this.pieces.remove(gamePiece);
    }

    /**
     * Adds a Piece to the array of Pieces held by a team.
     * @param gamePiece
     * @return void
     */
    public void addPieceToTeam(Piece gamePiece)
    {
        gamePiece.setTeamColor(this.getTeamColor());
        this.pieces.add(0, gamePiece);
    }

    /**Returns the Piece colors and symbols of the Pieces that belong to a Team.
     * @return String
     */
    @Override
    public String toString()
    {
        Collections.sort(pieces);

        String holdString = "";
        for(int index = 0;index<this.pieces.size();index++){
            holdString = holdString + pieces.get(index).toString() + " ";
        }

        // REGION // New Action Modification // Display each team's graveyard
        String graveYard = "";
        for(int index = 0;index<this.deadPieces.size();index++){
            graveYard = graveYard + deadPieces.get(index).toString() + " ";
        }
        // END REGION

        return "Team " + this.teamColor + " Pieces :\n" + holdString + "\nTeam " + this.teamColor + " Graveyard :\n" + graveYard;
    }
}
