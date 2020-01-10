package abilities;

import constants.RogueConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Knight;
import players.Wizard;

/**
 * Paralysis ability specific to Rogue players.
 * Singleton class implementing the PlayerVisitor interface.
 */
public final class Paralysis implements PlayersVisitor {
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
     * Apply damage and overtime damage on pyromancer.
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        Float raceModifier = RogueConstants.PARALYSIS_MOD_P;
        calculateTotalDamage(pyromancer, raceModifier);
    }

    /**
     * Apply damage and overtime damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        Float raceModifier = RogueConstants.PARALYSIS_MOD_R;
        calculateTotalDamage(rogue, raceModifier);
    }

    /**
     * Apply damage and overtime damage on wizard.
     * Also calculates damage without race modifiers.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        Float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        Float raceModifier = RogueConstants.PARALYSIS_MOD_W;
        calculateTotalDamage(wizard, raceModifier);
    }

    /**
     * Apply damage and overtime damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        Float raceModifier = RogueConstants.PARALYSIS_MOD_K;
        calculateTotalDamage(knight, raceModifier);
    }

    /**
     * Calculates damage wihout race modifiers.
     * Sets the number of overtime and immobilized rounds.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public Float calculateRawDamage(final Player victim) {
        Rogue assailant = (Rogue) battlefield.getOpponent(victim);

        Float damage = (float) (RogueConstants.PARALYSIS_BASE_DAMAGE
                + RogueConstants.PARALYSIS_LEVEL_DAMAGE * assailant.getLevel());

        if (battlefield.getPlayerLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {

            damage *= RogueConstants.LAND_TYPE_BONUS;
            victim.setImmobilized(RogueConstants.PARALYSIS_OVERTIME_WOODS + 1);
            victim.setOvertimeRounds(RogueConstants.PARALYSIS_OVERTIME_WOODS);

        } else {

            victim.setImmobilized(RogueConstants.PARALYSIS_OVERTIME + 1);
            victim.setOvertimeRounds(RogueConstants.PARALYSIS_OVERTIME);

        }
        return damage;
    }

    /**
     * Sets the total damage.
     * @param victim player who is attacked
     * @param raceMod race modifier specific to the victim
     * @return total damage with race modifiers
     */
    public void calculateTotalDamage(final Player victim, final Float raceMod) {

        Float raceModifier = raceMod;

        for (Float bonusMod: battlefield.getOpponent(victim).getBonusModifiers()) {
            raceModifier += bonusMod;
        }

        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        victim.setOvertimeDamage(damage);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
