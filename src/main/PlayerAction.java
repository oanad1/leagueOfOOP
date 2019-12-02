package main;

import abilities.FightMode;
import constants.UniversalConstants;
import input.Battlefield;
import players.Player;

/**
 * Object containing methods equivalent to the actions each player is
 * executing during a round
 */
public final class PlayerAction {

    private static Battlefield battlefield = Battlefield.getInstance();

    private PlayerAction() { }

    /**
     * Allows player to move on the map
     * @param player player which is supposed to be moved on the map
     * @param move a character representing the player's movement
     * **/
    public static void move(final Player player, final char move) {

        //Get the player's current position
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        //If the player is immobilized, decrease its number of rounds left
        if (player.getImmobilized() != 0) {
            int imRounds = player.getImmobilized() - 1;
            player.setImmobilized(imRounds);
            return;
        }

        //Remove the player from its current position on the battlefield
        battlefield.removePlayer(player);

        //Recalculate the player's position
        switch (move) {
            case'U':
                player.setrowPos(rowPos - 1);
                break;
            case 'D':
                player.setrowPos(rowPos + 1);
                break;
            case 'L':
                player.setcolumnPos(columnPos - 1);
                break;
            case 'R':
                player.setcolumnPos(columnPos + 1);
                break;
            case '_':
                break;
            default:
                break;
        }

        //Add the player back on the battlefield
        battlefield.addPlayer(player);
    }


    /**
     * Allows player to fight its opponent
     * @param player player who is supposed to fight
     * **/
    public static void fight(final Player player) {
         Battlefield.Lot lot = battlefield.getLot(player);

         //The fight happens only if the player is not alone on his Lot
         if (lot.getOccupants().size() == 2) {
             player.accept(FightMode.getInstance());
         }
    }


    /**
     * Applies the accumulated damage to the player's HP
     * @param player player for whom damage is applied
     * **/
    public static void sufferDamage(final Player player) {

        player.setCurrentHP(player.getCurrentHP() - player.getRoundDamage());
    }


    /**
     * Checks if the player is still alive after the last round
     * @param player player to check for life
     * **/
    public static void checkDeath(final Player player) {

        //Ignore players who were already dead before
        if (player.getCurrentHP() <= 0 && player.getLevel() > -1) {
            Battlefield.Lot lot = battlefield.getLot(player);

            //If the player died as a result of a fight, its opponent receives XP
            if (lot.getOccupants().size() > 1) {
                Player opponent = battlefield.getOpponent(player);
                opponent.gainXP(player.getLevel());
            }

            //Player is removed from the map and marked as dead
            player.setLevel(UniversalConstants.DEAD);
            battlefield.removePlayer(player);
        }
    }
}
