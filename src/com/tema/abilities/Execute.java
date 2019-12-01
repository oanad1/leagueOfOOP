package com.tema.abilities;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.KnightConstants;
import com.tema.constants.PyromancerConstants;
import com.tema.constants.RogueConstants;
import com.tema.constants.WizardConstants;
import com.tema.input.Battlefield;
import com.tema.players.*;

public class Execute implements PlayerVisitor {
    private static Execute instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Execute(){}
    public static Execute getInstance() {
        if(instance == null) {
            instance = new Execute();
        }
        return instance;
    }

    public void visit(Pyromancer pyromancer) {
        float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * pyromancer.getLevel();

        if(levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

     float HPLimit = KnightConstants.EXECUTE_INSTANT_PERCENT* (PyromancerConstants.BASE_HP +
             pyromancer.getLevel()*PyromancerConstants.LEVEL_HP);
     HPLimit += levelProcent;
     Math.round(HPLimit);

     if(pyromancer.getCurrentHP() < HPLimit){
         battlefield.RemovePlayer(pyromancer);
         pyromancer.setLevel(PyromancerConstants.DEAD);
         return;
     }

        float damage = CalculateRawDamage(pyromancer) * KnightConstants.EXECUTE_MOD_P;
        Math.round(damage);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }


    public void visit(Rogue rogue) {
        float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * rogue.getLevel();

        if(levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

        float HPLimit = KnightConstants.EXECUTE_INSTANT_PERCENT* (RogueConstants.BASE_HP +
                rogue.getLevel()*RogueConstants.LEVEL_HP);
        HPLimit += levelProcent;
        Math.round(HPLimit);

        if(rogue.getCurrentHP() < HPLimit){
            battlefield.RemovePlayer(rogue);
            rogue.setLevel(RogueConstants.DEAD);
            return;
        }

        float damage = CalculateRawDamage(rogue) * KnightConstants.EXECUTE_MOD_R;
        Math.round(damage);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }


    public void visit(Wizard wizard) {
        float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * wizard.getLevel();

        if(levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

        float HPLimit = KnightConstants.EXECUTE_INSTANT_PERCENT* (WizardConstants.BASE_HP +
                wizard.getLevel()*WizardConstants.LEVEL_HP);
        HPLimit += levelProcent;
        Math.round(HPLimit);

        if(wizard.getCurrentHP() < HPLimit){
            battlefield.RemovePlayer(wizard);
            wizard.setLevel(WizardConstants.DEAD);
            return;
        }

        float damage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(damage));

        damage *= KnightConstants.EXECUTE_MOD_W;
        Math.round(damage);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }


    public void visit(Knight knight) {
        float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * knight.getLevel();

        if(levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

        float HPLimit = KnightConstants.EXECUTE_INSTANT_PERCENT* (KnightConstants.BASE_HP +
                knight.getLevel()*KnightConstants.LEVEL_HP);
        HPLimit += levelProcent;
        Math.round(HPLimit);

        if(knight.getCurrentHP() < HPLimit){
            battlefield.RemovePlayer(knight);
            knight.setLevel(KnightConstants.DEAD);
            return;
        }

        float damage = CalculateRawDamage(knight) * KnightConstants.EXECUTE_MOD_P;
        Math.round(damage);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    public float CalculateRawDamage(Player victim){
        Player assailant = battlefield.GetOpponent(victim);

        float damage = KnightConstants.EXECUTE_BASE_DAMAGE +
                KnightConstants.EXECUTE_LEVEL_DAMAGE *assailant.getLevel();
        if(battlefield.getLot(assailant).getLandType() == KnightConstants.LAND_TYPE){
            damage *= KnightConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
