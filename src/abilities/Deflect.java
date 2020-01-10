package abilities;

import constants.WizardConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Knight;
import players.Wizard;

/**
 * Deflect ability specific to Wizard players.
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
        Float raceModifier = WizardConstants.DEFLECT_MOD_P;
        calculateTotalDamage(pyromancer, raceModifier);
    }


    /**
     * Applies damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        Float raceModifier = WizardConstants.DEFLECT_MOD_R;
        calculateTotalDamage(rogue, raceModifier);
    }


    /**
     * Does not do anything to another wizard.
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
        Float raceModifier = WizardConstants.DEFLECT_MOD_K;
        calculateTotalDamage(knight, raceModifier);
    }

    /**
     * Calculates the total damage without race modifiers.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public Float calculateRawDamage(final Player victim) {
        Wizard assailant = (Wizard) battlefield.getOpponent(victim);

        Float percent = WizardConstants.DEFLECT_BASE_PROCENT
                + WizardConstants.DEFLECT_LEVEL_PROCENT * assailant.getLevel();
        if (percent > WizardConstants.DEFLECT_MAX_PROCENT) {
            percent = WizardConstants.DEFLECT_MAX_PROCENT;
        }
        Float damage = percent * assailant.getUnmodifiedDamage();

        if (battlefield.getPlayerLot(assailant).getLandType() == WizardConstants.LAND_TYPE) {
            damage *= WizardConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    /**
     * Sets the total damage for the victim.
     * @param victim player who is attacked
     * @param raceMod race modifier specific to the victim
     * @return total damage with race modifiers
     */
    public void calculateTotalDamage(final Player victim, final Float raceMod) {

        Float raceModifier = raceMod;

        //Add angel and strategy bonuses to the land modifiers
        for (Float bonusMod: battlefield.getOpponent(victim).getBonusModifiers()) {
            raceModifier += bonusMod;
        }

        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
