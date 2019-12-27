package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public class DamageAngel implements AngelVisitor {

    private static DamageAngel instance = null;

    private DamageAngel() { }
    public static DamageAngel getInstance() {
        if (instance == null) {
            instance = new DamageAngel();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.setAngelModifier(pyromancer.getAngelModifier() + AngelConstants.DA_MOD_P);
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.setAngelModifier(rogue.getAngelModifier() + AngelConstants.DA_MOD_R);
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.setAngelModifier(wizard.getAngelModifier() + AngelConstants.DA_MOD_W);
    }

    @Override
    public void visit(Knight knight) {
        knight.setAngelModifier(knight.getAngelModifier() + AngelConstants.DA_MOD_K);
    }
}
