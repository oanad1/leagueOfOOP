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
public final class Fireblast implements PlayersVisitor {
    private static Fireblast instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    public static Fireblast getInstance() {
        if (instance == null) {
            instance = new Fireblast();
        }
        return instance;
    }

    /**
     * Apply damage on pyromancer.
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        Float raceModifier = PyromancerConstants.FIREBLAST_MOD_P;
        calculateTotalDamage(pyromancer, raceModifier);
    }

    /**
     * Apply damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        Float raceModifier = PyromancerConstants.FIREBLAST_MOD_R;
        calculateTotalDamage(rogue, raceModifier);
    }

    /**
     * Apply damage on wizard.
     * Calculate unmodified damage for wizard.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        Float unmodDamage = (float) calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        Float raceModifier = PyromancerConstants.FIREBLAST_MOD_W;
        calculateTotalDamage(wizard, raceModifier);
    }

    /**
     * Apply damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        Float raceModifier = PyromancerConstants.FIREBLAST_MOD_K;
        calculateTotalDamage(knight, raceModifier);
    }

    /**
     * Calculates the total damage without race modifiers.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public int calculateRawDamage(final Player victim) {

        Player assailant = battlefield.getOpponent(victim);
        Float damage = (float) (PyromancerConstants.FIREBLAST_BASE_DAMAGE
                + PyromancerConstants.FIREBLAST_LEVEL_DAMAGE * assailant.getLevel());

        //Apply land modifiers
        if (battlefield.getPlayerLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE) {
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }

        return Math.round(damage);
    }

    /**
     * Sets the total damage.
     * @param victim player who is attacked
     * @param raceMod race modifier specific to the victim
     * @return total damage with race modifiers
     */
    public void calculateTotalDamage(final Player victim, final Float raceMod) {

        Float raceModifier = raceMod;

        //Add angel and strategy bonuses to the land modifiers
        for (Float bonusMod: battlefield.getOpponent(victim).getBonusModifiers()) {
            raceModifier += bonusMod;
        }

        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
