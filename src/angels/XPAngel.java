package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * XP Angel- increases XP.
 * Singleton class implementing the AngelVisitor interface
 */
public final class XPAngel implements AngelVisitor {

    private static XPAngel instance = null;

    private XPAngel() { }
    public static XPAngel getInstance() {
        if (instance == null) {
            instance = new XPAngel();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        pyromancer.setCurrentXP(pyromancer.getCurrentXP() + AngelConstants.XPANGEL_XP_P);
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.setCurrentXP(rogue.getCurrentXP() + AngelConstants.XPANGEL_XP_R);
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.setCurrentXP(wizard.getCurrentXP() + AngelConstants.XPANGEL_XP_W);
    }

    @Override
    public void visit(final Knight knight) {
        knight.setCurrentXP(knight.getCurrentXP() + AngelConstants.XPANGEL_XP_K);
    }
}
