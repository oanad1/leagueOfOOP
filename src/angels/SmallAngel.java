package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * Small Angel- increases modifiers and HP.
 * Singleton class implementing the AngelVisitor interface
 */
public final class SmallAngel implements AngelVisitor {
    private static SmallAngel instance = null;

    private SmallAngel() { }
    public static SmallAngel getInstance() {
        if (instance == null) {
            instance = new SmallAngel();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.addBonusModifier(AngelConstants.SA_MOD_P);
        pyromancer.setCurrentHP(pyromancer.getCurrentHP() + AngelConstants.SA_HP_P);
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.addBonusModifier(AngelConstants.SA_MOD_R);
        rogue.setCurrentHP(rogue.getCurrentHP() + AngelConstants.SA_HP_R);
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.addBonusModifier(AngelConstants.SA_MOD_W);
        wizard.setCurrentHP(wizard.getCurrentHP() + AngelConstants.SA_HP_W);
    }

    @Override
    public void visit(final Knight knight) {
        knight.addBonusModifier(AngelConstants.SA_MOD_K);
        knight.setCurrentHP(knight.getCurrentHP() + AngelConstants.SA_HP_K);
    }
}
