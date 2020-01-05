package main;

import abilities.Fight;
import input.Battlefield;
import input.GameInfo;
import observers.*;
import players.Player;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
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
            System.out.println("---------------Round--------------" + i);
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

                System.out.println('\n');
                System.out.println(player.getType()+player.getId() + " " + player.getOvertimeRounds() + player.isImmobilized());
                System.out.println('\n');

                //Subtract the overtime damage from the player's current hp
                Battlefield battlefield = Battlefield.getInstance();
                int hp = player.getCurrentHP() - player.getOvertimeDamage();
                player.setCurrentHP(hp);

                //Decrease the number of overtime rounds left
                player.setOvertimeRounds(player.getOvertimeRounds() - 1);

                //If a player dies from overtime damage, remove him from the map
                if (player.getCurrentHP() < 0) {
                  //  battlefield.removePlayer(player);
                    System.out.println('\n');
                    System.out.println(player.getType()+player.getId() + " DIED");
                    System.out.println('\n');

                    //Player is marked as dead
                    player.setDead(true);
                }
            }
        }

        //First, non-wizard players are allowed to apply their abilities;
        //Wizard players need to have their unmodified damage calculated before fighting
        for (Player player: players) {
            if (!player.isDead() && player.getPriority()) {
                player.accept(Fight.getInstance());
            }
        }

        //Now, wizard players fight
        for (Player player: players) {
            if (!player.isDead() && !player.getPriority()) {
                player.accept(Fight.getInstance());
            }
        }

        //The damage for each player is calculated
        for (Player player: players) {
            if (!player.isDead()) {
                PlayerAction.sufferDamage(player);
            }
        }

        //Check if anybody died during the round(except overtime damage players)

        for(Player player: players) {
            Player opponent = Battlefield.getInstance().getOpponent(player);
            if (opponent != null && !player.isDead() && player.getCurrentHP() < 0 && opponent.getCurrentHP() >= 0) {
                opponent.gainXP(player.getLevel());
            }
        }

        for (Player player: players) {
            player.eventMonitor.setPlayerMonitor(player);
            PlayerAction.checkDeath(player);
        }
            for(Player player: players){
                if (!player.isDead()) {
                    player.checkLevelUp();
                }
            player.eventMonitor.removeObserver(LevelUpObserver.getInstance());
            player.eventMonitor.setChange(player,fileWriter);
            player.eventMonitor.removeObserver(DeathObserver.getInstance());
            player.eventMonitor.addObserver(LevelUpObserver.getInstance());
            player.eventMonitor.setChange(player,fileWriter);
            player.eventMonitor.addObserver(DeathObserver.getInstance());

            System.out.println(player.getType() + player.getId() + " " + player.getCurrentHP() + " " + player.getLevel() +
                    " mofifier:   " + player.getAngelModifier() + "  Coords: " + player.getrowPos() + " " + player.getcolumnPos()
            + " " + player.isDead() + player.isImmobilized() + player.getOvertimeRounds() + " dam " + player.getOvertimeDamage() );

            if(player.isImmobilized() >  0) {
                player.setImmobilized(player.isImmobilized() - 1);
            }
            if(player.isToImobilize() == true) {
                player.setImmobilized(player.isImmobilized() + 1);
                player.setToImobilize(false);
            }
        }

        for (GameInfo.Angel angel: angels){
            EventMonitor angelMonitor = new EventMonitor();
            angelMonitor.setAngelMonitor(angel);
            angelMonitor.addObserver(AngelPositionObserver.getInstance());
            angelMonitor.addObserver(AngelActionObserver.getInstance());

            Battlefield.Lot lot = Battlefield.getInstance().getBattlefieldMat()[angel.getxCoord()][angel.getyCoord()];

            for(int i=0; i< lot.getOccupants().size(); i++){
                Player occupant = lot.getOccupants().get(i);
                occupant.eventMonitor.setPlayerMonitor(occupant);
                boolean dead = occupant.isDead();

                if(!occupant.isDead() || (occupant.isDead() && angel.getName().equals("Spawner"))) {
                    occupant.accept(angel.getAngelType());
                    occupant.checkLevelUp();
                }

                if(occupant.getCurrentHP() <=0 && !occupant.isDead()){
                    occupant.setDead(true);
                }

                if(dead != occupant.isDead()) {
                    angelMonitor.getPlayersLifeChange().add(occupant);
                }
            }
            angelMonitor.setChange(angel,fileWriter);
        }
    }
}
