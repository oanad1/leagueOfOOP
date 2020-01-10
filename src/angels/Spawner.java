package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * Spawner- brings a player back to life.
 * Singleton class implementing the AngelVisitor interface
 */
public final class Spawner implements AngelVisitor {

    private static Spawner instance = null;

    private Spawner() { }
    public static Spawner getInstance() {
        if (instance == null) {
            instance = new Spawner();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
     if (pyromancer.isDead()) {
         pyromancer.setDead(false);
         pyromancer.setCurrentHP(AngelConstants.SPAWNER_HP_P);
     }
    }

    @Override
    public void visit(final Rogue rogue) {
        if (rogue.isDead()) {
            rogue.setDead(false);
            rogue.setCurrentHP(AngelConstants.SPAWNER_HP_R);
        }
    }

    @Override
    public void visit(final Wizard wizard) {
        if (wizard.isDead()) {
            wizard.setDead(false);
            wizard.setCurrentHP(AngelConstants.SPAWNER_HP_W);
        }
    }

    @Override
    public void visit(final Knight knight) {
        if (knight.isDead()) {
            knight.setDead(false);
            knight.setCurrentHP(AngelConstants.SPAWNER_HP_K);
        }
    }
}
