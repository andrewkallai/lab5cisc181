/**
 * <h1>Lab3</h1>
 * <h2>CISC181 181-080L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 *
 * </p>
 *
 * @author Andrew Kallai
 * @since 2022-03-2
 * check that return index of random thing is not always zero (could be an issue with parenthesis)
 * consolidate multiple method calls into one large method call
 */

public abstract class Piece {
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
    public char getSymbol() {
        return symbol;
    }
    public String getTeamColor() {
        return teamColor;
    }
    public boolean isHidden() {
        return hidden;
    }
    public boolean isOriginal() {
        return original;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    public void setOriginal(boolean original) {
        this.original = original;
    }
    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }
    public void setSymbol(char symbol)
    {
        this.symbol = symbol;
    }

    public abstract void speak();

    public abstract Piece spawn();

    @Override
    public String toString(){
        return teamColor + " " + symbol;
    }
}

// 4/11 changes: removed setSymbol and setTeamColor and implemented in Piece class.