package main;

import input.Battlefield;
import input.GameInfo;
import observers.*;
import players.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Object used to mimic the game's functionality; Its methods are used
 * to play the rounds, one by one, and perform the corresponding actions
 * for the players.
 */

public final class GameSystem {

    /**
     * Method which calls a function performing the operations for each round.
     * @param gameInfo class which holds important information about the game
     * **/
    public void playGame(final GameInfo gameInfo, FileWriter fileWriter, ScoreOutput scoreOutput) throws IOException {

        for (int i = 0; i < gameInfo.getNrRounds(); i++) {
            fileWriter.write("~~ Round " + (i + 1) + " ~~" + '\n');
            String roundMoves = gameInfo.getMoves().get(i);
            playRound(gameInfo.getPlayers(), roundMoves, gameInfo.getAngels().get(i),fileWriter,scoreOutput);
            fileWriter.write('\n');
        }
    }

    /**
     * Method which applies actions for each player during a game round.
     * @param players list of players in their initial order
     * @param moves string containing all player moves for the current round
     * **/
    public void playRound(final ArrayList<Player> players, final String moves, final ArrayList<GameInfo.Angel> angels,
                          FileWriter fileWriter, ScoreOutput scoreOutput) throws IOException {

        //Move players which are still alive and reset their roundDamage
        for (Player player: players) {
            if (!player.isDead()) {
                player.setRoundDamage(0);
                PlayerAction.move(player, moves.charAt(players.indexOf(player)));
            }
        }

        //Check if an alive player suffers from an overtime damage
        for (Player player: players) {
            if (!player.isDead() && player.getOvertimeRounds() != 0) {

                //Subtract the overtime damage from the player's current hp
                Battlefield battlefield = Battlefield.getInstance();
                int hp = player.getCurrentHP() - player.getOvertimeDamage();
                player.setCurrentHP(hp);

                //Decrease the number of overtime rounds left
                player.setOvertimeRounds(player.getOvertimeRounds() - 1);

                //If the player has served its overtime, reset its overtime damage
                if (player.getOvertimeRounds() == 0) {
                    player.setOvertimeDamage(0);
                }

                //If a player dies from overtime damage, remove him from the map
                if (player.getCurrentHP() <= 0) {
                    battlefield.removePlayer(player);

                    //Player is marked as dead
                    player.setDead(true);
                }
            }
        }

        //First, non-wizard players are allowed to apply their abilities;
        //Wizard players need to have their unmodified damage calculated before fighting
        for (Player player: players) {
            if (!player.isDead() && player.getPriority()) {
                PlayerAction.fight(player);
            }
        }

        //Now, wizard players fight
        for (Player player: players) {
            if (!player.isDead() && !player.getPriority()) {
                PlayerAction.fight(player);
            }
        }

        //The damage for each player is calculated
        for (Player player: players) {
            if (!player.isDead()) {
                PlayerAction.sufferDamage(player);
            }
        }

        //Check if anybody died during the round(except overtime damage players)
        for (Player player: players) {
            EventMonitor playerMonitor = new EventMonitor(player);
            playerMonitor.addObserver(LevelUpObserver.getInstance());
            playerMonitor.addObserver(FightDeathObserver.getInstance());

            PlayerAction.checkDeath(player);
            if (!player.isDead()) {
                player.checkLevelUp();
        }
            playerMonitor.setChange(player,fileWriter);
      }

        for (GameInfo.Angel angel: angels){
            EventMonitor angelMonitor = new EventMonitor(angel);
            angelMonitor.addObserver(AngelPositionObserver.getInstance());
            angelMonitor.addObserver(AngelActionObserver.getInstance());

            Battlefield.Lot lot = Battlefield.getInstance().getBattlefieldMat()[angel.getxCoord()][angel.getyCoord()];
            for(Player occupant: lot.getOccupants()){
                boolean dead = occupant.isDead();

                occupant.accept(angel.getAngelType());

                if(dead != occupant.isDead()) {
                    angelMonitor.getPlayersLifeChange().add(occupant);
                }
            }
            angelMonitor.setChange(angel,fileWriter);
        }
    }
}
