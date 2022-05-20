/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *     Creates the game controller, essentially the highest level file that will run the game.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-4
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Controller
{
    GameS22 game;
    TextView textView;

    /**
     * @return the full game setup with two teams of pieces
     */
    public GameS22 setUpGameModel(){
        // Create 4 pieces for team A
        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(new PieceMinion('M',"Blu",
                0,0,false,true));
        piecesTeamA.add(new PieceBuzz('B',"Blu",2,1,
                true,false,true));
        piecesTeamA.add(new PieceBlueHen('H',"Blu",0,
                9,false,true));
        piecesTeamA.add(new PieceEvilMinion('E',"Blu",1,
                1,4,false, true));
        piecesTeamA.add(new PieceNurseBlueHen('N',"Blu", false, true, 0));
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
        piecesTeamB.add(new PieceNurseBlueHen('N',"Red", false, true, 0));
        // Create a team object
        Team teamB = new Team("Red",piecesTeamB);
        // Create an instance of the game
        return new GameS22(8, 8,teamA, teamB);
    }

    // default constructor
    public Controller()
    {
        game = setUpGameModel();
        textView = new TextView(); // constructor for TextView
        textView.updateView(game); // TextView method
    }

    /**
     * @param fromRow the row of the piece performing the action
     * @param fromColumn the column of the piece performing the action
     * @param toRow the row of the target/selected square
     * @param toColumn the column of the target/selected square
     * @param action the action the user wishes to perform
     */
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

    private void carryOutAction(int fromRow, int fromColumn, int toRow, int toColumn, String piece, ArrayList<String> allowedRevives, ArrayList<Piece> deadPieces)
    {
        int pieceIndex = allowedRevives.indexOf(piece);
        ActionRevive revive = new ActionRevive(game, fromRow, fromColumn, toRow, toColumn, deadPieces.get(pieceIndex));
        revive.performAction();
    }

    /**
     * Starts the game. Continuously asks for user input until the game ends.
     */
    public void playGame()
    {
        char nextAction = ' ';
        String piece;

        while(!game.isGameEnded())
        {
            String pieceToRevive = "NULL";
            ArrayList<Piece> deadPieces = new ArrayList<>();
            ArrayList<String> allowedRevives = new ArrayList<>();
            boolean validAction = false;
            while (!validAction) {
                textView.getPlayersNextAction(game); // TextView method
                nextAction = textView.getActionType();
                if(nextAction == 'G')
                {
                    Scanner scan = new Scanner(System.in);

                    System.out.println("Please enter the piece you would like to revive.");
                    deadPieces = game.getCurrentTeam().getDeadPieces();
                    allowedRevives = new ArrayList<>();
                    for(int i = 0; i < deadPieces.size(); i++)
                    {
                        if(deadPieces.get(i) instanceof PieceAbominableSnowman)
                        {
                            allowedRevives.add("snowman");
                        }
                        else if(deadPieces.get(i) instanceof PieceBlueHen)
                        {
                            allowedRevives.add("bluehen");
                        }
                        else if(deadPieces.get(i) instanceof PieceBuzz)
                        {
                            allowedRevives.add("buzz");
                        }
                        else if(deadPieces.get(i) instanceof PieceEvilMinion)
                        {
                            allowedRevives.add("evilminion");
                        }
                        else if(deadPieces.get(i) instanceof PieceMinion)
                        {
                            allowedRevives.add("minion");
                        }
                        else if(deadPieces.get(i) instanceof PieceNurseBlueHen)
                        {
                            allowedRevives.add("nurse");
                        }
                    }
                    // REGION // DEBUG STRING
                    String listPieces = "";
                    for(int index = 0;index<allowedRevives.size();index++){
                        listPieces = listPieces + allowedRevives.get(index) + " ";
                    }
                    // END REGION
                    System.out.println("Your current pieces in the graveyard are: " + listPieces);
                    System.out.print(": ");

                    pieceToRevive = scan.next();

                    validAction = allowedRevives.contains(pieceToRevive);
                }
                else {
                    validAction = Rules.checkValidAction(game, textView.getRowIndexFromSquare(), textView.getColumnIndexFromSquare(), textView.getRowIndexToSquare(), textView.getColumnIndexToSquare(), nextAction);
                }
                if(!validAction)
                {
                    System.out.println("Invalid action or squares entered");
                    pieceToRevive = "NULL";
                }
            }
            nextAction = textView.getActionType();
            if(!pieceToRevive.equals("NULL"))
            {
                carryOutAction(textView.getRowIndexFromSquare(), textView.getColumnIndexFromSquare(), textView.getRowIndexToSquare(), textView.getColumnIndexToSquare(), pieceToRevive, allowedRevives, deadPieces);
            }
            else {
                carryOutAction(textView.getRowIndexFromSquare(), textView.getColumnIndexFromSquare(), textView.getRowIndexToSquare(), textView.getColumnIndexToSquare(), nextAction);
            }
            textView.updateView(game);
        }

        textView.printEndOfGameMessage(game);
    }

    public static void main(String args[])
    {
        Controller gameController = new Controller();
        gameController.playGame();
    }
}
