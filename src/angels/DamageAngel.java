package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * Damage Angel- increases modifiers.
 * Singleton class implementing the AngelVisitor interface
 */
public final class DamageAngel implements AngelVisitor {

    private static DamageAngel instance = null;

    private DamageAngel() { }
    public static DamageAngel getInstance() {
        if (instance == null) {
            instance = new DamageAngel();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.addBonusModifier(AngelConstants.DA_MOD_P);
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.addBonusModifier(AngelConstants.DA_MOD_R);
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.addBonusModifier(AngelConstants.DA_MOD_W);
    }

    @Override
    public void visit(final Knight knight) {
        knight.addBonusModifier(AngelConstants.DA_MOD_K);
    }
}
