package com.tema.abilities;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.KnightConstants;
import com.tema.constants.PyromancerConstants;
import com.tema.input.Battlefield;
import com.tema.players.*;

public class Slam implements PlayerVisitor {
    private static Slam instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Slam(){}
    public static Slam getInstance() {
        if(instance == null) {
            instance = new Slam();
        }
        return instance;
    }

    public void visit(Pyromancer pyromancer) {
        float damage = CalculateRawDamage(pyromancer) * KnightConstants.SLAM_MOD_P;
        Math.round(damage);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
        pyromancer.setImmobilized(1);
    }

    public void visit(Rogue rogue) {
        float damage = CalculateRawDamage(rogue) * KnightConstants.SLAM_MOD_R;
        Math.round(damage);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
        rogue.setImmobilized(1);
    }

    public void visit(Wizard wizard) {
        float damage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(damage));

        damage *= KnightConstants.SLAM_MOD_W;
        Math.round(damage);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
        wizard.setImmobilized(1);
    }

    public void visit(Knight knight) {
        float damage = CalculateRawDamage(knight) * KnightConstants.SLAM_MOD_K;
        Math.round(damage);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
        knight.setImmobilized(1);
    }

    public float CalculateRawDamage(Player victim){
        Player assailant = battlefield.GetOpponent(victim);

        float damage = KnightConstants.SLAM_BASE_DAMAGE +
                KnightConstants.SLAM_LEVEL_DAMAGE *assailant.getLevel();
        if(battlefield.getLot(assailant).getLandType() == KnightConstants.LAND_TYPE){
            damage *= KnightConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
