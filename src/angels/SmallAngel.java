package angels;

import constants.AngelConstants;
import input.Battlefield;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public class SmallAngel implements AngelVisitor {
    private static SmallAngel instance = null;

    private SmallAngel() { }
    public static SmallAngel getInstance() {
        if (instance == null) {
            instance = new SmallAngel();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.setAngelModifier(pyromancer.getAngelModifier() + AngelConstants.SA_MOD_P);
        pyromancer.setCurrentHP(pyromancer.getCurrentHP() + AngelConstants.SA_HP_P);
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.setAngelModifier(rogue.getAngelModifier() + AngelConstants.SA_MOD_R);
        rogue.setCurrentHP(rogue.getCurrentHP() + AngelConstants.SA_HP_R);
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.setAngelModifier(wizard.getAngelModifier() + AngelConstants.SA_MOD_W);
        wizard.setCurrentHP(wizard.getCurrentHP() + AngelConstants.SA_HP_W);
    }

    @Override
    public void visit(Knight knight) {
        knight.setAngelModifier(knight.getAngelModifier() + AngelConstants.SA_MOD_K);
        knight.setCurrentHP(knight.getCurrentHP() + AngelConstants.SA_HP_K);
    }
}
