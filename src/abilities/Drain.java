package abilities;

import constants.KnightConstants;
import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import input.Battlefield;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;

/**
 * Drain ability specific to the Wizard players
 * Singleton class implementing the PlayerVisitor interface
 */
public final class Drain implements PlayerVisitor {
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
     * Applies damage on pyromancer based on a procent of its min HP
     * @param pyromancer victimAppApplyly
     */
    public void visit(final Pyromancer pyromancer) {
        float procent = calculateRawDamage(pyromancer) * WizardConstants.DRAIN_MOD_P;
        int damage;
        float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (PyromancerConstants.BASE_HP
                + PyromancerConstants.LEVEL_HP * pyromancer.getLevel());

        if (oppMax < pyromancer.getCurrentHP()) {
            damage = Math.round(procent *  oppMax);
        } else {
            damage = Math.round(procent *  pyromancer.getCurrentHP());
        }

        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    /**
     * Applies damage on rogue based on a percent of its min HP
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        float procent = calculateRawDamage(rogue) * WizardConstants.DRAIN_MOD_R;
        int damage;
        float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (RogueConstants.BASE_HP
                + RogueConstants.LEVEL_HP * rogue.getLevel());

        if (oppMax < rogue.getCurrentHP()) {
            damage = Math.round(procent *  oppMax);
        } else {
            damage = Math.round(procent *  rogue.getCurrentHP());
        }

        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    /**
     * Applies damage on wizard based on a percent of its min HP;
     * Also calculates damage without race modifiers.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float procent = calculateRawDamage(wizard);
        float unmodDamage;
        float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (WizardConstants.BASE_HP
                + WizardConstants.LEVEL_HP * wizard.getLevel());

        if (oppMax < wizard.getCurrentHP()) {
            unmodDamage = procent *  oppMax;
        } else {
            unmodDamage = procent *  wizard.getCurrentHP();
        }

        wizard.setUnmodifiedDamage(Math.round(unmodDamage));
        int damage = Math.round(unmodDamage * WizardConstants.DRAIN_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    /**
     * Applies damage on knight based on a percent of its min HP
     * @param knight victim
     */
    public void visit(final Knight knight) {
        float procent = calculateRawDamage(knight) * WizardConstants.DRAIN_MOD_K;
        int damage;
        float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (KnightConstants.BASE_HP
                + KnightConstants.LEVEL_HP * knight.getLevel());

        if (oppMax < knight.getCurrentHP()) {
            damage = Math.round(procent *  oppMax);
        } else {
            damage = Math.round(procent *  knight.getCurrentHP());
        }

        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    /**
     * Calculates the total damage by using the victim's opponent
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Wizard assailant = (Wizard) battlefield.getOpponent(victim);

        float damage = WizardConstants.DRAIN_BASE_PROCENT
                + WizardConstants.DRAIN_LEVEL_PROCENT * assailant.getLevel();
        if (battlefield.getLot(assailant).getLandType() == WizardConstants.LAND_TYPE) {
            damage *= WizardConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
