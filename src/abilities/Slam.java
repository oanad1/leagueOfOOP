package abilities;

import constants.KnightConstants;
import input.Battlefield;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;


/**
 * Slam ability specific to the Knight players
 * Singleton class implementing the PlayerVisitor interface
 */
public final class Slam implements PlayerVisitor {
    private static Slam instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Slam() { }
    public static Slam getInstance() {
        if (instance == null) {
            instance = new Slam();
        }
        return instance;
    }

    /**
     * Applies damage on pyromancer and immobilizes him
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        int damage = Math.round(calculateRawDamage(pyromancer) * KnightConstants.SLAM_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
        pyromancer.setImmobilized(1);
    }

    /**
     * Applies damage on rogue and immobilizes him
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        int damage = Math.round(calculateRawDamage(rogue) * KnightConstants.SLAM_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
        rogue.setImmobilized(1);
    }

    /**
     * Applies damage on wizard and immobilizes him
     * Also calculates damage without race modifiers
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * KnightConstants.SLAM_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
        wizard.setImmobilized(1);
    }

    /**
     * Applies damage on knight and immobilizes him
     * @param knight victim
     */
    public void visit(final Knight knight) {
        int damage = Math.round(calculateRawDamage(knight) * KnightConstants.SLAM_MOD_K);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
        knight.setImmobilized(1);
    }

    /**
     * Calculates the total damage by using the victim's opponent
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);

        float damage = KnightConstants.SLAM_BASE_DAMAGE
                + KnightConstants.SLAM_LEVEL_DAMAGE * assailant.getLevel();
        if (battlefield.getLot(assailant).getLandType() == KnightConstants.LAND_TYPE) {
            damage *= KnightConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
