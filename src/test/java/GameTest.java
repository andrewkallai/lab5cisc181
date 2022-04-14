import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class GameTest {
    @Test
    public void gameTests() throws Exception {

        System.out.println("Testing Game");

        // Create 4 pieces for team A
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

        // Create 4 pieces for team B
        Piece redKevin = new PieceMinion('M',"Red",
                0,0,false,true);
        Piece redBob = new PieceEvilMinion('E',"Red",1,
                1,4,false, true);
        Piece redYoudee = new PieceBlueHen('H',"Red",3,
                9,false,true);
        Piece redBuzz = new PieceBuzz('B',"Red",2,1,
                true,false,true);
        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamB = new ArrayList<>();
        piecesTeamB.add(redKevin);
        piecesTeamB.add(redBuzz);
        piecesTeamB.add(redYoudee);
        piecesTeamB.add(redBob);
        // Create a team object
        Team teamB = new Team("Red",piecesTeamB);

        Game ourGame = new Game(8, 8,teamA, teamB);
        System.out.println(ourGame.toString());

        assertEquals(teamA,ourGame.getCurrentTeam());
        assertEquals(teamB,ourGame.getOpponentTeam());
        assertTrue(ourGame.isTurn(teamA));
        assertFalse(ourGame.isTurn(teamB));
        ourGame.changeTurn();
        assertEquals(teamB,ourGame.getCurrentTeam());
        assertEquals(teamA,ourGame.getOpponentTeam());
        assertTrue(ourGame.isTurn(teamB));
        assertFalse(ourGame.isTurn(teamA));

    }
}