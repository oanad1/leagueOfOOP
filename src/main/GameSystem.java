package main;

import input.Battlefield;
import input.GameInfo;
import players.Player;
import java.util.ArrayList;

public final class GameSystem {

    public void playGame(final GameInfo gameInfo) {
        for (int i = 0; i < gameInfo.getNrRounds(); i++) {
            String roundMoves = gameInfo.getMoves().get(i);
                playRound(gameInfo.getPlayers(), roundMoves);
        }
    }

    public void playRound(final ArrayList<Player> players, final String moves) {
        for (Player player: players) {
            if (player.isAlive()) {
                player.setRoundDamage(0);
                PlayerAction.move(player, moves.charAt(player.getId()));
            }
        }

        for (Player player: players) {
            if (player.isAlive() && player.getOvertimeRounds() != 0) {

                Battlefield battlefield = Battlefield.getInstance();
                int damage = player.getCurrentHP() - player.getOvertimeDamage();

                player.setCurrentHP(damage);
                player.setOvertimeRounds(player.getOvertimeRounds() - 1);

                if (player.getOvertimeRounds() == 0) {
                    player.setOvertimeDamage(0);
                }

                if (player.getCurrentHP() <= 0) {
                    battlefield.removePlayer(player);
                    player.setLevel(-1);
                }
            }
        }
        for (Player player: players) {
            if (player.isAlive() && player.getPriority()) {
                PlayerAction.fight(player);
            }
        }
        for (Player player: players) {
            if (player.isAlive() && !player.getPriority()) {
                PlayerAction.fight(player);
            }
        }
        for (Player player: players) {
            if (player.isAlive()) {
                PlayerAction.sufferDamage(player);
            }
        }
        for (Player player: players) {
                PlayerAction.checkDeath(player);
        }
        for (Player player: players) {
            if (player.isAlive()) {
                player.checkLevelUp();
            }
      }
    }
}
