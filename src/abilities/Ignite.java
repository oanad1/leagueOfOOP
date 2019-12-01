package abilities;

import constants.PyromancerConstants;
import input.Battlefield;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;

public final class Ignite implements PlayerVisitor {
    private static Ignite instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Ignite() { }
    public static Ignite getInstance() {
        if (instance == null) {
            instance = new Ignite();
        }
        return instance;
    }

    public void visit(final Pyromancer pyromancer) {
        int damage = Math.round(calculateRawDamage(pyromancer) * PyromancerConstants.IGNITE_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);

        damage = Math.round(calculateOvertime(pyromancer) * PyromancerConstants.IGNITE_MOD_P);
        pyromancer.setOvertimeDamage(damage);
        pyromancer.setOvertimeRounds(2);
    }

    public void visit(final Rogue rogue) {
        int damage = Math.round(calculateRawDamage(rogue) * PyromancerConstants.IGNITE_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);

        damage = Math.round(calculateOvertime(rogue) * PyromancerConstants.IGNITE_MOD_R);
        rogue.setOvertimeDamage(damage);
        rogue.setOvertimeRounds(2);
    }

    public void visit(final Wizard wizard) {
        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(wizard.getUnmodifiedDamage() + Math.round(unmodDamage));

        int damage = Math.round(unmodDamage * PyromancerConstants.IGNITE_MOD_W);
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);

        damage = Math.round(calculateOvertime(wizard) * PyromancerConstants.IGNITE_MOD_W);
        wizard.setOvertimeDamage(damage);
        wizard.setOvertimeRounds(2);
    }

    public void visit(final Knight knight) {
        int damage = Math.round(calculateRawDamage(knight) * PyromancerConstants.IGNITE_MOD_K);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);

        damage = Math.round(calculateOvertime(knight) * PyromancerConstants.IGNITE_MOD_K);
        knight.setOvertimeDamage(damage);
        knight.setOvertimeRounds(2);
    }

    public float calculateRawDamage(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);
        FightMode fightMode = FightMode.getInstance();
        float damage;

            damage = PyromancerConstants.IGNITE_BASE_DAMAGE
                    + PyromancerConstants.IGNITE_LEVEL_DAMAGE * assailant.getLevel();

        if (battlefield.getLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE) {
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    public float calculateOvertime(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);
        FightMode fightMode = FightMode.getInstance();
        float damage;

        damage = PyromancerConstants.IGNITE_SMALL_BASE_DAMAGE
                + PyromancerConstants.IGNITE_SMALL_LEVEL_DAMAGE * assailant.getLevel();

        if (battlefield.getLot(assailant).getLandType() == PyromancerConstants.LAND_TYPE) {
            damage *= PyromancerConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
