package abilities;

import constants.PyromancerConstants;
import input.Battlefield;
import players.*;

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
        int damage = Math.round(CalculateRawDamage(pyromancer) * PyromancerConstants.IGNITE_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(Rogue rogue) {
        int damage = Math.round(CalculateRawDamage(rogue) * PyromancerConstants.IGNITE_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    public void visit(Wizard wizard) {
        float unmodDamage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * PyromancerConstants.IGNITE_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    public void visit(Knight knight) {
        int damage = Math.round(CalculateRawDamage(knight) * PyromancerConstants.IGNITE_MOD_K);
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
