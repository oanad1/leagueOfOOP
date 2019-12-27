package angels;

import constants.AngelConstants;
import input.Battlefield;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public class LifeGiver implements AngelVisitor {

    private static LifeGiver instance = null;

    private LifeGiver() { }
    public static LifeGiver getInstance() {
        if (instance == null) {
            instance = new LifeGiver();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.setCurrentHP(pyromancer.getCurrentHP() + AngelConstants.LG_HP_P);
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.setCurrentHP(rogue.getCurrentHP() + AngelConstants.LG_HP_R);
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.setCurrentHP(wizard.getCurrentHP() + AngelConstants.LG_HP_W);
    }

    @Override
    public void visit(Knight knight) {
        knight.setCurrentHP(knight.getCurrentHP() + AngelConstants.LG_HP_K);
    }
}
