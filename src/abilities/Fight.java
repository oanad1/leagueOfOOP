package abilities;

import constants.*;
import input.Battlefield;
import main.PlayersVisitor;
import players.*;
import strategies.*;

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
     * Applies rogue Backstab and Paralysis ability.
     * @param rogue attacker
     */
    public void visit(final Rogue rogue) {

        int maxLevelHP = rogue.getLevel() * RogueConstants.LEVEL_HP + RogueConstants.BASE_HP;
        Context context = chooseStrategy(rogue,maxLevelHP,new StrategyDetails(rogue));
        context.executeStrategy(rogue);

        Visitable opponent = battlefield.getOpponent(rogue);
        opponent.accept(Backstab.getInstance());
        opponent.accept(Paralysis.getInstance());
    }

    /**
     * Applies pyromancer Fireblast and Ignite ability.
     * @param pyromancer attacker
     */
    public void visit(final Pyromancer pyromancer) {

        int maxLevelHP = pyromancer.getLevel() * PyromancerConstants.LEVEL_HP + PyromancerConstants.BASE_HP;
        Context context = chooseStrategy(pyromancer,maxLevelHP,new StrategyDetails(pyromancer));
        context.executeStrategy(pyromancer);

        Visitable opponent = battlefield.getOpponent(pyromancer);
        opponent.accept(Fireblast.getInstance());
        opponent.accept(Ignite.getInstance());
    }

    /**
     * Applies wizard Drain and Deflect ability.
     * @param wizard attacker
     */
    public void visit(final Wizard wizard) {

        int maxLevelHP = wizard.getLevel() * WizardConstants.LEVEL_HP + WizardConstants.BASE_HP;
        Context context = chooseStrategy(wizard,maxLevelHP,new StrategyDetails(wizard));
        context.executeStrategy(wizard);

        Visitable opponent = battlefield.getOpponent(wizard);
        opponent.accept(Drain.getInstance());
        opponent.accept(Deflect.getInstance());
    }

    /**
     * Applies knight Execute and Slam ability.
     * @param knight attacker
     */
    public void visit(final Knight knight) {

        int maxLevelHP = knight.getLevel() * KnightConstants.LEVEL_HP + KnightConstants.BASE_HP;
        Context context = chooseStrategy(knight,maxLevelHP,new StrategyDetails(knight));
        context.executeStrategy(knight);

        Visitable opponent = battlefield.getOpponent(knight);
        opponent.accept(Execute.getInstance());
        opponent.accept(Slam.getInstance());
    }

    public Context chooseStrategy(Player player, int maxLevelHP, StrategyDetails strategyDetails){

        if(player.getImmobilized() != 0) {
            return new Context(new DefenseStrategy(), 0, 0);
        }

        if (maxLevelHP * strategyDetails.getLowLimitFrac() < player.getCurrentHP()
                && player.getCurrentHP() < maxLevelHP * strategyDetails.getUpLimitFrac()) {

            return new Context(new AttackStrategy(), strategyDetails.getAttackHPFrac(),
                    strategyDetails.getAttackHPCoef());

        } else if(maxLevelHP * strategyDetails.getLowLimitFrac() > player.getCurrentHP()) {

            return new Context(new DefenseStrategy(), strategyDetails.getDefenseHPFrac(),
                    strategyDetails.getDefenseHPCoef());

        }
        return new Context(new DefenseStrategy(), 0, 0);
    }
}
