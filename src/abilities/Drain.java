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
 * Drain ability specific to Wizard players.
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
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        Float oppMaxHP = WizardConstants.DRAIN_OPP_MAX_PROCENT * (PyromancerConstants.BASE_HP
                + PyromancerConstants.LEVEL_HP * pyromancer.getLevel());

        Float raceModifier = WizardConstants.DRAIN_MOD_P;
        calculateTotalDamage(pyromancer, raceModifier, oppMaxHP);
    }

    /**
     * Applies damage on rogue based on a percent of its min HP.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        Float oppMaxHP = WizardConstants.DRAIN_OPP_MAX_PROCENT * (RogueConstants.BASE_HP
                + RogueConstants.LEVEL_HP * rogue.getLevel());

        Float raceModifier = WizardConstants.DRAIN_MOD_R;
        calculateTotalDamage(rogue, raceModifier, oppMaxHP);
    }

    /**
     * Applies damage on wizard based on a percent of its min HP;
     * Calculates unmodified damage for wizard.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        Float procent = calculatePercent(wizard, 1f);
        Float unmodDamage;
        Float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (WizardConstants.BASE_HP
                + WizardConstants.LEVEL_HP * wizard.getLevel());

        if (oppMax < wizard.getCurrentHP()) {
            unmodDamage = procent *  oppMax;
        } else {
            unmodDamage = procent *  wizard.getCurrentHP();
        }

        wizard.setUnmodifiedDamage(Math.round(unmodDamage));
        Float raceModifier = WizardConstants.DRAIN_MOD_W;
        for (Float bonusMod: battlefield.getOpponent(wizard).getBonusModifiers()) {
            raceModifier += bonusMod;
        }
        int damage = Math.round(unmodDamage * raceModifier);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    /**
     * Applies damage on knight based on a percent of its min HP.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        Float oppMaxHP = WizardConstants.DRAIN_OPP_MAX_PROCENT * (KnightConstants.BASE_HP
                + KnightConstants.LEVEL_HP * knight.getLevel());
        Float raceModifier = WizardConstants.DRAIN_MOD_K;
        calculateTotalDamage(knight, raceModifier, oppMaxHP);
    }

    /**
     * Calculates the total damage by using the victim's opponent.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public Float calculatePercent(final Player victim, final Float raceModifier) {
        Wizard assailant = (Wizard) battlefield.getOpponent(victim);

        Float percent = WizardConstants.DRAIN_BASE_PROCENT
                + WizardConstants.DRAIN_LEVEL_PROCENT * assailant.getLevel();
        percent *= raceModifier;
        if (battlefield.getPlayerLot(assailant).getLandType() == WizardConstants.LAND_TYPE) {
            percent *= WizardConstants.LAND_TYPE_BONUS;
        }
        return percent;
    }

    /**
     * Sets the total damage with race modifiers.
     * @param victim player who is attacked
     * @param raceMod race modifier specific to the victim
     * @param oppMaxHP 0.3 * victim max HP
     * @return total damage with race modifiers
     */
    public void calculateTotalDamage(final Player victim, final Float raceMod,
                                     final Float oppMaxHP) {
        Float raceModifier = raceMod;

        //Add angel and strategy bonuses to the land modifiers
        for (Float bonusMod: battlefield.getOpponent(victim).getBonusModifiers()) {
            raceModifier += bonusMod;
        }

        Float percent = calculatePercent(victim, raceModifier);
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
