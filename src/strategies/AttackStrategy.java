package strategies;

import players.Player;

/**
 * Concrete attack strategy implementation.
 * Increase coefficients and decrease hp.
 * **/
public final class AttackStrategy implements Strategy {

    public void applyTactic(final Player player, final Float fraction, final Float coefficient) {
        int hp = player.getCurrentHP();
        player.setCurrentHP(hp - Math.round(fraction * hp));
        player.addBonusModifier(coefficient);
    }
}
