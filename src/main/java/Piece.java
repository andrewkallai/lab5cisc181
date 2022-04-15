/**
 * <h1>Lab5</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *Abstract class Piece extended by PieceBlueHen, PieceMinion, and PieceBuzz.
 * </p>
 *
 * @author Andrew Kallai
 * @author Leon Giang
 * @since 2022-04-5
 *
 */

public abstract class Piece
{
    protected char symbol;
    protected String teamColor;
    protected boolean hidden ;
    protected boolean original;

    public Piece(char symbol, String teamColor, boolean hidden, boolean original){
        this.symbol = symbol;
        this.teamColor = teamColor;
        this.hidden = hidden;
        this.original = original;
    }

    // Getters for all properties
    public char getSymbol() // gets piece's symbol
    {
        return symbol;
    }
    public String getTeamColor() // gets piece's team color
    {
        return teamColor;
    }
    public boolean isHidden() // gets whether the piece is hidden or not
    {
        return hidden;
    }
    public boolean isOriginal() // gets whether the piece is original or not
    {
        return original;
    }

    // Setters for all properties
    public void setHidden(boolean hidden) // sets hidden property to argument
    {
        this.hidden = hidden;
    }
    public void setOriginal(boolean original) // sets original property to argument
    {
        this.original = original;
    }
    public void setTeamColor(String teamColor) // sets teamColor property to argument
    {
        this.teamColor = teamColor;
    }
    public void setSymbol(char symbol) // sets symbol property to argument
    {
        this.symbol = symbol;
    }

    /**abstract method speak is to be implemented in another class
     * @return void
     */
    public abstract void speak();

    /**
     * abstract method spawn is to be implemented in another class
     * @return Piece
     */
    public abstract Piece spawn();


    /**Overrides toString method to return a piece's team color and symbol.
     * @return String
     */
    @Override
    public String toString(){
        return teamColor + " " + symbol;
    }
}