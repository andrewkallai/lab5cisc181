import java.util.ArrayList;

public class Rules
{
    public static boolean checkValidAction(GameS22 game, int fromRow, int fromColumn, int toRow, int toColumn, char action)
    {
        // Board setup
        BoardSquare[][] squares = game.board.getSquares();
        GameBoard board = game.board;

        // Teams playing
        Team currentTeam = game.getCurrentTeam();
        Team enemyTeam = game.getOpponentTeam();

        // Squares selected
        BoardSquare fromSquare;
        BoardSquare toSquare;
        try {
            fromSquare = squares[fromRow][fromColumn];
            toSquare = squares[toRow][toColumn];
        }
        catch(Exception IndexOutOfBoundsException)
        {
            return false;
        }

        // bool variable declarations
        boolean inBounds = false;
        boolean isFromSquareEmpty = false;
        boolean isToSquareEmpty = false;
        boolean isPieceOnCurrentTeam = false;
        boolean isValidMove = false;

        // Conditions that MUST be true before checking anything else
        inBounds = board.inBounds(fromRow, fromColumn) && board.inBounds(toRow, toColumn);
        // Checking fromSquare
        isFromSquareEmpty = fromSquare.isEmpty();
        if(!isFromSquareEmpty)
        {
            isPieceOnCurrentTeam = fromSquare.getPiece().getTeamColor().equals(currentTeam.getTeamColor());
        }

        // Conditions that may NOT be required all the time
        isToSquareEmpty = squares[toRow][toColumn].isEmpty();

        // If all conditions check pass, check the action pass and go from there
        if(inBounds && !isFromSquareEmpty && isPieceOnCurrentTeam)
        {
            if(action == 'M')
            {
                isValidMove = isToSquareEmpty;
            }
            else if(action == 'S')
            {
                if(!(fromSquare.getPiece() instanceof PieceBuzz)) // only runs if Piece on fromSquare is NOT a PieceBuzz
                {
                    isValidMove = isToSquareEmpty;
                }
            }
            else if(action == 'R')
            {
                if(!(fromSquare.getPiece() instanceof PieceBuzz) && !isToSquareEmpty) // only runs if Piece on fromSquare is NOT a PieceBuzz
                {
                    isValidMove = enemyTeam.getTeamColor().equals(toSquare.getPiece().getTeamColor()); // valid if piece on toSquare is the enemy team
                }
            }
            else if(action == 'A')
            {
                // only runs if Piece on fromSquare is NOT a PieceMinion AND the piece on the toSquare is on the enemy team
                if(!(fromSquare.getPiece() instanceof PieceMinion))
                {
                    boolean toSquarePieceIsEnemy = enemyTeam.getTeamColor().equals(toSquare.getPiece().getTeamColor());
                    if((fromSquare.getPiece() instanceof PieceBuzz) && toSquarePieceIsEnemy) // checks conditions if Piece is a PieceBuzz
                    {
                        isValidMove = ((PieceBuzz) fromSquare.getPiece()).canAttack(); // Buzz can only attack when his laser is working
                    }
                    else if(fromSquare.getPiece() instanceof PieceEvilMinion)
                    {
                        isValidMove = ((PieceEvilMinion) fromSquare.getPiece()).canAttack(); // can attack any piece in hungry
                    }
                    else if((fromSquare.getPiece() instanceof PieceBlueHen))
                    {
                        isValidMove = toSquarePieceIsEnemy; // true if toSquare piece is enemy
                    }
                    else
                    {
                        System.out.println("Something bad happened");
                    }
                }
            }
        }

        return isValidMove;
    }

    public static void main(String[] arg){
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

        // create a game
        GameS22 game = new GameS22(6,6,teamA,teamB);

        // clear Piece off the board
        for(int row = 0; row < game.getGameBoard().getNumRows();row++){
            for (int col = 0; col < game.getGameBoard().getNumColumns(); col++){
                game.getGameBoard().getSquares()[row][col].removePiece();
            }
        }
        System.out.println(game);

        // load your pieces in specific locations of your choice
        game.getGameBoard().getSquares()[0][0].setPiece(piecesTeamA.get(0));
        game.getGameBoard().getSquares()[0][1].setPiece(piecesTeamA.get(1));
        game.getGameBoard().getSquares()[0][2].setPiece(piecesTeamA.get(2));
        game.getGameBoard().getSquares()[0][3].setPiece(piecesTeamA.get(3));
        game.getGameBoard().getSquares()[2][0].setPiece(piecesTeamB.get(0));
        game.getGameBoard().getSquares()[2][1].setPiece(piecesTeamB.get(1));
        game.getGameBoard().getSquares()[2][2].setPiece(piecesTeamB.get(2));
        game.getGameBoard().getSquares()[2][3].setPiece(piecesTeamB.get(3));

        System.out.println(game);

        // Test some moves that should be valid
        // Test some moves that are invalid

        // BLUE TEAM TURN

        System.out.println("testing blue team's MOVE");

        // This should be a valid move
        System.out.println(Rules.checkValidAction(game,
                0,0,
                1,4,'M'));

        // To Square isn't empty - should not be a valid move
        System.out.println(Rules.checkValidAction(game,
                0,0,
                0,1,'M'));
        // This isn't current team's piece - should not be valid
        System.out.println(Rules.checkValidAction(game,
                2,0,
                0,5,'M'));

        System.out.println("testing blue team's SPAWN");

        // This is a PieceBuzz - should not be a valid move
        System.out.println(Rules.checkValidAction(game,
                0,1,
                0,5,'S'));

        // toSquare is not empty - should not be a valid move
        System.out.println(Rules.checkValidAction(game,
                0,0,
                0,3,'S'));

        // fromSquare is not on the same team - should not be a valid move
        System.out.println(Rules.checkValidAction(game,
                2,0,
                0,5,'S'));

        // Should be able to spawn - should be valid move
        System.out.println(Rules.checkValidAction(game,
                0,0,
                0,5,'S'));

        System.out.println("Testing blue team's RECRUIT");


        // You can change the turn to test the other team pieces
        game.changeTurn();

        System.out.println(game);

        // RED TEAM TURN

        // This should be a valid move
        System.out.println(Rules.checkValidAction(game,
                2,0,
                0,5,'M'));

        // To Square isn't empty - should not be a valid move
        System.out.println(Rules.checkValidAction(game,
                0,0,
                0,1,'M'));
        // Wrong team - should not be a valid move
        System.out.println(Rules.checkValidAction(game,
                0,0,
                1,4,'M'));

    }
}
