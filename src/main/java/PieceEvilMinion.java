public class PieceEvilMinion extends PieceMinion
{
    private int numAttacks;
    private boolean hungry;

    public static int MAX_NUM_ATTACKS = 4;

    public PieceEvilMinion(char symbol, String teamColor, int numRecuits, int numAttacks,
                           int numTimesSpawned, boolean hidden, boolean original)
    {
        super(symbol, teamColor, numRecuits, numTimesSpawned, hidden, original);
        this.numAttacks = numAttacks;
    }

    public PieceEvilMinion()
    {
        this('E', "NON", 0, 0, 0, false, true);
    }
}
