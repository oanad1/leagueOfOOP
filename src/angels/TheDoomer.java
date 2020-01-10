package angels;

import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Knight;
import players.Wizard;

/**
 * The Doomer - kills a player.
 * Singleton class implementing the AngelVisitor interface
 */
public final class TheDoomer implements AngelVisitor {

    private static TheDoomer instance = null;

    private TheDoomer() { }
    public static TheDoomer getInstance() {
        if (instance == null) {
            instance = new TheDoomer();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
       kill(pyromancer);
    }

    @Override
    public void visit(final Rogue rogue) {
        kill(rogue);
    }

    @Override
    public void visit(final Wizard wizard) {
        kill(wizard);
    }

    @Override
    public void visit(final Knight knight) {
        kill(knight);
    }

    public void kill(final Player player) {
        if (!player.isDead()) {
            player.setDead(true);
        }
    }
}
