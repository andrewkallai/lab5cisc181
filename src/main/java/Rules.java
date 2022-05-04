import java.util.ArrayList;

public class Rules
{
    public static boolean checkValidAction(GameS22 game, int fromRow, int fromColumn, int toRow, int toColumn, char action)
    {
        // bool variable declarations
        boolean inBounds = false;
        boolean isFromSquareEmpty = false;
        boolean isToSquareEmpty = false;
        boolean isPieceOnCurrentTeam = false;
        boolean validMove = false;

        // default checks for booleans that must be true for a validMove regardless of the move
        inBounds = game.board.inBounds(fromRow, fromColumn) && game.board.inBounds(toRow, toColumn);
        isFromSquareEmpty = game.getBoardSquares()[fromRow][fromColumn].isEmpty();
        if(!isFromSquareEmpty)
        {
            isPieceOnCurrentTeam = game.getCurrentTeam().getTeamColor().equals(game.getBoardSquares()[fromRow][fromColumn].getPiece().getTeamColor());
        }

        // convenience check for a piece on the toSquare
        isToSquareEmpty = game.getBoardSquares()[fromRow][fromColumn].isEmpty();

        if(inBounds && !isFromSquareEmpty && isPieceOnCurrentTeam)
        {
            if(action == 'M')
            {
                validMove = isToSquareEmpty;
            }
            else if(action == 'S')
            {
                if(!(game.getBoardSquares()[fromRow][fromColumn].getPiece() instanceof PieceBuzz)) // only runs if Piece on fromSquare is NOT a PieceBuzz
                {
                    validMove = isToSquareEmpty;
                }
            }
            else if(action == 'R')
            {
                if(!(game.getBoardSquares()[fromRow][fromColumn].getPiece() instanceof PieceBuzz)) // only runs if Piece on fromSquare is NOT a PieceBuzz
                {
                    if(!isToSquareEmpty)
                    {
                        validMove = game.getOpponentTeam().getTeamColor().equals(game.getBoardSquares()[toRow][toColumn].getPiece().getTeamColor()); // valid if piece on toSquare is the enemy team
                    }
                }
            }
            else if(action == 'A')
            {
                // only runs if Piece on fromSquare is NOT a PieceMinion AND the piece on the toSquare is on the enemy team
                if(!(game.getBoardSquares()[fromRow][fromColumn].getPiece() instanceof PieceMinion) && game.getOpponentTeam().getTeamColor().equals(game.getBoardSquares()[toRow][toColumn].getPiece().getTeamColor()))
                {
                    if(game.getBoardSquares()[fromRow][fromColumn].getPiece() instanceof PieceBuzz) // checks conditions if Piece is a PieceBuzz
                    {
                        validMove = ((PieceBuzz) game.getBoardSquares()[fromRow][fromColumn].getPiece()).canAttack();
                    }
                    else if(game.getBoardSquares()[fromRow][fromColumn].getPiece() instanceof PieceEvilMinion)
                    {
                        validMove = ((PieceEvilMinion) game.getBoardSquares()[fromRow][fromColumn].getPiece()).canAttack();
                    }
                    else
                    {
                        validMove = true; // for PieceBlueHen
                    }
                }
            }
        }

        return validMove;
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


        // You can change the turn to test the other team pieces
        game.changeTurn();

        System.out.println(Rules.checkValidAction(game,
                2,0,
                0,5,'M'));


    }
}
