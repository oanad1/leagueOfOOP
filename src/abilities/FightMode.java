package abilities;

import input.Battlefield;
import players.Visitable;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;

/**
 * A class which implements a visitor pattern allowing players to
 * apply their abilities in a fight
 */
public final class FightMode implements PlayerVisitor {
    private static FightMode instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private FightMode() { }
    public static FightMode getInstance() {
        if (instance == null) {
            instance = new FightMode();
        }
        return instance;
    }

    /**
     * Applies rogue Backstab and Paralysis ability
     * @param rogue attacker
     */
    public void visit(final Rogue rogue) {
        Visitable opponent = battlefield.getOpponent(rogue);
        opponent.accept(Backstab.getInstance());
        opponent.accept(Paralysis.getInstance());
    }

    /**
     * Applies pyromancer Fireblast and Ignite ability
     * @param pyromancer attacker
     */
    public void visit(final Pyromancer pyromancer) {
        Visitable opponent = battlefield.getOpponent(pyromancer);
        opponent.accept(Fireblast.getInstance());
        opponent.accept(Ignite.getInstance());
    }

    /**
     * Applies wizatd Drain and Deflect ability
     * @param wizard attacker
     */
    public void visit(final Wizard wizard) {
        Visitable opponent = battlefield.getOpponent(wizard);
        opponent.accept(Drain.getInstance());
        opponent.accept(Deflect.getInstance());
    }

    /**
     * Applies knight Execute and Slam ability
     * @param knight attacker
     */
    public void visit(final Knight knight) {
        Visitable opponent = battlefield.getOpponent(knight);
        opponent.accept(Execute.getInstance());
        opponent.accept(Slam.getInstance());
    }


}
