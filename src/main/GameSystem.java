package main;

import constants.UniversalConstants;
import input.Battlefield;
import input.GameInfo;
import players.Player;
import java.util.ArrayList;

/**
 * Object used to mimic the game's functionality; Its methods are used
 * to play the rounds, one by one, and perform the corresponding actions
 * for the players
 */

public final class GameSystem {

    /**
     * Method which calls a function performing the operations for each round
     * @param gameInfo class which holds important information about the game
     * **/
    public void playGame(final GameInfo gameInfo) {
        for (int i = 0; i < gameInfo.getNrRounds(); i++) {
            String roundMoves = gameInfo.getMoves().get(i);
                playRound(gameInfo.getPlayers(), roundMoves);
        }
    }

    /**
     * Method which applies actions for each player during a game round
     * @param players list of players in their initial order
     * @param moves string containing all player moves for the current round
     * **/
    public void playRound(final ArrayList<Player> players, final String moves) {

        //Move players which are still alive and reset their roundDamage
        for (Player player: players) {
            if (player.isAlive()) {
                player.setRoundDamage(0);
                PlayerAction.move(player, moves.charAt(player.getId()));
            }
        }

        //Check if an alive player suffers from an overtime damage
        for (Player player: players) {
            if (player.isAlive() && player.getOvertimeRounds() != 0) {

                //Subtract the overtime damage from the player's current damage
                Battlefield battlefield = Battlefield.getInstance();
                int damage = player.getCurrentHP() - player.getOvertimeDamage();
                player.setCurrentHP(damage);

                //Decrease the number of overtime rounds left
                player.setOvertimeRounds(player.getOvertimeRounds() - 1);

                //If the player has served its overtime, reset its overtime damage
                if (player.getOvertimeRounds() == 0) {
                    player.setOvertimeDamage(0);
                }

                //If a player dies from overtime damage, remove him from the map
                if (player.getCurrentHP() <= 0) {
                    battlefield.removePlayer(player);

                    //Player is marked as dead by setting its level to -1
                    player.setLevel(UniversalConstants.DEAD);
                }
            }
        }

        //First, non-wizard players are allowed to apply their abilities;
        //Wizard players need to have their unmodified damage calculated before fighting
        for (Player player: players) {
            if (player.isAlive() && player.getPriority()) {
                PlayerAction.fight(player);
            }
        }

        //Now, wizard players fight
        for (Player player: players) {
            if (player.isAlive() && !player.getPriority()) {
                PlayerAction.fight(player);
            }
        }

        //The damage for each player is calculated
        for (Player player: players) {
            if (player.isAlive()) {
                PlayerAction.sufferDamage(player);
            }
        }

        //Check if anybody died during the round(except overtime damage players)
        for (Player player: players) {
                PlayerAction.checkDeath(player);
        }

        //For each alive player, check if has enough XP to level up
        for (Player player: players) {
            if (player.isAlive()) {
                player.checkLevelUp();
            }
      }
    }
}
