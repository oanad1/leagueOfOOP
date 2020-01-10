package angels;

import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import constants.KnightConstants;
import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * LifeGiver- increases HP.
 * Singleton class implementing the AngelVisitor interface
 */
public final class LifeGiver implements AngelVisitor {

    private static LifeGiver instance = null;

    private LifeGiver() { }
    public static LifeGiver getInstance() {
        if (instance == null) {
            instance = new LifeGiver();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
        int maxLevelHP = PyromancerConstants.BASE_HP + pyromancer.getLevel()
                * PyromancerConstants.LEVEL_HP;
        pyromancer.setCurrentHP(pyromancer.getCurrentHP() + AngelConstants.LG_HP_P);
        if (pyromancer.getCurrentHP() > maxLevelHP) {
            pyromancer.setCurrentHP(maxLevelHP);
        }
    }

    @Override
    public void visit(final Rogue rogue) {
        int maxLevelHP = RogueConstants.BASE_HP + rogue.getLevel() * RogueConstants.LEVEL_HP;
        rogue.setCurrentHP(rogue.getCurrentHP() + AngelConstants.LG_HP_R);
        if (rogue.getCurrentHP() > maxLevelHP) {
            rogue.setCurrentHP(maxLevelHP);
        }
    }

    @Override
    public void visit(final Wizard wizard) {
        int maxLevelHP = WizardConstants.BASE_HP + wizard.getLevel() * WizardConstants.LEVEL_HP;
        wizard.setCurrentHP(wizard.getCurrentHP() + AngelConstants.LG_HP_W);
        if (wizard.getCurrentHP() > maxLevelHP) {
            wizard.setCurrentHP(maxLevelHP);
        }
    }

    @Override
    public void visit(final Knight knight) {
        int maxLevelHP = KnightConstants.BASE_HP + knight.getLevel() * KnightConstants.LEVEL_HP;
        knight.setCurrentHP(knight.getCurrentHP() + AngelConstants.LG_HP_K);
        if (knight.getCurrentHP() > maxLevelHP) {
            knight.setCurrentHP(maxLevelHP);
        }
    }
}
