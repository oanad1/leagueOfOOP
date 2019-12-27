package angels;

import constants.AngelConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public class Dracula implements AngelVisitor {

    private static Dracula instance = null;

    private Dracula() { }
    public static Dracula getInstance() {
        if (instance == null) {
            instance = new Dracula();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
        pyromancer.setAngelModifier(pyromancer.getAngelModifier() - AngelConstants.DRACULA_MOD_P);
        pyromancer.setCurrentHP(pyromancer.getCurrentHP() - AngelConstants.DRACULA_HP_P);
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.setAngelModifier(rogue.getAngelModifier() - AngelConstants.DRACULA_MOD_R);
        rogue.setCurrentHP(rogue.getCurrentHP() - AngelConstants.DRACULA_HP_R);
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.setAngelModifier(wizard.getAngelModifier() - AngelConstants.DRACULA_MOD_W);
        wizard.setCurrentHP(wizard.getCurrentHP() - AngelConstants.DRACULA_HP_W);
    }

    @Override
    public void visit(Knight knight) {
        knight.setAngelModifier(knight.getAngelModifier() - AngelConstants.DRACULA_MOD_K);
        knight.setCurrentHP(knight.getCurrentHP() - AngelConstants.DRACULA_HP_K);
    }
}
