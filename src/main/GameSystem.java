package main;

import abilities.Fight;
import input.Battlefield;
import input.GameInfo;
import observers.DeathObserver;
import observers.LevelUpObserver;
import observers.EventMonitor;
import observers.AngelActionObserver;
import observers.AngelPositionObserver;
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
     * @param fileWriter used to write results to the output file
     * **/
    public void playGame(final GameInfo gameInfo, final FileWriter fileWriter)
            throws IOException {

        for (int i = 0; i < gameInfo.getNrRounds(); i++) {
            fileWriter.write("~~ Round " + (i + 1) + " ~~" + '\n');
            String roundMoves = gameInfo.getMoves().get(i);
            playRound(gameInfo.getPlayers(), roundMoves, gameInfo.getAngels().get(i),
                    fileWriter);
            fileWriter.write('\n');
        }
    }

    /**
     * Method which applies actions for each player during a game round.
     * @param players list of players in their initial order
     * @param moves string containing all player moves for the current round
     * @param angels an ArrayList of angles in the current round
     * @param fileWriter used to write results to the output file
     * **/
    public void playRound(final ArrayList<Player> players, final String moves,
                          final ArrayList<GameInfo.Angel> angels, final FileWriter fileWriter)
            throws IOException {

        //Start monitoring events
        EventMonitor eventMonitor = new EventMonitor(players);
        eventMonitor.addObserver(DeathObserver.getInstance());
        eventMonitor.addObserver(LevelUpObserver.getInstance());

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
                int hp = player.getCurrentHP() - player.getOvertimeDamage();
                player.setCurrentHP(hp);

                //Decrease the number of overtime rounds left
                player.setOvertimeRounds(player.getOvertimeRounds() - 1);

                //If a player dies from overtime damage, mark him as dead
                if (player.getCurrentHP() < 0) {
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

        //Calculate the XP received by each player as a result of the fight
        for (Player player: players) {
            Player opponent = Battlefield.getInstance().getOpponent(player);
            if (opponent != null && !player.isDead() && player.getCurrentHP() < 0) {
                opponent.gainXP(player.getLevel());
            }
        }

        for (Player player: players) {

            //Capture each player's state before levelUp/death
            player.getPlayerMonitor().captureState();

            boolean lifeStatus = player.isDead();

            //Check if the player died during the round
            PlayerAction.checkDeath(player);

            //Check if the player can level up
            if (player.isDead() != lifeStatus || !player.isDead()) {
                player.checkLevelUp();
            }

            //If the player is immobilized, decrease its time left
            if (player.isImmobilized() >  0) {
                player.setImmobilized(player.isImmobilized() - 1);
            }

            //If the player is immobilized during the round, set its time
            if (player.isToImobilize()) {
                player.setImmobilized(player.isImmobilized() + 1);
                player.setToImobilize(false);
            }
        }

        //Update changes which happened as a result of the fights
        eventMonitor.setChange(fileWriter);

        //Reset the EventMonitor to observe angel related events
        eventMonitor.removeObserver(LevelUpObserver.getInstance());
        eventMonitor.removeObserver(DeathObserver.getInstance());
        eventMonitor.addObserver(AngelPositionObserver.getInstance());
        eventMonitor.addObserver(AngelActionObserver.getInstance());


        for (GameInfo.Angel angel: angels) {

            //Update the monitor to watch the current angel
            eventMonitor.setAngel(angel);

            //Go to the battlefield cell occupied by the angel
            Battlefield.Lot lot = Battlefield.getInstance().getBattlefieldMat()
                    [angel.getxCoord()][angel.getyCoord()];

            //Apply angel abilities on all the cell's occupants
            for (int i = 0; i < lot.getOccupants().size(); i++) {
                Player occupant = lot.getOccupants().get(i);

                //Capture the state of the occupant before the angel can apply its abilities
                occupant.getPlayerMonitor().captureState();

                boolean dead = occupant.isDead();

                if (!occupant.isDead() || (occupant.isDead()
                        && angel.getName().equals("Spawner"))) {

                    occupant.accept(angel.getAngelType());
                    occupant.checkLevelUp();

                }

                //Check if the occupant died as a result of the angel's actions
                if (occupant.getCurrentHP() <= 0 && !occupant.isDead()) {
                    occupant.setDead(true);
                }
            }
            //Update changes which happened as a result of the angel's actions
            eventMonitor.setChange(fileWriter);
        }
    }
}
