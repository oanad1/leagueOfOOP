package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * Dark Angel- decreases HP.
 * Singleton class implementing the AngelVisitor interface
 */
public final class DarkAngel implements AngelVisitor {

    private static DarkAngel instance = null;

    private DarkAngel() { }
    public static DarkAngel getInstance() {
        if (instance == null) {
            instance = new DarkAngel();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
       pyromancer.setCurrentHP(pyromancer.getCurrentHP() - AngelConstants.DARK_HP_P);
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.setCurrentHP(rogue.getCurrentHP() - AngelConstants.DARK_HP_R);
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.setCurrentHP(wizard.getCurrentHP() - AngelConstants.DARK_HP_W);
    }

    @Override
    public void visit(final Knight knight) {
        knight.setCurrentHP(knight.getCurrentHP() - AngelConstants.DARK_HP_K);
    }
}
