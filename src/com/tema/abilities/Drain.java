package com.tema.abilities;

import com.tema.constants.KnightConstants;
import com.tema.constants.PyromancerConstants;
import com.tema.constants.RogueConstants;
import com.tema.constants.WizardConstants;
import com.tema.input.Battlefield;
import com.tema.players.*;

public class Drain implements PlayerVisitor {
    private static Drain instance = null;
    private Battlefield battlefield = Battlefield.getInstance();


    private Drain(){}
    public static Drain getInstance() {
        if(instance == null) {
            instance = new Drain();
        }
        return instance;
    }


    public void visit(Pyromancer pyromancer) {
        float procent = CalculateRawDamage(pyromancer) * WizardConstants.DRAIN_MOD_P;
        float damage;
        float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (PyromancerConstants.BASE_HP + PyromancerConstants.LEVEL_HP *
                pyromancer.getLevel());

        if(oppMax < pyromancer.getCurrentHP()){
            damage = procent *  oppMax;
        } else {
            damage = procent *  pyromancer.getCurrentHP();
        }

        Math.round(damage);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        float procent = CalculateRawDamage(rogue) * WizardConstants.DRAIN_MOD_R;
        float damage;
        float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (RogueConstants.BASE_HP + RogueConstants.LEVEL_HP *
                rogue.getLevel());

        if(oppMax < rogue.getCurrentHP()){
            damage = procent *  oppMax;
        } else {
            damage = procent *  rogue.getCurrentHP();
        }

        Math.round(damage);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    public void visit(Wizard wizard) {
        float procent = CalculateRawDamage(wizard) * WizardConstants.DRAIN_MOD_W;
        float damage;
        float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (WizardConstants.BASE_HP + WizardConstants.LEVEL_HP *
                wizard.getLevel());

        if(oppMax < wizard.getCurrentHP()){
            damage = procent *  oppMax;
        } else {
            damage = procent *  wizard.getCurrentHP();
        }

        Math.round(damage);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    public void visit(Knight knight) {
        float procent = CalculateRawDamage(knight) * WizardConstants.DRAIN_MOD_K;
        float damage;
        float oppMax = WizardConstants.DRAIN_OPP_MAX_PROCENT * (KnightConstants.BASE_HP + KnightConstants.LEVEL_HP *
                knight.getLevel());

        if(oppMax < knight.getCurrentHP()){
            damage = procent *  oppMax;
        } else {
            damage = procent *  knight.getCurrentHP();
        }

        Math.round(damage);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    public float CalculateRawDamage(Player victim){
        Wizard assailant = (Wizard)battlefield.GetOpponent(victim);

        float damage = WizardConstants.DRAIN_BASE_PROCENT +
                WizardConstants.DRAIN_LEVEL_PROCENT * assailant.getLevel();
        if(battlefield.getLot(assailant).getLandType() == WizardConstants.LAND_TYPE){
            damage *= WizardConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
