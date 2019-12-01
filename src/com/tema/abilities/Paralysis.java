package com.tema.abilities;

import com.tema.constants.PyromancerConstants;
import com.tema.constants.RogueConstants;
import com.tema.input.Battlefield;
import com.tema.players.*;

public class Paralysis implements PlayerVisitor {
    private static Paralysis instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Paralysis(){}
    public static Paralysis getInstance() {
        if(instance == null) {
            instance = new Paralysis();
        }
        return instance;
    }

    public void visit(Pyromancer pyromancer) {
        float damage = CalculateRawDamage(pyromancer) * RogueConstants.PARALYSIS_MOD_P;
        Math.round(damage);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        float damage = CalculateRawDamage(rogue) * RogueConstants.PARALYSIS_MOD_R;
        Math.round(damage);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    public void visit(Wizard wizard) {
        float damage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(damage));

        damage *= RogueConstants.PARALYSIS_MOD_W;
        Math.round(damage);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    public void visit(Knight knight) {
        float damage = CalculateRawDamage(knight) * RogueConstants.PARALYSIS_MOD_K;
        Math.round(damage);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    public float CalculateRawDamage(Player victim){
        Rogue assailant = (Rogue)battlefield.GetOpponent(victim);

        float damage = RogueConstants.PARALYSIS_BASE_DAMAGE +
                RogueConstants.PARALYSIS_LEVEL_DAMAGE *assailant.getLevel();

        if(battlefield.getLot(assailant).getLandType() == RogueConstants.LAND_TYPE){
            damage *= RogueConstants.LAND_TYPE_BONUS;
            victim.setImmobilized(6);
        } else {
            victim.setImmobilized(3);
        }

        victim.setOvertimeDamage(Math.round(damage));
        return damage;
    }
}
