package observers;

import input.Battlefield;
import players.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Observer for players - prints message in case a player
 * has died as a result of a fight.
 */
public final class DeathObserver implements GreatMagicianObserver {

    private static DeathObserver instance = null;

    private DeathObserver() { }
    public static DeathObserver getInstance() {
        if (instance == null) {
            instance = new DeathObserver();
        }
        return instance;
    }

    public void update(final EventMonitor eventMonitor, final FileWriter fileWriter)
            throws IOException {

        ArrayList<Player> players = eventMonitor.getPlayers();

        for (Player player : players) {
            Player opponent = Battlefield.getInstance().getOpponent(player);

            //Check if the player was killed by another alive player
            if ((!player.isDead() && opponent != null && !opponent.getPlayerMonitor().getDeath()
                    && opponent.isDead())) {

                fileWriter.write("Player " + opponent.getType() + " " + opponent.getId()
                        + " was killed by " + player.getType() + " " + player.getId() + '\n');
            }

            //Check if the player and its opponent have killed each other
            if (player.isDead() && opponent != null && opponent.isDead() && player.getId()
                    < opponent.getId() && !opponent.getPlayerMonitor().getDeath()
                    && !player.getPlayerMonitor().getDeath()) {

                fileWriter.write("Player " + opponent.getType() + " " + opponent.getId()
                        + " was killed by " + player.getType() + " " + player.getId() + '\n');
                fileWriter.write("Player " + player.getType() + " " + player.getId()
                        + " was killed by " + opponent.getType() + " " + opponent.getId() + '\n');

            }
        }
    }
}
