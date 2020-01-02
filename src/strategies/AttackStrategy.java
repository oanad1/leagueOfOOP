package strategies;

import players.*;

public class AttackStrategy implements Strategy {

    public void applyTactic(Player player, float fraction, float coefficient) {
        int hp = player.getCurrentHP();
        player.setCurrentHP(hp - Math.round(fraction * hp));
        player.setAngelModifier(player.getAngelModifier() + coefficient);
    }
}
