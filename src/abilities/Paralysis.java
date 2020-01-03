package abilities;

import constants.RogueConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.*;

/**
 * Paralysis ability specific to the Rogue players.
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
        float raceModifier = RogueConstants.PARALYSIS_MOD_P + battlefield.getOpponent(pyromancer).getAngelModifier();
        calculateTotalDamage(pyromancer,raceModifier);
    }

    /**
     * Apply damage and overtime damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        float raceModifier = RogueConstants.PARALYSIS_MOD_R + battlefield.getOpponent(rogue).getAngelModifier();
        calculateTotalDamage(rogue,raceModifier);
    }

    /**
     * Apply damage and overtime damage on wizard.
     * Also calculates damage without race modifiers.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        float raceModifier = RogueConstants.PARALYSIS_MOD_W + battlefield.getOpponent(wizard).getAngelModifier();
        calculateTotalDamage(wizard,raceModifier);
    }

    /**
     * Apply damage and overtime damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        float raceModifier = RogueConstants.PARALYSIS_MOD_K + battlefield.getOpponent(knight).getAngelModifier();
        calculateTotalDamage(knight,raceModifier);
    }

    /**
     * Calculates the total damage and sets overtime and immobilized rounds.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Rogue assailant = (Rogue) battlefield.getOpponent(victim);

        float damage = RogueConstants.PARALYSIS_BASE_DAMAGE
                + RogueConstants.PARALYSIS_LEVEL_DAMAGE * assailant.getLevel();

        if (battlefield.getPlayerLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {
            damage *= RogueConstants.LAND_TYPE_BONUS;
            victim.setImmobilized(RogueConstants.PARALYSIS_OVERTIME_WOODS);
            victim.setOvertimeRounds(RogueConstants.PARALYSIS_OVERTIME_WOODS);
        } else {
            victim.setImmobilized(RogueConstants.PARALYSIS_OVERTIME);
            victim.setOvertimeRounds(RogueConstants.PARALYSIS_OVERTIME);
        }
        return damage;
    }

    public void calculateTotalDamage(final Player victim, final float raceModifier) {
        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        victim.setOvertimeDamage(damage);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
