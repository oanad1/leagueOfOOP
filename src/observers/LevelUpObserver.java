package observers;

import input.Battlefield;
import players.Player;

import java.io.FileWriter;
import java.io.IOException;

public class LevelUpObserver implements GreatMagicianObserver {
    private static LevelUpObserver instance = null;

    private LevelUpObserver() { }
    public static LevelUpObserver getInstance() {
        if (instance == null) {
            instance = new LevelUpObserver();
        }
        return instance;
    }

        public void update(EventMonitor eventMonitor, FileWriter fileWriter) throws IOException {
            Player player = eventMonitor.getPlayer();
            int oldLevel = player.getLevel();

            while(oldLevel != player.getLevel()) {
                fileWriter.write("Player " + player.getType() + " " + player.getId() + " reached level "
                        + oldLevel + '\n');
                oldLevel++;
            }
        }
    }

