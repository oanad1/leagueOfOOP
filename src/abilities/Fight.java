package abilities;

import constants.PyromancerConstants;
import constants.RogueConstants;
import constants.KnightConstants;
import constants.WizardConstants;
import input.Battlefield;
import main.PlayersVisitor;
import players.Player;
import players.Rogue;
import players.Pyromancer;
import players.Knight;
import players.Wizard;
import strategies.AttackStrategy;
import strategies.DefenseStrategy;
import strategies.StrategyDetails;
import strategies.Context;


/**
 * A class which implements a visitor pattern allowing players to
 * apply their abilities in a fight.
 */
public final class Fight implements PlayersVisitor {
    private static Fight instance = null;
    private Battlefield battlefield = Battlefield.getInstance();

    private Fight() { }
    public static Fight getInstance() {
        if (instance == null) {
            instance = new Fight();
        }
        return instance;
    }

    /**
     * Allows rogue to choose a strategy.
     * Applies Backstab and Paralysis ability.
     * @param rogue attacker
     */
    public void visit(final Rogue rogue) {

        int maxLevelHP = rogue.getLevel() * RogueConstants.LEVEL_HP + RogueConstants.BASE_HP;
        Context context = chooseStrategy(rogue, maxLevelHP, new StrategyDetails(rogue));
        context.executeStrategy(rogue);

        Player opponent = battlefield.getOpponent(rogue);
        if (opponent != null && !opponent.isDead()) {
            opponent.accept(Backstab.getInstance());
            opponent.accept(Paralysis.getInstance());
        }
    }

    /**
     * Allows pyromancer to choose a strategy.
     * Applies Fireblast and Ignite ability.
     * @param pyromancer attacker
     */
    public void visit(final Pyromancer pyromancer) {

        int maxLevelHP = pyromancer.getLevel() * PyromancerConstants.LEVEL_HP
                + PyromancerConstants.BASE_HP;
        Context context = chooseStrategy(pyromancer, maxLevelHP, new StrategyDetails(pyromancer));
        context.executeStrategy(pyromancer);

        Player opponent = battlefield.getOpponent(pyromancer);
        if (opponent != null && !opponent.isDead()) {
            opponent.accept(Fireblast.getInstance());
            opponent.accept(Ignite.getInstance());

        }
    }

    /**
     * Allows wizard to choose a strategy.
     * Applies Drain and Deflect ability.
     * @param wizard attacker
     */
    public void visit(final Wizard wizard) {

        int maxLevelHP = wizard.getLevel() * WizardConstants.LEVEL_HP + WizardConstants.BASE_HP;
        Context context = chooseStrategy(wizard, maxLevelHP, new StrategyDetails(wizard));
        context.executeStrategy(wizard);

        Player opponent = battlefield.getOpponent(wizard);
        if (opponent != null && !opponent.isDead()) {
            opponent.accept(Drain.getInstance());
            opponent.accept(Deflect.getInstance());
        }
    }

    /**
     * Allows knight to choose a strategy.
     * Applies Execute and Slam ability.
     * @param knight attacker
     */
    public void visit(final Knight knight) {
        int maxLevelHP = knight.getLevel() * KnightConstants.LEVEL_HP + KnightConstants.BASE_HP;
        Context context = chooseStrategy(knight, maxLevelHP, new StrategyDetails(knight));
        context.executeStrategy(knight);

        Player opponent = battlefield.getOpponent(knight);
        if (opponent != null && !opponent.isDead()) {
            opponent.accept(Execute.getInstance());
            opponent.accept(Slam.getInstance());
        }
    }

    /**
     * Allows an attacker to choose his strategy.
     * @param player attacker
     * @param maxLevelHP the maximum HP for the attacker's current level
     * @param strategyDetails data needed to apply each strategy
     * @return A context with a corresponding strategy
     */
    public Context chooseStrategy(final Player player, final int maxLevelHP,
                                  final StrategyDetails strategyDetails) {

        //Players who are immobilized don't choose a strategy
        if (player.isImmobilized() > 0) {
            return new Context(null, 0f, 0f);
        }

        if (Math.round(maxLevelHP * strategyDetails.getLowLimitFrac())
                < player.getCurrentHP() && player.getCurrentHP() < maxLevelHP
                * strategyDetails.getUpLimitFrac()) {

            //Chose the attack strategy
            return new Context(new AttackStrategy(), strategyDetails.getAttackHPFrac(),
                    strategyDetails.getAttackHPCoef());

        } else if (Math.round(maxLevelHP * strategyDetails.getLowLimitFrac())
                > player.getCurrentHP()) {

            //Chose the defense strategy
            return new Context(new DefenseStrategy(), strategyDetails.getDefenseHPFrac(),
                    strategyDetails.getDefenseHPCoef());

        }
        //Chose no strategy
        return new Context(null, 0f, 0f);
    }
}
