package abilities;

import constants.KnightConstants;
import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Knight;
import players.Wizard;

/**
 * Execute ability specific to Knight players.
 * Singleton class implementing the PlayerVisitor interface.
 */
public final class Execute implements PlayersVisitor {
    private static Execute instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Execute() { }
    public static Execute getInstance() {
        if (instance == null) {
            instance = new Execute();
        }
        return instance;
    }

    /**
     * Calculates HP limit and determines if instant kill can be applied;
     * If not, applies normal damage on pyromancer.
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        Float raceModifier = KnightConstants.EXECUTE_MOD_P;
        int maxLevelHP = PyromancerConstants.BASE_HP
                + pyromancer.getLevel() * PyromancerConstants.LEVEL_HP;

        calculateTotalDamage(pyromancer, raceModifier, maxLevelHP);
    }

    /**
     * Calculates HP limit and determines if instant kill can be applied;
     * If not, applies normal damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        Float raceModifier = KnightConstants.EXECUTE_MOD_R;
        int maxLevelHP = RogueConstants.BASE_HP
                + rogue.getLevel() * RogueConstants.LEVEL_HP;

        calculateTotalDamage(rogue, raceModifier, maxLevelHP);
    }

    /**
     * Calculates HP limit and determines if instant kill can be applied;
     * If not, applies normal damage on wizard.
     * Calculates unmodified damage for wizard.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {

        Float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        Float raceModifier = KnightConstants.EXECUTE_MOD_W;
        int maxLevelHP = WizardConstants.BASE_HP
                + wizard.getLevel() * WizardConstants.LEVEL_HP;

        calculateTotalDamage(wizard, raceModifier, maxLevelHP);
        }

    /**
     * Calculates HP limit and determines if instant kill can be applied;
     * If not, applies normal damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        Float raceModifier = 1f;
        int maxLevelHP = KnightConstants.BASE_HP
                + knight.getLevel() * KnightConstants.LEVEL_HP;
        calculateTotalDamage(knight, raceModifier, maxLevelHP);
    }

    /**
     * Calculates the damage without race modifiers.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public Float calculateRawDamage(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);

        Float damage = (float) (KnightConstants.EXECUTE_BASE_DAMAGE
                + KnightConstants.EXECUTE_LEVEL_DAMAGE * assailant.getLevel());

        if (battlefield.getPlayerLot(assailant).getLandType() == KnightConstants.LAND_TYPE) {
            damage *= KnightConstants.LAND_TYPE_BONUS;
        }

        return damage;
    }

    /**
     * Sets the total damage.
     * @param victim player who is attacked
     * @param raceMod race modifier specific to the victim
     * @param maxLevelHP the victim's max HP for his level
     * @return total damage with race modifiers
     */
    public void calculateTotalDamage(final Player victim,
                                     final Float raceMod, final int maxLevelHP) {

        Float raceModifier = raceMod;

        //Add angel and strategy bonuses to the land modifiers
        if (raceModifier != 1f) {
            for (Float bonusMod : battlefield.getOpponent(victim).getBonusModifiers()) {
                raceModifier += bonusMod;
            }
        }

        Float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * victim.getLevel();

        if (levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

        int hpLimit = Math.round(KnightConstants.EXECUTE_INSTANT_PERCENT * maxLevelHP
                + levelProcent);

        //If the victim's HP is smaller than the limit, apply instant kill
        if (victim.getCurrentHP() < hpLimit) {
            victim.setRoundDamage(victim.getCurrentHP());
            return;
        }

        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
