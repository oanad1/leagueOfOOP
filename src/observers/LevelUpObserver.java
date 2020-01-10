package observers;

import players.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Observer for players - prints message in case a player
 * has changed its level.
 */
public final class LevelUpObserver implements GreatMagicianObserver {
    private static LevelUpObserver instance = null;

    private LevelUpObserver() { }
    public static LevelUpObserver getInstance() {
        if (instance == null) {
            instance = new LevelUpObserver();
        }
        return instance;
    }

        public void update(final EventMonitor eventMonitor, final FileWriter fileWriter)
                throws IOException {
            ArrayList<Player> players = eventMonitor.getPlayers();

            for (Player player : players) {
                int oldLevel = player.getPlayerMonitor().getLevel();
                while (oldLevel != player.getLevel()) {
                    oldLevel++;
                    fileWriter.write(player.getType() + " " + player.getId() + " reached level "
                            + oldLevel + '\n');
                }
            }
        }
    }

