package abilities;

import constants.KnightConstants;
import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.WizardConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.*;


/**
 * Execute ability specific to the Knight players.
 * Singleton class implementing the PlayerVisitor interface.
 */
public final class Execute implements PlayersVisitor {
    private static Execute instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Execute() { }
    public static Execute getInstance() {
        if (instance == null) {
            instance = new Execute();
        }
        return instance;
    }

    /**
     * Calculates HP limit and determines if instant kill can be applied;
     * If not, applies normal damage on pyromancer.
     * @param pyromancer victim
     */
    public void visit(final Pyromancer pyromancer) {
        float raceModifier = KnightConstants.EXECUTE_MOD_P + battlefield.getOpponent(pyromancer).getAngelModifier();
        int maxLevelHP = PyromancerConstants.BASE_HP + pyromancer.getLevel() * PyromancerConstants.LEVEL_HP;

        calculateTotalDamage(pyromancer, raceModifier, maxLevelHP);
    }


    /**
     * Calculates HP limit and determines if instant kill can be applied;
     * If not, applies normal damage on rogue.
     * @param rogue victim
     */
    public void visit(final Rogue rogue) {
        float raceModifier = KnightConstants.EXECUTE_MOD_R + battlefield.getOpponent(rogue).getAngelModifier();
        int maxLevelHP = RogueConstants.BASE_HP + rogue.getLevel() * RogueConstants.LEVEL_HP;

        calculateTotalDamage(rogue, raceModifier, maxLevelHP);
    }

    /**
     * Calculates HP limit and determines if instant kill can be applied;
     * If not, applies normal damage on wizard.
     * Also calculates damage without race modifiers.
     * @param wizard victim
     */
    public void visit(final Wizard wizard) {
        float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * wizard.getLevel();

        if (levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

        int hpLimit = Math.round(KnightConstants.EXECUTE_INSTANT_PERCENT
                * (WizardConstants.BASE_HP + wizard.getLevel()
                * WizardConstants.LEVEL_HP) + levelProcent);

        float unmodDamage = calculateRawDamage(wizard);
        wizard.setUnmodifiedDamage(Math.round(unmodDamage));

        if (wizard.getCurrentHP() < hpLimit) {
            wizard.setRoundDamage(wizard.getCurrentHP());
            return;
        }

        int damage = Math.round(unmodDamage * (KnightConstants.EXECUTE_MOD_W
                + battlefield.getOpponent(wizard).getAngelModifier()));
        damage += wizard.getRoundDamage();
        wizard.setRoundDamage(damage);
    }

    /**
     * Calculates HP limit and determines if instant kill can be applied;
     * If not, applies normal damage on knight.
     * @param knight victim
     */
    public void visit(final Knight knight) {
        float raceModifier = 1;
        int maxLevelHP = KnightConstants.BASE_HP + knight.getLevel() * KnightConstants.LEVEL_HP;
        calculateTotalDamage(knight,raceModifier,maxLevelHP);
    }

    /**
     * Calculates the total damage by using the victim's opponent.
     * @param victim player who is attacked
     * @return total damage without race modifiers
     */
    public float calculateRawDamage(final Player victim) {
        Player assailant = battlefield.getOpponent(victim);

        float damage = KnightConstants.EXECUTE_BASE_DAMAGE
               + KnightConstants.EXECUTE_LEVEL_DAMAGE * assailant.getLevel();
        if (battlefield.getPlayerLot(assailant).getLandType() == KnightConstants.LAND_TYPE) {
            damage *= KnightConstants.LAND_TYPE_BONUS;
        }
        return damage;
    }

    public void calculateTotalDamage(final Player victim, final float raceModifier, final int maxLevelHP) {
        float levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_PERCENT * victim.getLevel();

        if (levelProcent > KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT) {
            levelProcent = KnightConstants.EXECUTE_INSTANT_LEVEL_MAX_PERCENT;
        }

        int hpLimit = Math.round(KnightConstants.EXECUTE_INSTANT_PERCENT * maxLevelHP + levelProcent);

        if (victim.getCurrentHP() < hpLimit) {
            victim.setRoundDamage(victim.getCurrentHP());
            return;
        }

        int damage = Math.round(calculateRawDamage(victim) * raceModifier);
        damage += victim.getRoundDamage();
        victim.setRoundDamage(damage);
    }
}
