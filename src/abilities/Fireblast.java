package abilities;

import constants.PyromancerConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.*;


/**
 * Ignote ability specific to the Pyromancer players.
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
        float raceModifier = PyromancerConstants.FIREBLAST_MOD_P + battlefield.getOpponent(pyromancer).getAngelModifier();
        calculateTotalDamage(pyromancer,raceModifier);
    }

    /**
     * Apply damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        float raceModifier = PyromancerConstants.FIREBLAST_MOD_R + battlefield.getOpponent(rogue).getAngelModifier();
        calculateTotalDamage(rogue,raceModifier);
    }

    /**
     * Apply damage on wizard.
     * Also calculate unmodified damage
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        float raceModifier = PyromancerConstants.FIREBLAST_MOD_W + battlefield.getOpponent(wizard).getAngelModifier();
        calculateTotalDamage(wizard,raceModifier);
    }

    /**
     * Apply damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        float raceModifier = PyromancerConstants.FIREBLAST_MOD_K + battlefield.getOpponent(knight).getAngelModifier();
        calculateTotalDamage(knight,raceModifier);
    }

    /**
     * Calculates the total damage without race modifiers.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);
        float damage = PyromancerConstants.FIREBLAST_BASE_DAMAGE
                + PyromancerConstants.FIREBLAST_LEVEL_DAMAGE * assailant.getLevel();
        if (battlefield.getPlayerLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE) {
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    public void calculateTotalDamage(final Player victim, final float raceModifier) {
        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
