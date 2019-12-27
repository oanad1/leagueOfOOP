package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public class GoodBoy implements AngelVisitor {

    private static GoodBoy instance = null;

    private GoodBoy() { }
    public static GoodBoy getInstance() {
        if (instance == null) {
            instance = new GoodBoy();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.setAngelModifier(pyromancer.getAngelModifier() + AngelConstants.GB_MOD_P);
        pyromancer.setCurrentHP(pyromancer.getCurrentHP() + AngelConstants.GB_HP_P);
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.setAngelModifier(rogue.getAngelModifier() + AngelConstants.GB_MOD_R);
        rogue.setCurrentHP(rogue.getCurrentHP() + AngelConstants.GB_HP_R);
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.setAngelModifier(wizard.getAngelModifier() + AngelConstants.GB_MOD_W);
        wizard.setCurrentHP(wizard.getCurrentHP() + AngelConstants.GB_HP_W);
    }

    @Override
    public void visit(Knight knight) {
        knight.setAngelModifier(knight.getAngelModifier() + AngelConstants.GB_MOD_K);
        knight.setCurrentHP(knight.getCurrentHP() + AngelConstants.GB_HP_K);
    }
}
