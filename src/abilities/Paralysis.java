package abilities;

import constants.RogueConstants;
import input.Battlefield;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;

/**
 * Paralysis ability specific to the Rogue players
 * Singleton class implementing the PlayerVisitor interface
 */
public final class Paralysis implements PlayerVisitor {
    private static Paralysis instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Paralysis() { }
    public static Paralysis getInstance() {
        if (instance == null) {
            instance = new Paralysis();
        }
        return instance;
    }

    /**
     * Apply damage and overtime damage on pyromancer
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        int damage = Math.round(calculateRawDamage(pyromancer) * RogueConstants.PARALYSIS_MOD_P);
        pyromancer.setOvertimeDamage(damage);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    /**
     * Apply damage and overtime damage on rogue
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        int damage = Math.round(calculateRawDamage(rogue) * RogueConstants.PARALYSIS_MOD_R);
        rogue.setOvertimeDamage(damage);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    /**
     * Apply damage and overtime damage on wizard
     * Also calculates damage without race modifiers.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * RogueConstants.PARALYSIS_MOD_W);
        wizard.setOvertimeDamage(damage);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    /**
     * Apply damage and overtime damage on knight
     * @param knight victim
     */
    public void visit(final Knight knight) {
        int damage = Math.round(calculateRawDamage(knight) * RogueConstants.PARALYSIS_MOD_K);
        knight.setOvertimeDamage(damage);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    /**
     * Calculates the total damage and sets overtime and immobilized rounds
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Rogue assailant = (Rogue) battlefield.getOpponent(victim);

        float damage = RogueConstants.PARALYSIS_BASE_DAMAGE
                + RogueConstants.PARALYSIS_LEVEL_DAMAGE * assailant.getLevel();

        if (battlefield.getLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {
            damage *= RogueConstants.LAND_TYPE_BONUS;
            victim.setImmobilized(RogueConstants.PARALYSIS_OVERTIME_WOODS);
            victim.setOvertimeRounds(RogueConstants.PARALYSIS_OVERTIME_WOODS);
        } else {
            victim.setImmobilized(RogueConstants.PARALYSIS_OVERTIME);
            victim.setOvertimeRounds(RogueConstants.PARALYSIS_OVERTIME);
        }
        return damage;
    }
}
