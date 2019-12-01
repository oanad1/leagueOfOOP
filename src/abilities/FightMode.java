package abilities;

import input.Battlefield;
import players.Visitable;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;

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

    public void visit(final Rogue rogue) {  //ordinea conteaza
        Visitable opponent = battlefield.getOpponent(rogue);
        opponent.accept(Backstab.getInstance());
        opponent.accept(Paralysis.getInstance());
    }

    public void visit(final Pyromancer pyromancer) {
        Visitable opponent = battlefield.getOpponent(pyromancer);
        opponent.accept(Fireblast.getInstance());
        opponent.accept(Ignite.getInstance());
    }

    public void visit(final Wizard wizard) {
        Visitable opponent = battlefield.getOpponent(wizard);
        opponent.accept(Drain.getInstance());
        opponent.accept(Deflect.getInstance());
    }

    public void visit(final Knight knight) {
        Visitable opponent = battlefield.getOpponent(knight);
        opponent.accept(Execute.getInstance());
        opponent.accept(Slam.getInstance());
    }


}
