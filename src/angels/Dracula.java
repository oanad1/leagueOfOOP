package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * Dracula- decreases modifiers and HP.
 * Singleton class implementing the AngelVisitor interface
 */
public final class Dracula implements AngelVisitor {

    private static Dracula instance = null;

    private Dracula() { }
    public static Dracula getInstance() {
        if (instance == null) {
            instance = new Dracula();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.addBonusModifier(-AngelConstants.DRACULA_MOD_P);
        pyromancer.setCurrentHP(pyromancer.getCurrentHP() - AngelConstants.DRACULA_HP_P);
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.addBonusModifier(-AngelConstants.DRACULA_MOD_R);
        rogue.setCurrentHP(rogue.getCurrentHP() - AngelConstants.DRACULA_HP_R);
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.addBonusModifier(-AngelConstants.DRACULA_MOD_W);
        wizard.setCurrentHP(wizard.getCurrentHP() - AngelConstants.DRACULA_HP_W);
    }

    @Override
    public void visit(final Knight knight) {
        knight.addBonusModifier(-AngelConstants.DRACULA_MOD_K);
        knight.setCurrentHP(knight.getCurrentHP() - AngelConstants.DRACULA_HP_K);
    }
}
