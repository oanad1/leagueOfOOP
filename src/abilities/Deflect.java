package abilities;

import constants.RogueConstants;
import constants.WizardConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.*;


/**
 * Deflect ability specific to the Wizard players.
 * Singleton class implementing the PlayerVisitor interface.
 */
public final class Deflect implements PlayersVisitor {
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
        float raceModifier = WizardConstants.DEFLECT_MOD_P + battlefield.getOpponent(pyromancer).getAngelModifier();
        calculateTotalDamage(pyromancer,raceModifier);
    }

    /**
     * Applies damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        float raceModifier = WizardConstants.DEFLECT_MOD_R + battlefield.getOpponent(rogue).getAngelModifier();
        calculateTotalDamage(rogue,raceModifier);
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
        float raceModifier = WizardConstants.DEFLECT_MOD_K + battlefield.getOpponent(knight).getAngelModifier();
        calculateTotalDamage(knight,raceModifier);
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

        if (battlefield.getPlayerLot(assailant).getLandType() == WizardConstants.LAND_TYPE) {
            damage *= WizardConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    public void calculateTotalDamage(final Player victim, final float raceModifier) {
        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
