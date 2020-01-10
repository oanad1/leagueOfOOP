package strategies;

import constants.KnightConstants;
import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

/**
 * Helper class used to keep all strategy information
 * for a specific type of player.
 * **/
public final class StrategyDetails {
    private Float lowLimitFrac;
    private Float upLimitFrac;
    private Float attackHPFrac;
    private Float attackHPCoef;
    private Float defenseHPFrac;
    private Float defenseHPCoef;

    public StrategyDetails(final Rogue rogue) {
        this.lowLimitFrac = RogueConstants.LOW_LIMIT_FRAC;
        this.upLimitFrac = RogueConstants.UP_LIMIT_FRAC;
        this.attackHPFrac = RogueConstants.ATTACK_HP_FRAC;
        this.attackHPCoef = RogueConstants.ATTACK_COEF;
        this.defenseHPFrac = RogueConstants.DEFENSE_HP_FRAC;
        this.defenseHPCoef = RogueConstants.DEFENSE_COEF;
    }

    public StrategyDetails(final Pyromancer pyromancer) {
        this.lowLimitFrac = PyromancerConstants.LOW_LIMIT_FRAC;
        this.upLimitFrac = PyromancerConstants.UP_LIMIT_FRAC;
        this.attackHPFrac = PyromancerConstants.ATTACK_HP_FRAC;
        this.attackHPCoef = PyromancerConstants.ATTACK_COEF;
        this.defenseHPFrac = PyromancerConstants.DEFENSE_HP_FRAC;
        this.defenseHPCoef = PyromancerConstants.DEFENSE_COEF;
    }

    public StrategyDetails(final Knight knight) {
        this.lowLimitFrac = KnightConstants.LOW_LIMIT_FRAC;
        this.upLimitFrac = KnightConstants.UP_LIMIT_FRAC;
        this.attackHPFrac = KnightConstants.ATTACK_HP_FRAC;
        this.attackHPCoef = KnightConstants.ATTACK_COEF;
        this.defenseHPFrac = KnightConstants.DEFENSE_HP_FRAC;
        this.defenseHPCoef = KnightConstants.DEFENSE_COEF;
    }

    public StrategyDetails(final Wizard wizard) {
        this.lowLimitFrac = WizardConstants.LOW_LIMIT_FRAC;
        this.upLimitFrac = WizardConstants.UP_LIMIT_FRAC;
        this.attackHPFrac = WizardConstants.ATTACK_HP_FRAC;
        this.attackHPCoef = WizardConstants.ATTACK_COEF;
        this.defenseHPFrac = WizardConstants.DEFENSE_HP_FRAC;
        this.defenseHPCoef = WizardConstants.DEFENSE_COEF;
    }

    public Float getLowLimitFrac() {
        return lowLimitFrac;
    }

    public Float getUpLimitFrac() {
        return upLimitFrac;
    }

    public Float getAttackHPFrac() {
        return attackHPFrac;
    }

    public Float getDefenseHPFrac() {
        return defenseHPFrac;
    }

    public Float getDefenseHPCoef() {
        return defenseHPCoef;
    }

    public Float getAttackHPCoef() {
        return attackHPCoef;
    }
}
