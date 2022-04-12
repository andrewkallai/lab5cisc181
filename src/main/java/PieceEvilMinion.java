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
        updateHungry();
    }

    public PieceEvilMinion()
    {
        this('E', "NON", 0, 0, 0, false, true);
    }

    public int getNumAttacks() {
        return numAttacks;
    }

    public boolean canAttack()
    {
        return hungry;
    }

    public void setNumAttacks(int numAttacks) {
        this.numAttacks = numAttacks;
    }

    public void updateHungry()
    {
        this.hungry = this.numAttacks < MAX_NUM_ATTACKS;
    }

    public void speak()
    {
        System.out.println("Roar!");
    }

    public boolean validMovePath()
    {
        return true;
    }

    public PieceEvilMinion spawn()
    {
        this.numTimesSpawned += 1;
        return new PieceEvilMinion(Character.toLowerCase(this.symbol),
                this.teamColor, 1, 0, 0, false, false);
    }
}
