package abilities;

import constants.PyromancerConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Knight;
import players.Wizard;

/**
 * Ignite ability specific to Pyromancer players.
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
        Float raceModifier = PyromancerConstants.IGNITE_MOD_P;
        calculateTotalDamage(pyromancer, raceModifier);
    }

    /**
     * Apply damage and overtime damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        Float raceModifier = PyromancerConstants.IGNITE_MOD_R;
        calculateTotalDamage(rogue, raceModifier);
    }

    /**
     * Apply damage and overtime damage on wizard.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        Float unmodDamage = (float) calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        Float raceModifier = PyromancerConstants.IGNITE_MOD_W;
        calculateTotalDamage(wizard, raceModifier);
    }

    /**
     * Apply damage and overtime damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        Float raceModifier = PyromancerConstants.IGNITE_MOD_K;
        calculateTotalDamage(knight, raceModifier);
    }

    /**
     * Calculates the total damage without race modifiers.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public int calculateRawDamage(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);
        Float damage;

            damage = (float) (PyromancerConstants.IGNITE_BASE_DAMAGE
                    + PyromancerConstants.IGNITE_LEVEL_DAMAGE * assailant.getLevel());

        if (battlefield.getPlayerLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE) {
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return Math.round(damage);
    }

    /**
     * Calculates the total overtime damage without race modifiers.
     * @param victim player who is attacked
     * @return total overtime damage without race modifiers
     */
    public int calculateOvertime(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);
        Float damage;

        damage = (float) (PyromancerConstants.IGNITE_SMALL_BASE_DAMAGE
                + PyromancerConstants.IGNITE_SMALL_LEVEL_DAMAGE * assailant.getLevel());

        if (battlefield.getPlayerLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE) {
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return Math.round(damage);
    }

    /**
     * Sets the total damage.
     * @param victim player who is attacked
     * @param raceMod race modifier specific to the victim
     * @return total damage with race modifiers.
     */
    public void calculateTotalDamage(final Player victim, final Float raceMod) {

        Float raceModifier = raceMod;

        //Add angel and strategy bonuses to race modifiers
        for (Float bonusMod: battlefield.getOpponent(victim).getBonusModifiers()) {
            raceModifier += bonusMod;
        }

        //Set total damage
        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);

        //Set overtime damage and overtime rounds
        damage = Math.round(calculateOvertime(victim) * raceModifier);
        victim.setOvertimeDamage(damage);
        victim.setOvertimeRounds(2);
    }
}
