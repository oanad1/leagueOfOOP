package abilities;

import constants.WizardConstants;
import input.Battlefield;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;


/**
 * Deflect ability specific to the Wizard players.
 * Singleton class implementing the PlayerVisitor interface.
 */
public final class Deflect implements PlayerVisitor {
    private static Deflect instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Deflect() { }
    public static Deflect getInstance() {
        if (instance == null) {
            instance = new Deflect();
        }
        return instance;
    }

    /**
     * Applies damage on pyromancer.
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        int damage = Math.round(calculateRawDamage(pyromancer) * WizardConstants.DEFLECT_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    /**
     * Applies damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        int damage = Math.round(calculateRawDamage(rogue) * WizardConstants.DEFLECT_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    /**
     * Does not apply anything on another wizard.
     * @param wizard
     */
    public void visit(final Wizard wizard) {
        return;
    }

    /**
     * Applies damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        int damage = Math.round(calculateRawDamage(knight) * WizardConstants.DEFLECT_MOD_K);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    /**
     * Calculates the total damage by using the victim's opponent and his unmodified damage.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Wizard assailant = (Wizard) battlefield.getOpponent(victim);

        float percent = WizardConstants.DEFLECT_BASE_PROCENT
                + WizardConstants.DEFLECT_LEVEL_PROCENT * assailant.getLevel();
        if (percent > WizardConstants.DEFLECT_MAX_PROCENT) {
            percent = WizardConstants.DEFLECT_MAX_PROCENT;
        }
        float damage = percent * assailant.getUnmodifiedDamage();

        if (battlefield.getLot(assailant).getLandType() == WizardConstants.LAND_TYPE) {
            damage *= WizardConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
