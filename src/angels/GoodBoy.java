package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * GoodBoy- increases modifiers and HP.
 * Singleton class implementing the AngelVisitor interface
 */
public final class GoodBoy implements AngelVisitor {

    private static GoodBoy instance = null;

    private GoodBoy() { }
    public static GoodBoy getInstance() {
        if (instance == null) {
            instance = new GoodBoy();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.addBonusModifier(AngelConstants.GB_MOD_P);
        pyromancer.setCurrentHP(pyromancer.getCurrentHP() + AngelConstants.GB_HP_P);
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.addBonusModifier(AngelConstants.GB_MOD_R);
        rogue.setCurrentHP(rogue.getCurrentHP() + AngelConstants.GB_HP_R);
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.addBonusModifier(AngelConstants.GB_MOD_W);
        wizard.setCurrentHP(wizard.getCurrentHP() + AngelConstants.GB_HP_W);
    }

    @Override
    public void visit(final Knight knight) {
        knight.addBonusModifier(AngelConstants.GB_MOD_K);
        knight.setCurrentHP(knight.getCurrentHP() + AngelConstants.GB_HP_K);
    }
}
