package angels;

import constants.AngelConstants;
import input.Battlefield;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public class XPAngel implements AngelVisitor{

    private static XPAngel instance = null;

    private XPAngel() { }
    public static XPAngel getInstance() {
        if (instance == null) {
            instance = new XPAngel();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.setCurrentXP(pyromancer.getCurrentXP() + AngelConstants.XPANGEL_XP_P);
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.setCurrentXP(rogue.getCurrentXP() + AngelConstants.XPANGEL_XP_R);
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.setCurrentXP(wizard.getCurrentXP() + AngelConstants.XPANGEL_XP_W);
    }

    @Override
    public void visit(Knight knight) {
        knight.setCurrentXP(knight.getCurrentXP() + AngelConstants.XPANGEL_XP_K);
    }
}
