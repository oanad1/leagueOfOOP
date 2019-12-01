package abilities;

import constants.PyromancerConstants;
import constants.RogueConstants;
import input.Battlefield;
import players.*;

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
        int damage = Math.round(CalculateRawDamage(pyromancer) * RogueConstants.PARALYSIS_MOD_P);
        pyromancer.setOvertimeDamage(damage);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        int damage = Math.round(CalculateRawDamage(rogue) * RogueConstants.PARALYSIS_MOD_R);
        rogue.setOvertimeDamage(damage);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage); System.out.println("P" +damage);
    }

    public void visit(Wizard wizard) {
        float unmodDamage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * RogueConstants.PARALYSIS_MOD_W);
        wizard.setOvertimeDamage(damage);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    public void visit(Knight knight) {
        int damage = Math.round(CalculateRawDamage(knight) * RogueConstants.PARALYSIS_MOD_K);
        knight.setOvertimeDamage(damage);
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
            victim.setOvertimeRounds(6);
        } else {
            victim.setImmobilized(3);
            victim.setOvertimeRounds(3);
        }
        return damage;
    }
}
