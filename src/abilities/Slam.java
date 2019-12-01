package abilities;

import abilities.PlayerVisitor;
import constants.KnightConstants;
import constants.PyromancerConstants;
import input.Battlefield;
import players.*;

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
        int damage = Math.round(CalculateRawDamage(pyromancer) * KnightConstants.SLAM_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
        pyromancer.setImmobilized(1);
    }

    public void visit(Rogue rogue) {
        int damage = Math.round(CalculateRawDamage(rogue) * KnightConstants.SLAM_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
        rogue.setImmobilized(1);
    }

    public void visit(Wizard wizard) {
        float unmodDamage = CalculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * KnightConstants.SLAM_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
        wizard.setImmobilized(1);
    }

    public void visit(Knight knight) {
        int damage = Math.round(CalculateRawDamage(knight) * KnightConstants.SLAM_MOD_K);
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
