package angels;

import constants.AngelConstants;
import constants.PlayerConstants;
import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Knight;
import players.Wizard;

/**
 * LevelUpAngel- increases XP in order for the player to reach the next level.
 *             - increases modifiers
 * Singleton class implementing the AngelVisitor interface
 */
public final class LevelUpAngel implements AngelVisitor {

    private static LevelUpAngel instance = null;

    private LevelUpAngel() { }
    public static LevelUpAngel getInstance() {
        if (instance == null) {
            instance = new LevelUpAngel();
        }
        return instance;
    }

    @Override
    public void visit(final Pyromancer pyromancer) {
       pyromancer.setCurrentXP(getXPLevelUp(pyromancer));
       pyromancer.checkLevelUp();
       pyromancer.addBonusModifier(AngelConstants.LUA_MOD_P);
    }

    @Override
    public void visit(final Rogue rogue) {
        rogue.setCurrentXP(getXPLevelUp(rogue));
        rogue.checkLevelUp();
        rogue.addBonusModifier(AngelConstants.LUA_MOD_R);
    }

    @Override
    public void visit(final Wizard wizard) {
        wizard.setCurrentXP(getXPLevelUp(wizard));
        wizard.checkLevelUp();
        wizard.addBonusModifier(AngelConstants.LUA_MOD_W);
    }

    @Override
    public void visit(final Knight knight) {
        knight.setCurrentXP(getXPLevelUp(knight));
        knight.checkLevelUp();
        knight.addBonusModifier(AngelConstants.LUA_MOD_K);
    }

    public int getXPLevelUp(final Player player) {
        return PlayerConstants.LEVEL_UP_BASE + PlayerConstants.LEVEL_UP_MULTIPLIER
                * player.getLevel();
    }
}
