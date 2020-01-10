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
 * Backstab ability specific to Rogue players.
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
        Float raceModifiers = RogueConstants.BACKSTAB_MOD_P;
        calculateTotalDamage(pyromancer, raceModifiers);
    }

    /**
     * Applies damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        Float raceModifiers = RogueConstants.BACKSTAB_MOD_R;
        calculateTotalDamage(rogue, raceModifiers);
    }

    /**
     * Applies damage on wizard.
     * Calculates unmodified damage
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        Float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        Float raceModifiers = RogueConstants.BACKSTAB_MOD_W;

        for (Float bonusMod: battlefield.getOpponent(wizard).getBonusModifiers()) {
            raceModifiers += bonusMod;
        }

        int damage = Math.round(unmodDamage * raceModifiers);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    /**
     * Applies damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        Float raceModifiers = RogueConstants.BACKSTAB_MOD_K;
        calculateTotalDamage(knight, raceModifiers);
    }

    /**
     * Calculates the damage without race modifiers, applying a critical hit once every 3 fights.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public Float calculateRawDamage(final Player victim) {
        Rogue assailant = (Rogue) battlefield.getOpponent(victim);

        Float damage = (float) (RogueConstants.BACKSTAB_BASE_DAMAGE
                + RogueConstants.BACKSTAB_LEVEL_DAMAGE * assailant.getLevel());

        //Check critical hit and apply bonus
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

        //Apply land bonus
        if (battlefield.getPlayerLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {
            damage *= RogueConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    /**
     * Sets the total damage.
     * @param victim player who is attacked
     * @param raceMod race modifier specific to the victim
     * @return total damage without race modifiers
     */
    public void calculateTotalDamage(final Player victim, final Float raceMod) {

        Float raceModifiers = raceMod;

        //Add angel and strategy bonuses to the land modifiers
        for (Float bonusMod: battlefield.getOpponent(victim).getBonusModifiers()) {
            raceModifiers += bonusMod;
        }

        int damage = Math.round(calculateRawDamage(victim) * raceModifiers);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
