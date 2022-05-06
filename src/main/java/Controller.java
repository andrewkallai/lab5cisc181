import java.util.ArrayList;

public class Controller
{
    GameS22 game;
    TextView textView;

    public GameS22 setUpGameModel(){
        // Create 4 pieces for team A
        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(new PieceMinion('M',"Blu",
                0,0,false,true));
        piecesTeamA.add(new PieceBuzz('B',"Blu",2,1,
                true,false,true));
        piecesTeamA.add(new PieceBlueHen('H',"Blu",3,
                9,false,true));
        piecesTeamA.add(new PieceEvilMinion('E',"Blu",1,
                1,4,false, true));
        // Create a team object
        Team teamA = new Team("Blu",piecesTeamA);
        // Create 4 pieces for team B
        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamB = new ArrayList<>();
        piecesTeamB.add(new PieceMinion('M',"Red",
                0,0,false,true));
        piecesTeamB.add(new PieceBlueHen('H',"Red",3,
                9,false,true));
        piecesTeamB.add(new PieceBuzz('B',"Red",2,1,
                true,false,true));
        piecesTeamB.add(new PieceEvilMinion('E',"Red",1,
                1,4,false, true));
        // Create a team object
        Team teamB = new Team("Red",piecesTeamB);
        // Create an instance of the game
        return new GameS22(8, 8,teamA, teamB);
    }

    public Controller()
    {
        game = setUpGameModel();
        textView = new TextView(); // constructor for TextView
        textView.updateView(); // TextView method
    }

    private void carryOutAction(int fromRow, int fromColumn, int toRow, int toColumn, char action)
    {
        if(action == 'M')
        {
            ActionMove move = new ActionMove(game, fromRow, fromColumn, toRow, toColumn);
            move.performAction();
        }
        else if(action == 'A')
        {
            ActionAttack attack = new ActionAttack(game, fromRow, fromColumn, toRow, toColumn);
            attack.performAction();
        }
        else if(action == 'R')
        {
            ActionRecruit recruit = new ActionRecruit(game, fromRow, fromColumn, toRow, toColumn);
            recruit.performAction();
        }
        else if(action == 'S')
        {
            ActionSpawn spawn = new ActionSpawn(game, fromRow, fromColumn, toRow, toColumn);
            spawn.performAction();
        }
    }

    public void playGame()
    {
        char nextAction = ' ';

        while(!game.isGameEnded()) {
            while (!Rules.checkValidAction(nextAction)) {
                getNextPlayersAction(); // TextView method
                nextAction = textView.getAction();
            }

            carryOutAction(textView.getFromRow, textView.getFromColumn, textView.getToRow, textView.getToColumn, nextAction);
            System.out.println(game);
        }

        System.out.println("The game has ended!");
        if(game.isAWinner())
        {
            System.out.println("The winning team is: " + game.getWinner().getTeamColor());
        }
        else
        {
            System.out.println("Nobody won!");
        }
    }

    public static void main(String args[])
    {
        Controller gameController = new Controller();
        gameController.playGame();
    }
}
