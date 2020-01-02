package strategies;

import abilities.*;
import constants.RogueConstants;
import players.*;

public class Context {
    private Strategy strategy;
    private float coefficient;
    private float fraction;

    public Context(Strategy strategy, float hpFrac, float coef){
        this.coefficient = coef;
        this.fraction = hpFrac;
        this.strategy = strategy;
    }

    public void executeStrategy(Player player){
        strategy.applyTactic(player, fraction,coefficient);
    }
}
