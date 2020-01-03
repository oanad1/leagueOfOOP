package abilities;

import constants.RogueConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.*;

/**
 * Backstab ability specific to the Rogue players.
 * Singleton class implementing the PlayerVisitor interface
 */
public final class Backstab implements PlayersVisitor {
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
     * Applies damage on pyromancer.
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        float raceModifiers = RogueConstants.BACKSTAB_MOD_P + battlefield.getOpponent(pyromancer).getAngelModifier();
        calculateTotalDamage(pyromancer,raceModifiers);
    }

    /**
     * Applies damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        float raceModifiers = RogueConstants.BACKSTAB_MOD_R + battlefield.getOpponent(rogue).getAngelModifier();
        calculateTotalDamage(rogue,raceModifiers);
    }

    /**
     * Applies damage on wizard.
     * Also calculates unmodified damage
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        float raceModifiers = RogueConstants.BACKSTAB_MOD_W + battlefield.getOpponent(wizard).getAngelModifier();
        int damage = Math.round(unmodDamage * raceModifiers);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    /**
     * Applies damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        float raceModifiers = RogueConstants.BACKSTAB_MOD_K + battlefield.getOpponent(knight).getAngelModifier();
        calculateTotalDamage(knight,raceModifiers);
    }

    /**
     * Calculates the total damage, applying a critical hit once every 3 fights.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Rogue assailant = (Rogue) battlefield.getOpponent(victim);

        float damage = RogueConstants.BACKSTAB_BASE_DAMAGE
                + RogueConstants.BACKSTAB_LEVEL_DAMAGE * assailant.getLevel();

        if (assailant.getNrBackstabHits() % RogueConstants.BACKSTAB_NR_HITS == 0) {
            if (battlefield.getPlayerLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {
                damage *= RogueConstants.BACKSTAB_CRITICAL;
                assailant.setNrBackstabHits(assailant.getNrBackstabHits() + 1);
            } else {
                assailant.setNrBackstabHits(0);
            }
        } else {
            assailant.setNrBackstabHits(assailant.getNrBackstabHits() + 1);
        }

        if (battlefield.getPlayerLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {
            damage *= RogueConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    public void calculateTotalDamage(final Player victim, final float raceModifiers) {
        int damage = Math.round(calculateRawDamage(victim) * raceModifiers);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
