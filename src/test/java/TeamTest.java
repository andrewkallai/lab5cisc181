import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class TeamTest {
    @Test
    public void getTeamName() {

        System.out.println("Testing Team class");

        Piece blueKevin = new PieceMinion('M',"Blu",
                0,0,false,true);
        Piece blueBob = new PieceEvilMinion('E',"Blu",1,
                1,4,false, true);
        Piece blueYoudee = new PieceBlueHen('H',"Blu",3,
                9,false,true);
        Piece blueBuzz = new PieceBuzz('B',"Blu",2,1,
                true,false,true);

        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(blueKevin);
        piecesTeamA.add(blueBuzz);
        piecesTeamA.add(blueYoudee);
        piecesTeamA.add(blueBob);

        // Create a team object
        Team teamA = new Team("Blu",piecesTeamA);

        // check teamA
        assertEquals("Blu",teamA.getTeamColor());
        assertEquals(4,teamA.getTeamPieces().size());

        assertTrue(teamA.getTeamPieces().contains(blueKevin));
        assertTrue(teamA.getTeamPieces().contains(blueBuzz));
        assertTrue(teamA.getTeamPieces().contains(blueYoudee));
        assertTrue(teamA.getTeamPieces().contains(blueBob));

        // remove a Piece from teamA
        teamA.removePieceFromTeam(blueYoudee);
        assertEquals(3,teamA.getTeamPieces().size());
        assertFalse(teamA.getTeamPieces().contains(blueYoudee));

        // create another Team object
        ArrayList<Piece> piecesTeamB = new ArrayList<>();
        Team teamB = new Team("Red",piecesTeamB);
        // add a Piece the blueYoudee to team B
        teamB.addPieceToTeam(blueYoudee);
        //check that blueYoudee color was changed to Red
        assertEquals("Red",blueYoudee.getTeamColor());

        // check team B pieces
        assertEquals(1,teamB.getTeamPieces().size());
        assertTrue(teamB.getTeamPieces().contains(blueYoudee));

        System.out.println(teamA.toString());
        System.out.println(teamB.toString());
    }
}