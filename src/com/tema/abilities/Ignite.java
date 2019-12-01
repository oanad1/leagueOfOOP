package com.tema.abilities;

import com.tema.constants.PyromancerConstants;
import com.tema.input.Battlefield;
import com.tema.players.*;

public class Ignite implements PlayerVisitor {
    private static Ignite instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Ignite(){}
    public static Ignite getInstance() {
        if(instance == null) {
            instance = new Ignite();
        }
        return instance;
    }

    public void visit(Pyromancer pyromancer) {
        float damage = CalculateRawDamage(pyromancer) * PyromancerConstants.IGNITE_MOD_P;
        Math.round(damage);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        float damage = CalculateRawDamage(rogue) * PyromancerConstants.IGNITE_MOD_R;
        Math.round(damage);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    public void visit(Wizard wizard) {
        float damage = CalculateRawDamage(wizard) * PyromancerConstants.IGNITE_MOD_W;
        Math.round(damage);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    public void visit(Knight knight) {
        float damage = CalculateRawDamage(knight) * PyromancerConstants.IGNITE_MOD_K;
        Math.round(damage);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    public float CalculateRawDamage(Player victim){
        Player assailant = battlefield.GetOpponent(victim);
        FightMode fightMode = FightMode.getInstance();
        float damage;

        if(fightMode.getRoundNr() % 3 == 0) {
            damage = PyromancerConstants.IGNITE_BASE_DAMAGE +
                    PyromancerConstants.IGNITE_LEVEL_DAMAGE *assailant.getLevel();
        } else {
            damage = PyromancerConstants.IGNITE_SMALL_BASE_DAMAGE +
                    PyromancerConstants.IGNITE_SMALL_LEVEL_DAMAGE *assailant.getLevel();
        }

        if(battlefield.getLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE){
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
