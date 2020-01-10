package strategies;

import players.Player;

/**
 * Concrete defense strategy implementation.
 * Decrease coefficients and increase hp.
 * **/
public final class DefenseStrategy implements Strategy {

    public void applyTactic(final Player player, final Float fraction, final Float coefficient) {
        int hp = player.getCurrentHP();
        player.setCurrentHP(hp + Math.round(fraction * hp));
        player.addBonusModifier(-coefficient);
    }
}
