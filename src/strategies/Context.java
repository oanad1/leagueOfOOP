package strategies;

import players.Player;


/**
 * Class used to execute a Strategy.
 * **/
public final class Context {
    private Strategy strategy;
    private Float coefficient;
    private Float fraction;

    public Context(final Strategy strategy, final Float hpFrac, final Float coef) {
        this.coefficient = coef;
        this.fraction = hpFrac;
        this.strategy = strategy;
    }

    public void executeStrategy(final Player player) {
        if (strategy != null) {
            strategy.applyTactic(player, fraction, coefficient);
        }
    }
}
