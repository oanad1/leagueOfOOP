package abilities;

import constants.WizardConstants;
import input.Battlefield;
import players.Player;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;
import players.Knight;

public final class Deflect implements PlayerVisitor {
    private static Deflect instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Deflect() { }
    public static Deflect getInstance() {
        if (instance == null) {
            instance = new Deflect();
        }
        return instance;
    }

    public void visit(final Pyromancer pyromancer) {
        int damage = Math.round(calculateRawDamage(pyromancer) * WizardConstants.DEFLECT_MOD_P);
        damage += pyromancer.getRoundDamage();
        pyromancer.setRoundDamage(damage);
    }

    public void visit(final Rogue rogue) {
        int damage = Math.round(calculateRawDamage(rogue) * WizardConstants.DEFLECT_MOD_R);
        damage += rogue.getRoundDamage();
        rogue.setRoundDamage(damage);
    }

    public void visit(final Wizard wizard) {
        return;
    }

    public void visit(final Knight knight) {
        int damage = Math.round(calculateRawDamage(knight) * WizardConstants.DEFLECT_MOD_K);
        damage += knight.getRoundDamage();
        knight.setRoundDamage(damage);
    }

    public float calculateRawDamage(final Player victim) {
        Wizard assailant = (Wizard) battlefield.getOpponent(victim);

        float percent = WizardConstants.DEFLECT_BASE_PROCENT
                + WizardConstants.DEFLECT_LEVEL_PROCENT * assailant.getLevel();
        if (percent > WizardConstants.DEFLECT_MAX_PROCENT) {
            percent = WizardConstants.DEFLECT_MAX_PROCENT;
        }
        float damage = percent * assailant.getUnmodifiedDamage();

        if (battlefield.getLot(assailant).getLandType() == WizardConstants.LAND_TYPE) {
            damage *= WizardConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }
}
