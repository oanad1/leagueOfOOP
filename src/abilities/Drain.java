package abilities;

import constants.KnightConstants;
import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Knight;
import players.Wizard;

/**
 * Drain ability specific to the Wizard players.
 * Singleton class implementing the PlayerVisitor interface.
 */
public final class Drain implements PlayersVisitor {
    private static Drain instance = null;
    private Battlefield battlefield = Battlefield.getInstance();


    private Drain() { }
    public static Drain getInstance() {
        if (instance == null) {
            instance = new Drain();
        }
        return instance;
    }

    /**
     * Applies damage on pyromancer based on a procent of its min HP.
     * @param pyromancer victimAppApplyly
     */
    public void visit(final Pyromancer pyromancer) {
        float oppMaxHP = WizardConstants.DRAIN_OPP_MAX_PROCENT * (PyromancerConstants.BASE_HP
                + PyromancerConstants.LEVEL_HP * pyromancer.getLevel());

        float raceModifier = WizardConstants.DRAIN_MOD_P
                + battlefield.getOpponent(pyromancer).getAngelModifier();
        calculateTotalDamage(pyromancer, raceModifier, oppMaxHP);
    }

    /**
     * Applies damage on rogue based on a percent of its min HP.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        float oppMaxHP = WizardConstants.DRAIN_OPP_MAX_PROCENT * (RogueConstants.BASE_HP
                + RogueConstants.LEVEL_HP * rogue.getLevel());

        float raceModifier = WizardConstants.DRAIN_MOD_R
                + battlefield.getOpponent(rogue).getAngelModifier();
        calculateTotalDamage(rogue, raceModifier, oppMaxHP);
    }

    /**
     * Applies damage on wizard based on a percent of its min HP;
     * Also calculates damage without race modifiers.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float procent = calculatePercent(wizard, 1);
        float unmodDamage;
        float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (WizardConstants.BASE_HP
                + WizardConstants.LEVEL_HP * wizard.getLevel());

        if (oppMax < wizard.getCurrentHP()) {
            unmodDamage = procent *  oppMax;
        } else {
            unmodDamage = procent *  wizard.getCurrentHP();
        }

        wizard.setUnmodifiedDamage(Math.round(unmodDamage));
        int damage = Math.round(unmodDamage * (WizardConstants.DRAIN_MOD_W
                + battlefield.getOpponent(wizard).getAngelModifier()));
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    /**
     * Applies damage on knight based on a percent of its min HP.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        float oppMaxHP = WizardConstants.DRAIN_OPP_MAX_PROCENT * (KnightConstants.BASE_HP
                + KnightConstants.LEVEL_HP * knight.getLevel());
        float raceModifier = WizardConstants.DRAIN_MOD_K
                + battlefield.getOpponent(knight).getAngelModifier();
        calculateTotalDamage(knight, raceModifier, oppMaxHP);
    }

    /**
     * Calculates the total damage by using the victim's opponent.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculatePercent(final Player victim, final float raceModifier) {
        Wizard assailant = (Wizard) battlefield.getOpponent(victim);

        float percent = WizardConstants.DRAIN_BASE_PROCENT
                + WizardConstants.DRAIN_LEVEL_PROCENT * assailant.getLevel();
        percent *= raceModifier;
        if (battlefield.getPlayerLot(assailant).getLandType() == WizardConstants.LAND_TYPE) {
            percent *= WizardConstants.LAND_TYPE_BONUS;
        }
        return percent;
    }

    public void calculateTotalDamage(final Player victim, final float raceModifier,
                                     final float oppMaxHP) {

        float percent = calculatePercent(victim, raceModifier);
        int damage;

        if (oppMaxHP < victim.getCurrentHP()) {
            damage = Math.round(percent *  oppMaxHP);
        } else {
            damage = Math.round(percent *  victim.getCurrentHP());
        }

        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }

}
