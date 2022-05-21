import java.util.Scanner;

/**
 * <h1>Lab7</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *  Provides a text based interface to the players so that they can play the game.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-05-5
 */
public class TextView {
    private int rowIndexFromSquare;
    private int columnIndexFromSquare;
    private int rowIndexToSquare;
    private int columnIndexToSquare;
    private char actionType;

    public int getRowIndexFromSquare(){
        return rowIndexFromSquare;
    }
    public int getColumnIndexFromSquare(){
        return columnIndexFromSquare;
    }
    public int getRowIndexToSquare(){
        return rowIndexToSquare;
    }
    public int getColumnIndexToSquare(){
        return columnIndexToSquare;
    }
    public char getActionType(){
        return actionType;
    }

    /**
     *Repeatedly asks the user to enter one the following character
     * values:
     *  A (representing attack)
     *  M (representing move)
     *  R (representing recruit)
     *  S (representing spawn)
     * The first character from this list entered by the user should be returned by this method.
     * @param scr: Scanner
     * @return char
     */
    public static char getUsersNextActionType(Scanner scr) {
        char returnChar = ' ';
        boolean validValue = false;
        while (!validValue) {
            System.out.println("Please enter one of the following:");
            System.out.println("A (attack)");
            System.out.println("M (move)");
            System.out.println("R (recruit)");
            System.out.println("S (spawn)");
            System.out.println("G (revive)");
            System.out.print(": ");
            if (scr.hasNext()) {
                String usrInput = scr.next();
                if ((usrInput.charAt(0) == 'A') || (usrInput.charAt(0) == 'a')) {
                    returnChar = usrInput.charAt(0);
                    validValue = true;
                } else if ((usrInput.charAt(0) == 'M') || (usrInput.charAt(0) == 'm')) {
                    returnChar = usrInput.charAt(0);
                    validValue = true;
                } else if ((usrInput.charAt(0) == 'R') || (usrInput.charAt(0) == 'r')) {
                    returnChar = usrInput.charAt(0);
                    validValue = true;
                } else if ((usrInput.charAt(0) == 'S') || (usrInput.charAt(0) == 's')) {
                    returnChar = usrInput.charAt(0);
                    validValue = true;
                } else if ((usrInput.charAt(0) == 'G') || (usrInput.charAt(0) == 'g')) {
                    returnChar = usrInput.charAt(0);
                    validValue = true;
                } else {
                    System.out.println("Invalid input.");
                }
            }
        }

        return returnChar;
    }

    /**
     * Takes user input and returns the user input if it is an integer in the
     * range of minNum to maxNum (inclusive). Else loops indefinitely until user
     * enters a valid value.
     * @param minNum: int
     * @param maxNum: int
     * @param scr: int
     * @return
     */
    public static int getValidInt(int minNum, int maxNum, Scanner scr) {
        boolean validValue = false;
        int intValue;
        while (!validValue) {
            System.out.println("Your integer must be between " + minNum +" and " + maxNum +" (inclusive).");
            System.out.print(": ");
            if (scr.hasNextInt()) {
                intValue = scr.nextInt();
                if (minNum <= intValue && intValue <= maxNum) {
                    validValue = true;
                    return intValue;
                } else {
                    System.out.println("Your integer is not in the valid range.");
                }
            } else {
                scr.next();
                System.out.println("Your value is not an integer");
            }
        }
        return -1;
    }

    /**
     * calls getUsersNextActionType to get the type of action the player wants to take (M, R, A, or S).
     * and assign it to the action type property.
     * Asks the player to enter the row and column indexes of the from square they are selecting for their action.
     * Assigns the values to from Row and from Col properties.
     * Asks the player to enter the row and column indexes of the to square they are selecting for their action  .
     * Assigns the values to To Row and To Col properties.
     * @param game: GameS22
     */
    public void getPlayersNextAction(GameS22 game){
        Scanner scnr = new Scanner(System.in);
        actionType = getUsersNextActionType(scnr);

        System.out.println("Where is the piece that you want to perform the action?");
        System.out.println("Enter row index");
        rowIndexFromSquare = getValidInt(0, game.getGameBoard().getNumRows(), scnr);
        System.out.println("Enter column index");
        columnIndexFromSquare = getValidInt(0, game.getGameBoard().getNumColumns(), scnr);

        System.out.println("Where is your target square?");
        System.out.println("Enter row index");
        rowIndexToSquare = getValidInt(0, game.getGameBoard().getNumRows(), scnr);
        System.out.println("Enter column index");
        columnIndexToSquare = getValidInt(0, game.getGameBoard().getNumColumns(), scnr);
    }

    /**
     *Prints the game object.
     * @param game: Game
     */
    public void updateView(Game game){
        System.out.println(game);
    }

    /**
     * Displays a message saying the game has ended and which team won.
     * @param game: Game
     */
    public void printEndOfGameMessage(Game game){
        System.out.println("The game has ended!");
        if(game.isAWinner())
        {
            // New Objective Modification // Displays win message according to winType.
            if(((GameS22) game).getWinType().equals("elimination"))
            {
                System.out.println("Winner by elimination! You have defeated your foe in glorious combat!");
            }
            else if(((GameS22) game).getWinType().equals("pacifist"))
            {
                System.out.println("The pen is mightier than the sword! You have won without any combat! Gold star!");
            }

            System.out.println("The winning team is: " + game.getWinner().getTeamColor());
        }
        else
        {
            System.out.println("Nobody won!");
        }
    }

}
