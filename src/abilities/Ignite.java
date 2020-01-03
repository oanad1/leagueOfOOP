package abilities;

import constants.PyromancerConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.*;


/**
 * Ignite ability specific to the Pyromancer players.
 * Singleton class implementing the PlayerVisitor interface.
 */
public final class Ignite implements PlayersVisitor {
    private static Ignite instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Ignite() { }
    public static Ignite getInstance() {
        if (instance == null) {
            instance = new Ignite();
        }
        return instance;
    }


    /**
     * Apply damage and overtime damage on pyromancer.
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        float raceModifier = PyromancerConstants.IGNITE_MOD_P + battlefield.getOpponent(pyromancer).getAngelModifier();
        calculateTotalDamage(pyromancer,raceModifier);
    }

    /**
     * Apply damage and overtime damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        float raceModifier = PyromancerConstants.IGNITE_MOD_R + battlefield.getOpponent(rogue).getAngelModifier();
        calculateTotalDamage(rogue,raceModifier);
    }

    /**
     * Apply damage and overtime damage on wizard.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        float raceModifier = PyromancerConstants.IGNITE_MOD_W + battlefield.getOpponent(wizard).getAngelModifier();
        calculateTotalDamage(wizard,raceModifier);
    }

    /**
     * Apply damage and overtime damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        float raceModifier = PyromancerConstants.IGNITE_MOD_K + battlefield.getOpponent(knight).getAngelModifier();
        calculateTotalDamage(knight,raceModifier);
    }

    /**
     * Calculates the total damage without race modifiers.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);
        Fight fight = Fight.getInstance();
        float damage;

            damage = PyromancerConstants.IGNITE_BASE_DAMAGE
                    + PyromancerConstants.IGNITE_LEVEL_DAMAGE * assailant.getLevel();

        if (battlefield.getPlayerLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE) {
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    /**
     * Calculates the total overtime damage without race modifiers.
     * @param victim player who is attacked
     * @return total overtime damage without race modifiers
     */
    public float calculateOvertime(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);
        Fight fight = Fight.getInstance();
        float damage;

        damage = PyromancerConstants.IGNITE_SMALL_BASE_DAMAGE
                + PyromancerConstants.IGNITE_SMALL_LEVEL_DAMAGE * assailant.getLevel();

        if (battlefield.getPlayerLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE) {
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    public void calculateTotalDamage(final Player victim, final float raceModifier) {
        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);

        damage = Math.round(calculateOvertime(victim) * raceModifier);
        victim.setOvertimeDamage(damage);
        victim.setOvertimeRounds(2);
    }
}
