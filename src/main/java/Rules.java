/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *     Establishes whether a move is valid based on the action, the squares selected, and the rules of the game
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-4
 */

import java.util.ArrayList;

public class Rules
{

    private static double distanceBetweenPoints(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x2 - x1, 2.0) + Math.pow(y2 - y1, 2.0));
    }

    public static int getCornerIndex(double[][] nearestCorners)
    {
        int index = -1;
        double min = 999;
        for(int i = 0; i < nearestCorners.length; i++)
        {
            if(nearestCorners[i][0] != -1.0 && nearestCorners[i][1] < min)
            {
                min = nearestCorners[i][1];
                index = i;
            }
        }
        if(index != -1) {
            nearestCorners[index][0] = -1;
        }

        return (int)index;
    }

    /**
     * @param game The game being played
     * @param fromRow the row of the piece performing the action
     * @param fromColumn the column of the piece performing the action
     * @param toRow the row of the target/selected square
     * @param toColumn the column of the target/selected square
     * @param action the action the user wishes to perform
     * @return whether the action is a valid action
     */
    public static boolean checkValidAction(GameS22 game, int fromRow, int fromColumn, int toRow, int toColumn, char action)
    {
        // checks if a piece is trying to attack itself, move to itself, recruit itself, or spawn itself.
        if(fromRow == toRow && fromColumn == toColumn)
        {
            return false;
        }

        // Board setup
        BoardSquare[][] squares = game.board.getSquares();
        GameBoard board = game.board;

        // Teams playing
        Team currentTeam = game.getCurrentTeam();
        Team enemyTeam = game.getOpponentTeam();

        // Squares selected
        BoardSquare fromSquare;
        BoardSquare toSquare;

        // reference for max number of rows and columns
        int numRows = game.getGameBoard().getNumRows();
        int numColumns = game.getGameBoard().getNumColumns();

        // Trys to access the fromSquare and toSquare. If it fails due to IndexOutOfBounds, return false since
        // at least one of the points is out of bounds
        try {
            fromSquare = squares[fromRow][fromColumn];
            toSquare = squares[toRow][toColumn];
        }
        catch(Exception IndexOutOfBoundsException)
        {
            System.out.println("Index entered is out of bounds");
            return false;
        }

        // bool variable declarations
        boolean isFromSquareEmpty = false;
        boolean isToSquareEmpty = false;
//        boolean isToSquareBlackHole = false;
        boolean isPieceOnCurrentTeam = false;
        boolean isValidMove = false;

        // Conditions that MUST be true before checking anything else
        // Checking fromSquare
        isFromSquareEmpty = fromSquare.isEmpty();
        if(!isFromSquareEmpty)
        {
            isPieceOnCurrentTeam = fromSquare.getPiece().getTeamColor().equals(currentTeam.getTeamColor());
        }

        // Conditions that may NOT be required all the time
        isToSquareEmpty = squares[toRow][toColumn].isEmpty();

        // NEW square behavior
//        isToSquareBlackHole = toSquare.isBlackHole();

        // If all conditions check pass, check the action pass and go from there
        if(!isFromSquareEmpty && isPieceOnCurrentTeam)
        {
            /* finds what type the current Piece is */
            Piece fromPiece = fromSquare.getPiece();
            String currentPiece = "";
            if(fromPiece instanceof PieceBuzz)
            {
                currentPiece = "PieceBuzz";
            }
            else if(fromPiece instanceof PieceNurseBlueHen)//New Extended Piece Modification
            {
                currentPiece = "PieceNurseBlueHen";
            }
            else if(fromPiece instanceof PieceBlueHen)
            {
                currentPiece = "PieceBlueHen";
            }
            else if(fromPiece instanceof PieceEvilMinion)
            {
                currentPiece = "PieceEvilMinion";
            }
            else if(fromPiece instanceof PieceMinion)
            {
                currentPiece = "PieceMinion";
            }
            else if(fromPiece instanceof PieceAbominableSnowman) //New Piece Modification
            {
                currentPiece = "PieceAbominableSnowman";
            }

            //if(action == 'M' && currentPiece.equals(""))
            if(action == 'M')
            {
                isValidMove = isToSquareEmpty && switch (currentPiece) {
                    case "PieceBuzz" -> ((PieceBuzz) fromPiece).validMovePath(fromRow, fromColumn, toRow, toColumn);
                    case "PieceBlueHen" ->
                            ((PieceBlueHen) fromPiece).validMovePath(fromRow, fromColumn, toRow, toColumn);
                    case "PieceEvilMinion" ->
                            ((PieceEvilMinion) fromPiece).validMovePath(fromRow, fromColumn, toRow, toColumn);
                    case "PieceMinion" -> ((PieceMinion) fromPiece).validMovePath(fromRow, fromColumn, toRow, toColumn);
                    case "PieceAbominableSnowman" ->  //New Piece Modification
                            ((PieceAbominableSnowman) fromPiece).validMovePath(fromRow, fromColumn, toRow, toColumn);
                    case "PieceNurseBlueHen" ->  //New Extended Piece Modification
                            ((PieceNurseBlueHen) fromPiece).validMovePath(fromRow, fromColumn, toRow, toColumn);
                    default -> false;
                };
            }
            else if(action == 'S' && !currentPiece.equals("PieceBuzz")) // PieceBuzz cannot spawn
            {
                if(currentPiece.equals("PieceMinion"))
                {
                    // create a 2d array holding the coordinates for the corners of the board
                    int[][] corners = {{0, 0}, {0, numColumns-1}, {numRows-1, 0}, {numRows-1, numColumns-1}};

                    // create an array that holds the distance between the fromSquare and the corners
                    double[] distances = new double[4];
                    for(int i = 0; i < 4; i++)
                    {
                        distances[i] = distanceBetweenPoints(fromRow,fromColumn, corners[i][0], corners[i][1]);
                    }

                    // Creates another 2d array that will hold the distances as well as the index of the corner coordinates that produced that result
                    double[][] nearestCorners = new double[4][2];
                    for(int i = 0; i < distances.length; i++)
                    {
                        nearestCorners[i][0] = i;
                        nearestCorners[i][1] = distances[i];
                    }

                    // Looks for the index of the nearest empty corner. If there are no empty corners, returns -1.
                    int closestEmptyCornerIndex = -1;
                    int check = -1;
                    while(check == -1)
                    {
                        closestEmptyCornerIndex = getCornerIndex(nearestCorners);
                        if(closestEmptyCornerIndex == -1)
                        {
                            check = 0;
                        }
                        else {
                            check = squares[corners[closestEmptyCornerIndex][0]][corners[closestEmptyCornerIndex][1]].isEmpty() ? 0 : -1;
                        }
                    }

                    //System.out.println("DEBUG:\nIndex of closest empty corner: " + closestEmptyCornerIndex);

                    // If the returned index of the nearest corner is -1, means there are no empty corners and PieceMinion cannot spawn
                    // else, checks if the toSquare coordinates match that of the nearest empty corner.
                    if(closestEmptyCornerIndex == -1)
                    {
                        isValidMove = false;
                    }
                    else {
                        isValidMove = toRow == corners[closestEmptyCornerIndex][0] && toColumn == corners[closestEmptyCornerIndex][1];
                    }
                }
                //fix for lab 7
                else if(currentPiece.equals("PieceEvilMinion"))
                {
                    isValidMove = ((PieceEvilMinion) fromPiece).canSpawn();
                }
                else
                {
                    isValidMove = isToSquareEmpty;
                }
            }
            else if(action == 'R' && (!currentPiece.equals("PieceBuzz") || !currentPiece.equals("PieceNurseBlueHen"))) // PieceBuzz and PieceNusreBlueHen cannot recruit //New Extended Piece Modification
            {
                isValidMove = !isToSquareEmpty && enemyTeam.getTeamColor().equals(toSquare.getPiece().getTeamColor()) && switch (currentPiece) {
                    case "PieceBlueHen" ->
                            ((PieceBlueHen) fromPiece).validRecruitPath(fromRow, fromColumn, toRow, toColumn);
                    case "PieceEvilMinion" ->
                            ((PieceEvilMinion) fromPiece).validRecruitPath(fromRow, fromColumn, toRow, toColumn);
                    case "PieceMinion" -> ((PieceMinion) fromPiece).validRecruitPath(fromRow, fromColumn, toRow, toColumn);
                    case "PieceAbominableSnowman" -> //New Piece Modification
                            ((PieceAbominableSnowman) fromPiece).validRecruitPath(fromRow, fromColumn, toRow, toColumn);
                    default -> false;
                };
            }
            else if(action == 'A' && !currentPiece.equals("PieceMinion") && !isToSquareEmpty)
            {
                boolean toSquarePieceIsEnemy = enemyTeam.getTeamColor().equals(toSquare.getPiece().getTeamColor());
                if(currentPiece.equals("PieceBuzz") && toSquarePieceIsEnemy) // checks conditions if Piece is a PieceBuzz
                {
                    // valid if PieceBuzz's laser is working (canAttack) AND the movePath is valid AND the target is an enemy
                    isValidMove = ((PieceBuzz) fromPiece).canAttack() && ((PieceBuzz) fromPiece).validAttackPath(fromRow, fromColumn, toRow, toColumn); // Buzz can only attack when his laser is working
                }
                else if(currentPiece.equals("PieceEvilMinion"))
                {
                    if(toSquarePieceIsEnemy) {
                        // valid on any Piece as long as PieceEvilMinion is hungry (canAttack) AND the movePath is valid
                        isValidMove = ((PieceEvilMinion) fromPiece).canAttack() && ((PieceEvilMinion) fromPiece).validAttackPath(fromRow, fromColumn, toRow, toColumn); // can attack any piece in hungry
                    }
                    else
                    {
                        isValidMove = ((PieceEvilMinion) fromPiece).canAttack() && ((PieceEvilMinion) fromPiece).validAttackPath(fromRow, fromColumn, toRow, toColumn) && (toSquare.getPiece() instanceof PieceMinion);
                    }
                }
                else if(currentPiece.equals("PieceBlueHen") && toSquarePieceIsEnemy)
                {
                    // valid if target is an enemy AND the movePath is valid
                    isValidMove = ((PieceBlueHen) fromPiece).validAttackPath(fromRow, fromColumn, toRow, toColumn);
                }
                else if(currentPiece.equals("PieceAbominableSnowman") && toSquarePieceIsEnemy) //New Piece Modification
                {
                    // valid if target is an enemy AND the movePath is valid
                    isValidMove = ((PieceAbominableSnowman) fromPiece).validAttackPath(fromRow, fromColumn, toRow, toColumn);
                }
                else if(currentPiece.equals("PieceNurseBlueHen") && toSquarePieceIsEnemy) //New Extended Piece Modification
                {
                    // valid if target is an enemy AND the movePath is valid
                    isValidMove = ((PieceNurseBlueHen) fromPiece).validAttackPath(fromRow, fromColumn, toRow, toColumn);
                }
            }
            else if(action == 'G' && currentPiece.equals("PieceNurseBlueHen")) //New Extended Piece Modification
            {
                isValidMove = toSquare.isEmpty();
            }
        }

        return isValidMove;
    }

    // Tests for checkValidAction
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
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,0,
                1,4,'M'));
        // This should be a valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,1,
                1,4,'M'));
        // This should be a valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,2,
                1,4,'M'));
        // This should be a valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,3,
                1,4,'M'));

        // To Square isn't empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                0,1,'M'));
        // fromSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,4,
                0,1,'M'));
        // This isn't current team's piece - should not be valid
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,0,
                0,5,'M'));
        // fromSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                10,10,
                0,1,'M'));
        // toSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                6,1,'M'));

        System.out.println("testing blue team's SPAWN");

        // This is a PieceBuzz - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,1,
                0,5,'S'));
        // toSquare is not empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                0,3,'S'));
        // fromSquare is not on the same team - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,0,
                0,5,'S'));
        // fromSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,4,
                0,5,'S'));
        // fromSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                10,10,
                0,1,'S'));
        // toSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                6,1,'S'));

        // Should be able to spawn - should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,0,
                0,5,'S'));
        // Should be able to spawn - should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,2,
                0,5,'S'));
        // Should be able to spawn - should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,3,
                0,5,'S'));

        System.out.println("Testing blue team's RECRUIT");

        // fromSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,4,
                2,0,'R'));
        // toSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                0,5,'R'));
        // toSquare is on the same team - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                0,1,'R'));
        // Piece is Buzz - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,1,
                2,0,'R'));
        // fromSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                10,10,
                0,1,'R'));
        // toSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                6,1,'R'));

        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,0,
                2,0,'R'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,2,
                2,1,'R'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,3,
                2,2,'R'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,3,
                2,3,'R'));

        System.out.println("Testing blue team's ATTACK");

        // fromSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                10,10,
                0,1,'A'));
        // toSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                6,1,'A'));
        // fromSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,4,
                2,0,'A'));
        // toSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,1,
                0,5,'A'));
        // toSquare is on the same team and Piece is NOT an EvilMinion - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,1,
                0,1,'A'));
        // Piece is Minion - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                2,0,'A'));

        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,1,
                2,0,'A'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,2,
                2,1,'A'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,3,
                2,2,'A'));
        // This should be valid move. Piece is EvilMinion and EvilMinion is hungry
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                0,3,
                0,2,'A'));

        ((PieceEvilMinion) piecesTeamA.get(3)).setNumAttacks(50);
        ((PieceEvilMinion) piecesTeamA.get(3)).updateHungry();
        // Piece is EvilMinion, but EvilMinion is NOT hungry - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,3,
                0,0,'A'));
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,3,
                2,0,'A'));

        ((PieceBuzz) piecesTeamA.get(1)).setWorkingLaser(false);
        // Piece is Buzz, but Buzz's laser is disabled - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,1,
                2,0,'A'));
        // Piece is Buzz, but Buzz's laser is disabled AND targeting own team - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,1,
                0,0,'A'));

        // You can change the turn to test the other team pieces
        game.changeTurn();

        System.out.println(game);

        // RED TEAM TURN

        System.out.println("Testing red team's MOVE");

        // This should be a valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,0,
                1,4,'M'));
        // This should be a valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,1,
                1,5,'M'));
        // This should be a valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,2,
                2,5,'M'));
        // This should be a valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,3,
                5,5,'M'));

        // To Square isn't empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,0,
                0,1,'M'));
        // fromSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,4,
                1,1,'M'));
        // This isn't current team's piece - should not be valid
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                0,5,'M'));
        // fromSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                10,10,
                0,1,'M'));
        // toSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                6,1,'M'));

        System.out.println("testing red team's SPAWN");

        // This is a PieceBuzz - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,2,
                0,5,'S'));
        // toSquare is not empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,0,
                0,3,'S'));
        // fromSquare is not on the same team - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                0,5,'S'));
        // fromSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,4,
                0,5,'S'));
        // fromSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                10,10,
                0,1,'S'));
        // toSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                6,1,'S'));

        // Should be able to spawn - should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,0,
                0,5,'S'));
        // Should be able to spawn - should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,1,
                0,5,'S'));
        // Should be able to spawn - should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,3,
                0,5,'S'));

        System.out.println("Testing red team's RECRUIT");

        // fromSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,4,
                0,0,'R'));
        // toSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,0,
                0,5,'R'));
        // toSquare is on the same team - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,1,
                2,0,'R'));
        // Piece is Buzz - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,2,
                0,0,'R'));
        // fromSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                10,10,
                0,1,'R'));
        // toSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                6,1,'R'));

        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,0,
                0,0,'R'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,1,
                0,1,'R'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,3,
                0,2,'R'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,3,
                0,3,'R'));

        System.out.println("Testing red team's ATTACK");

        // fromSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                10,10,
                0,1,'A'));
        // toSquare index out of bounds - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,0,
                6,1,'A'));
        // fromSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                0,4,
                0,0,'A'));
        // toSquare is empty - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,1,
                0,5,'A'));
        // toSquare is on the same team and Piece is NOT an EvilMinion - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,1,
                2,0,'A'));
        // Piece is Minion - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,0,
                0,2,'A'));

        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,1,
                0,0,'A'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,2,
                0,1,'A'));
        // This should be valid move
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,3,
                0,2,'A'));
        // This should be valid move. Piece is EvilMinion and EvilMinion is hungry
        System.out.println("expected: TRUE, GOT: " + Rules.checkValidAction(game,
                2,3,
                0,3,'A'));

        ((PieceEvilMinion) piecesTeamB.get(3)).setNumAttacks(50);
        ((PieceEvilMinion) piecesTeamB.get(3)).updateHungry();
        // Piece is EvilMinion, but EvilMinion is NOT hungry - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,3,
                2,0,'A'));
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,3,
                0,0,'A'));

        ((PieceBuzz) piecesTeamB.get(2)).setWorkingLaser(false);
        // Piece is Buzz, but Buzz's laser is disabled - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,2,
                0,2,'A'));
        // Piece is Buzz, but Buzz's laser is disabled AND targeting own team - should not be a valid move
        System.out.println("expected: FALSE, GOT: " + Rules.checkValidAction(game,
                2,2,
                2,0,'A'));

    }
}
