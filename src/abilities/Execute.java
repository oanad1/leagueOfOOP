package abilities;

import abilities.PlayerVisitor;
import constants.KnightConstants;
import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import input.Battlefield;
import players.*;

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

     int HPLimit = Math.round(KnightConstants.EXECUTE_INSTANT_PERCENT* (PyromancerConstants.BASE_HP +
             pyromancer.getLevel()*PyromancerConstants.LEVEL_HP) + levelProcent);

     if(pyromancer.getCurrentHP() < HPLimit){
         pyromancer.setRoundDamage(pyromancer.getCurrentHP());
         return;
     }

        int damage = Math.round(CalculateRawDamage(pyromancer) * KnightConstants.EXECUTE_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }


    public void visit(Rogue rogue) {
        float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * rogue.getLevel();

        if(levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

        int HPLimit = Math.round(KnightConstants.EXECUTE_INSTANT_PERCENT* (RogueConstants.BASE_HP +
                rogue.getLevel()*RogueConstants.LEVEL_HP) + levelProcent);

        if(rogue.getCurrentHP() < HPLimit){
            rogue.setRoundDamage(rogue.getCurrentHP());
            return;
        }

        int damage = Math.round(CalculateRawDamage(rogue) * KnightConstants.EXECUTE_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }


    public void visit(Wizard wizard) {
        float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * wizard.getLevel();

        if(levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

        int HPLimit = Math.round(KnightConstants.EXECUTE_INSTANT_PERCENT* (WizardConstants.BASE_HP +
                wizard.getLevel()*WizardConstants.LEVEL_HP) + levelProcent);

        if(wizard.getCurrentHP() < HPLimit){
            wizard.setUnmodifiedDamage(wizard.getCurrentHP());
            wizard.setRoundDamage(wizard.getCurrentHP());
            return;
        }

        float unmodDamage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * KnightConstants.EXECUTE_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }


    public void visit(Knight knight) {
        float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * knight.getLevel();

        if(levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

        int HPLimit = Math.round(KnightConstants.EXECUTE_INSTANT_PERCENT* (KnightConstants.BASE_HP +
                knight.getLevel()*KnightConstants.LEVEL_HP) + levelProcent);

        if(knight.getCurrentHP() < HPLimit){
            knight.setRoundDamage(knight.getCurrentHP());
            return;
        }

        int damage = Math.round(CalculateRawDamage(knight));
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
