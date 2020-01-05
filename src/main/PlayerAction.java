package main;

import abilities.Fight;
import input.Battlefield;
import players.Player;

/**
 * Object containing methods equivalent to the actions each player is
 * executing during a round.
 */
public final class PlayerAction {

    private static Battlefield battlefield = Battlefield.getInstance();

    private PlayerAction() { }

    /**
     * Allows player to move on the map.
     * @param player player which is supposed to be moved on the map
     * @param move a character representing the player's movement
     * **/
    public static void move(final Player player, final char move) {

        //Get the player's current position
        int rowPos = player.getrowPos();
        int columnPos = player.getcolumnPos();

        //If the player is immobilized, decrease its number of rounds left
        if (player.isImmobilized() > 0) {
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
        if(player.getcolumnPos() < battlefield.getNrCols() && player.getrowPos() < battlefield.getNrRows() &&
        player.getrowPos() >= 0 && player.getcolumnPos() >= 0)
        battlefield.addPlayer(player);
    }


    /**
     * Allows player to fight its opponent.
     * @param player player who is supposed to fight
     * **/
    public static void fight(final Player player) {
         Battlefield.Lot lot = battlefield.getPlayerLot(player);

         //The fight happens only if the player is not alone on his Lot
         if ( lot != null && lot.getOccupants().size() > 1) {
             player.accept(Fight.getInstance());
         }
    }


    /**
     * Applies the accumulated damage to the player's HP.
     * @param player player for whom damage is applied
     * **/
    public static void sufferDamage(final Player player) {

        player.setCurrentHP(player.getCurrentHP() - player.getRoundDamage());
    }


    /**
     * Checks if the player is still alive after the last round.
     * @param player player to check for life
     * **/
    public static void checkDeath(final Player player) {

        //Ignore players who were already dead before
        if (player.getCurrentHP() < 0 && !player.isDead()) {

            //Player is removed from the map and marked as dead
            player.setDead(true);
            //battlefield.removePlayer(player);
        }
    }
}
