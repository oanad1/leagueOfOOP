package strategies;

import constants.KnightConstants;
import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;


public class StrategyDetails {
    private float lowLimitFrac;
    private float upLimitFrac;
    private float attackHPFrac;
    private float attackHPCoef;
    private float defenseHPFrac;
    private float defenseHPCoef;

    public StrategyDetails(Rogue rogue){
        this.lowLimitFrac = RogueConstants.LOW_LIMIT_FRAC;
        this.upLimitFrac = RogueConstants.UP_LIMIT_FRAC;
        this.attackHPFrac = RogueConstants.ATTACK_HP_FRAC;
        this.attackHPCoef = RogueConstants.ATTACK_COEF;
        this.defenseHPFrac = RogueConstants.DEFENSE_HP_FRAC;
        this.defenseHPCoef = RogueConstants.DEFENSE_COEF;
    }

    public StrategyDetails(Pyromancer pyromancer){
        this.lowLimitFrac = PyromancerConstants.LOW_LIMIT_FRAC;
        this.upLimitFrac = PyromancerConstants.UP_LIMIT_FRAC;
        this.attackHPFrac = PyromancerConstants.ATTACK_HP_FRAC;
        this.attackHPCoef = PyromancerConstants.ATTACK_COEF;
        this.defenseHPFrac = PyromancerConstants.DEFENSE_HP_FRAC;
        this.defenseHPCoef = PyromancerConstants.DEFENSE_COEF;
    }

    public StrategyDetails(Knight knight){
        this.lowLimitFrac = KnightConstants.LOW_LIMIT_FRAC;
        this.upLimitFrac = KnightConstants.UP_LIMIT_FRAC;
        this.attackHPFrac = KnightConstants.ATTACK_HP_FRAC;
        this.attackHPCoef = KnightConstants.ATTACK_COEF;
        this.defenseHPFrac = KnightConstants.DEFENSE_HP_FRAC;
        this.defenseHPCoef = KnightConstants.DEFENSE_COEF;
    }

    public StrategyDetails (Wizard wizard){
        this.lowLimitFrac = WizardConstants.LOW_LIMIT_FRAC;
        this.upLimitFrac = WizardConstants.UP_LIMIT_FRAC;
        this.attackHPFrac = WizardConstants.ATTACK_HP_FRAC;
        this.attackHPCoef = WizardConstants.ATTACK_COEF;
        this.defenseHPFrac = WizardConstants.DEFENSE_HP_FRAC;
        this.defenseHPCoef = WizardConstants.DEFENSE_COEF;
    }

    public float getLowLimitFrac() {
        return lowLimitFrac;
    }

    public void setLowLimitFrac(float lowLimitFrac) {
        this.lowLimitFrac = lowLimitFrac;
    }

    public float getUpLimitFrac() {
        return upLimitFrac;
    }

    public void setUpLimitFrac(float upLimitFrac) {
        this.upLimitFrac = upLimitFrac;
    }

    public float getAttackHPFrac() {
        return attackHPFrac;
    }

    public void setAttackHPFrac(float attackHPFrac) {
        this.attackHPFrac = attackHPFrac;
    }

    public float getDefenseHPFrac() {
        return defenseHPFrac;
    }

    public void setDefenseHPFrac(float defenseHPFrac) {
        this.defenseHPFrac = defenseHPFrac;
    }

    public float getDefenseHPCoef() {
        return defenseHPCoef;
    }

    public void setDefenseHPCoef(float defenseHPCoef) {
        this.defenseHPCoef = defenseHPCoef;
    }

    public float getAttackHPCoef() {
        return attackHPCoef;
    }

    public void setAttackHPCoef(float attackHPCoef) {
        this.attackHPCoef = attackHPCoef;
    }
}
