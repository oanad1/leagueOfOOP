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
        int damage = Math.round(CalculateRawDamage(pyromancer) * PyromancerConstants.FIREBLAST_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        int damage = Math.round(CalculateRawDamage(rogue) * PyromancerConstants.FIREBLAST_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    public void visit(Wizard wizard) {
        float unmodDamage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * PyromancerConstants.FIREBLAST_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    public void visit(Knight knight) {
        int damage = Math.round(CalculateRawDamage(knight) * PyromancerConstants.FIREBLAST_MOD_K);
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
