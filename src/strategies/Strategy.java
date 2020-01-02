package strategies;

import players.Player;

public interface Strategy {

    public void applyTactic(Player player, float fraction, float coefficient);

}
