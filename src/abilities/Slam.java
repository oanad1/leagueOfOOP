package abilities;

import constants.KnightConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Knight;
import players.Wizard;


/**
 * Slam ability specific to Knight players.
 * Singleton class implementing the PlayerVisitor interface.
 */
public final class Slam implements PlayersVisitor {
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
     * Applies damage on pyromancer and immobilizes him.
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        Float raceModifiers =  KnightConstants.SLAM_MOD_P;
        calculateTotalDamage(pyromancer, raceModifiers);
    }

    /**
     * Applies damage on rogue and immobilizes him.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        Float raceModifiers =  KnightConstants.SLAM_MOD_R;
        calculateTotalDamage(rogue, raceModifiers);
    }

    /**
     * Applies damage on wizard and immobilizes him.
     * Calculates unmodified damage for wizard.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        Float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage()
                + Math.round(unmodDamage));

        Float raceModifiers =  KnightConstants.SLAM_MOD_W;
        calculateTotalDamage(wizard, raceModifiers);
    }

    /**
     * Applies damage on knight and immobilizes him.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        Float raceModifiers =  KnightConstants.SLAM_MOD_K;
        calculateTotalDamage(knight, raceModifiers);
    }

    /**
     * Calculates the damage without race modifiers.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public Float calculateRawDamage(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);

        Float damage = (float) (KnightConstants.SLAM_BASE_DAMAGE
                + KnightConstants.SLAM_LEVEL_DAMAGE * assailant.getLevel());

        if (battlefield.getPlayerLot(assailant).getLandType() == KnightConstants.LAND_TYPE) {
            damage *= KnightConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    /**
     * Sets the total damage.
     * @param victim player who is attacked
     * @param raceMod race modifier specific to the victim
     * @return total damage with race modifiers
     */
    public void calculateTotalDamage(final Player victim, final Float raceMod) {

        Float raceModifier = raceMod;
        //Add angel and strategy bonuses to race modifiers
        for (Float bonusMod: battlefield.getOpponent(victim).getBonusModifiers()) {
            raceModifier = raceModifier + bonusMod;
        }
        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
        victim.setToImobilize(true);
    }
}
