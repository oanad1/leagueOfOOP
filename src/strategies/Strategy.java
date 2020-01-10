package strategies;

import players.Player;

/**
 * Interface for Strategies.
 * **/
public interface Strategy {

    void applyTactic(Player player, Float fraction, Float coefficient);

}
