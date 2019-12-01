package com.tema.abilities;

import com.tema.constants.PyromancerConstants;
import com.tema.constants.RogueConstants;
import com.tema.constants.WizardConstants;
import com.tema.input.Battlefield;
import com.tema.players.*;

public class Backstab implements PlayerVisitor {
    private static Backstab instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Backstab(){}
    public static Backstab getInstance() {
        if(instance == null) {
            instance = new Backstab();
        }
        return instance;
    }

    public void visit(Pyromancer pyromancer) {
        float damage = CalculateRawDamage(pyromancer) * RogueConstants.BACKSTAB_MOD_P;
        Math.round(damage);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        float damage = CalculateRawDamage(rogue) * RogueConstants.BACKSTAB_MOD_R;
        Math.round(damage);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    public void visit(Wizard wizard) {
        float damage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(damage));

        damage *= RogueConstants.BACKSTAB_MOD_W;
        Math.round(damage);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    public void visit(Knight knight) {
        float damage = CalculateRawDamage(knight) * RogueConstants.BACKSTAB_MOD_K;
        Math.round(damage);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    public float CalculateRawDamage(Player victim){
        Rogue assailant = (Rogue) battlefield.GetOpponent(victim);

        float damage = RogueConstants.BACKSTAB_BASE_DAMAGE +
                RogueConstants.BACKSTAB_LEVEL_DAMAGE *assailant.getLevel();

        if(assailant.getNrBackstabHits() == RogueConstants.BACKSTAB_NR_HITS){
            if(battlefield.getLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {
                damage *= RogueConstants.BACKSTAB_CRITICAL;
            }
            assailant.setNrBackstabHits(0);
        }

        if(battlefield.getLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE){
            damage *= RogueConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
