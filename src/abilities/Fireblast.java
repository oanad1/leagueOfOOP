package abilities;

import constants.PyromancerConstants;
import input.Battlefield;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;


/**
 * Ignote ability specific to the Pyromancer players.
 * Singleton class implementing the PlayerVisitor interface.
 */
public final class Fireblast implements PlayerVisitor {
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
        int damage = Math.round(calculateRawDamage(pyromancer)
                * PyromancerConstants.FIREBLAST_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    /**
     * Apply damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        int damage = Math.round(calculateRawDamage(rogue) * PyromancerConstants.FIREBLAST_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    /**
     * Apply damage on wizard.
     * Also calculate unmodified damage
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * PyromancerConstants.FIREBLAST_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    /**
     * Apply damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        int damage = Math.round(calculateRawDamage(knight) * PyromancerConstants.FIREBLAST_MOD_K);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
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
        if (battlefield.getLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE) {
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
