package abilities;

import constants.KnightConstants;
import constants.RogueConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.*;


/**
 * Slam ability specific to the Knight players.
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
        float raceModifiers =  KnightConstants.SLAM_MOD_P + battlefield.getOpponent(pyromancer).getAngelModifier();
        calculateTotalDamage(pyromancer,raceModifiers);
    }

    /**
     * Applies damage on rogue and immobilizes him.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        float raceModifiers =  KnightConstants.SLAM_MOD_R + battlefield.getOpponent(rogue).getAngelModifier();
        calculateTotalDamage(rogue,raceModifiers);
    }

    /**
     * Applies damage on wizard and immobilizes him.
     * Also calculates damage without race modifiers
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        float raceModifiers =  KnightConstants.SLAM_MOD_W + battlefield.getOpponent(wizard).getAngelModifier();
        calculateTotalDamage(wizard,raceModifiers);
    }

    /**
     * Applies damage on knight and immobilizes him.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        float raceModifiers =  KnightConstants.SLAM_MOD_K + battlefield.getOpponent(knight).getAngelModifier();
        calculateTotalDamage(knight,raceModifiers);
    }

    /**
     * Calculates the total damage by using the victim's opponent.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);

        float damage = KnightConstants.SLAM_BASE_DAMAGE
                + KnightConstants.SLAM_LEVEL_DAMAGE * assailant.getLevel();
        if (battlefield.getPlayerLot(assailant).getLandType() == KnightConstants.LAND_TYPE) {
            damage *= KnightConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    public void calculateTotalDamage(final Player victim, final float raceModifier) {
        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
        victim.setImmobilized(1);
    }
}
