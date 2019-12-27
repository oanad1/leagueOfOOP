package angels;

import constants.AngelConstants;
import constants.UniversalConstants;
import players.*;

public class LevelUpAngel implements AngelVisitor {

    private static LevelUpAngel instance = null;

    private LevelUpAngel() { }
    public static LevelUpAngel getInstance() {
        if (instance == null) {
            instance = new LevelUpAngel();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
       pyromancer.setCurrentXP(getXPLevelUp(pyromancer));
       pyromancer.checkLevelUp();
       pyromancer.setAngelModifier(pyromancer.getAngelModifier() + AngelConstants.LUA_MOD_P);
    }

    @Override
    public void visit(Rogue rogue) {
        rogue.setCurrentXP(getXPLevelUp(rogue));
        rogue.checkLevelUp();
        rogue.setAngelModifier(rogue.getAngelModifier() + AngelConstants.LUA_MOD_R);
    }

    @Override
    public void visit(Wizard wizard) {
        wizard.setCurrentXP(getXPLevelUp(wizard));
        wizard.checkLevelUp();
        wizard.setAngelModifier(wizard.getAngelModifier() + AngelConstants.LUA_MOD_W);
    }

    @Override
    public void visit(Knight knight) {
        knight.setCurrentXP(getXPLevelUp(knight));
        knight.checkLevelUp();
        knight.setAngelModifier(knight.getAngelModifier() + AngelConstants.LUA_MOD_K);
    }

    public int getXPLevelUp(Player player) {
        return UniversalConstants.LEVEL_UP_BASE + UniversalConstants.LEVEL_UP_MULTIPLIER * player.getLevel();
    }
}
