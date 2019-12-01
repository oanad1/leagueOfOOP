package abilities;

import constants.KnightConstants;
import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import input.Battlefield;
import players.*;

public class Deflect implements PlayerVisitor {
    private static Deflect instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Deflect(){}
    public static Deflect getInstance() {
        if(instance == null) {
            instance = new Deflect();
        }
        return instance;
    }

    public void visit(Pyromancer pyromancer) {
        int damage = Math.round(CalculateRawDamage(pyromancer) * WizardConstants.DEFLECT_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        int damage = Math.round(CalculateRawDamage(rogue) * WizardConstants.DEFLECT_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    public void visit(Wizard wizard) {
        return;
    }

    public void visit(Knight knight) {
        int damage = Math.round(CalculateRawDamage(knight) * WizardConstants.DEFLECT_MOD_K);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    public float CalculateRawDamage(Player victim){
        Wizard assailant = (Wizard) battlefield.GetOpponent(victim);

        float percent = WizardConstants.DEFLECT_BASE_PROCENT +          //TODO: typo in procent
                WizardConstants.DEFLECT_LEVEL_PROCENT *assailant.getLevel();
        if(percent > WizardConstants.DEFLECT_MAX_PROCENT) {
            percent = WizardConstants.DEFLECT_MAX_PROCENT;
        }
        float damage = percent * assailant.getUnmodifiedDamage();

        if(battlefield.getLot(assailant).getLandType() == WizardConstants.LAND_TYPE){
            damage *= WizardConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
