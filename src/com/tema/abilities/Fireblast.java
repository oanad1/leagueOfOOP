package com.tema.abilities;

import com.tema.constants.PyromancerConstants;
import com.tema.input.Battlefield;
import com.tema.players.*;

public class Fireblast implements PlayerVisitor {
    private static Fireblast instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    public static Fireblast getInstance() {
        if(instance == null) {
            instance = new Fireblast();
        }
        return instance;
    }

    public void visit(Pyromancer pyromancer) {
        float damage = CalculateRawDamage(pyromancer) * PyromancerConstants.FIREBLAST_MOD_P;
        Math.round(damage);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        float damage = CalculateRawDamage(rogue) * PyromancerConstants.FIREBLAST_MOD_R;
        Math.round(damage);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    public void visit(Wizard wizard) {
        float damage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(damage));

        damage *= PyromancerConstants.FIREBLAST_MOD_W;
        Math.round(damage);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    public void visit(Knight knight) {
        float damage = CalculateRawDamage(knight) * PyromancerConstants.FIREBLAST_MOD_K;
        Math.round(damage);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    public float CalculateRawDamage(Player victim){
        Player assailant = battlefield.GetOpponent(victim);
        float damage = PyromancerConstants.FIREBLAST_BASE_DAMAGE +
                PyromancerConstants.FIREBLAST_LEVEL_DAMAGE *assailant.getLevel();
        if(battlefield.getLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE){
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
