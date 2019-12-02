package abilities;

import constants.RogueConstants;
import input.Battlefield;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;

/**
 * Backstab ability specific to the Rogue players
 * Singleton class implementing the PlayerVisitor interface
 */
public final class Backstab implements PlayerVisitor {
    private static Backstab instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Backstab() { }
    public static Backstab getInstance() {
        if (instance == null) {
            instance = new Backstab();
        }
        return instance;
    }

    /**
     * Applies damage on pyromancer
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        int damage = Math.round(calculateRawDamage(pyromancer) * RogueConstants.BACKSTAB_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    /**
     * Applies damage on rogue
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        int damage = Math.round(calculateRawDamage(rogue) * RogueConstants.BACKSTAB_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    /**
     * Applies damage on wizard
     * Also calculates unmodified damage
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * RogueConstants.BACKSTAB_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    /**
     * Applies damage on knight
     * @param knight victim
     */
    public void visit(final Knight knight) {
        int damage = Math.round(calculateRawDamage(knight) * RogueConstants.BACKSTAB_MOD_K);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    /**
     * Calculates the total damage, applying a critical hit once every 3 fights
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Rogue assailant = (Rogue) battlefield.getOpponent(victim);

        float damage = RogueConstants.BACKSTAB_BASE_DAMAGE
                + RogueConstants.BACKSTAB_LEVEL_DAMAGE * assailant.getLevel();

        if (assailant.getNrBackstabHits() % RogueConstants.BACKSTAB_NR_HITS == 0) {
            if (battlefield.getLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {
                damage *= RogueConstants.BACKSTAB_CRITICAL;
                assailant.setNrBackstabHits(assailant.getNrBackstabHits() + 1);
            } else {
                assailant.setNrBackstabHits(0);
            }
        } else {
            assailant.setNrBackstabHits(assailant.getNrBackstabHits() + 1);
        }

        if (battlefield.getLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {
            damage *= RogueConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
