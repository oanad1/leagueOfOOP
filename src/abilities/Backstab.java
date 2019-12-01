package abilities;

import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import input.Battlefield;
import players.*;

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
        int damage = Math.round(CalculateRawDamage(pyromancer) * RogueConstants.BACKSTAB_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        int damage = Math.round(CalculateRawDamage(rogue) * RogueConstants.BACKSTAB_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);System.out.println(damage);
    }

    public void visit(Wizard wizard) {
        float unmodDamage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * RogueConstants.BACKSTAB_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    public void visit(Knight knight) {
        int damage = Math.round(CalculateRawDamage(knight) * RogueConstants.BACKSTAB_MOD_K);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    public float CalculateRawDamage(Player victim){
        Rogue assailant = (Rogue) battlefield.GetOpponent(victim);

        float damage = RogueConstants.BACKSTAB_BASE_DAMAGE +
                RogueConstants.BACKSTAB_LEVEL_DAMAGE *assailant.getLevel();

        if(assailant.getNrBackstabHits() % RogueConstants.BACKSTAB_NR_HITS == 0){
            if(battlefield.getLot(assailant).getLandType() == RogueConstants.LAND_TYPE) {
                damage *= RogueConstants.BACKSTAB_CRITICAL;
                assailant.setNrBackstabHits(assailant.getNrBackstabHits() + 1);
            } else {
                assailant.setNrBackstabHits(0);
            }
        } else {
            assailant.setNrBackstabHits(assailant.getNrBackstabHits() + 1);
        }

        if(battlefield.getLot(assailant).getLandType() == RogueConstants.LAND_TYPE){
            damage *= RogueConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
