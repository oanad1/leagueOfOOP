package observers;

import input.Battlefield;
import input.GameInfo;
import players.Player;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Observer for angels and players - prints message in case a player
 * is affected by an angel.
 */
public final class AngelActionObserver implements GreatMagicianObserver {

    private static AngelActionObserver instance = null;

    private AngelActionObserver() { }
    public static AngelActionObserver getInstance() {
        if (instance == null) {
            instance = new AngelActionObserver();
        }
        return instance;
    }

    public void update(final EventMonitor eventMonitor, final FileWriter fileWriter)
            throws IOException {
        GameInfo.Angel angel = eventMonitor.getAngel();
        Battlefield.Lot lot = Battlefield.getInstance().getBattlefieldMat()
                [angel.getxCoord()][angel.getyCoord()];

        for (Player player: lot.getOccupants()) {
            if ((!player.getPlayerMonitor().getDeath() && !angel.getName().equals("Spawner"))
                    || (angel.getName().equals("Spawner")
                    && player.getPlayerMonitor().getDeath())) {

                switch (angel.getName()) {

                        //Bad angels
                    case "DarkAngel":
                    case "Dracula":
                    case "TheDoomer":
                        fileWriter.write(angel.getName() + " hit " + player.getType() + " "
                                + player.getId() + '\n');
                        break;

                        //Good Angels
                    case "DamageAngel":
                    case "GoodBoy":
                    case "LevelUpAngel":
                    case "LifeGiver":
                    case "SmallAngel":
                    case "Spawner":
                    case "XPAngel":
                        fileWriter.write(angel.getName() + " helped " + player.getType() + " "
                                + player.getId() + '\n');
                        break;

                    default:
                        break;
                }
            }
            //Check if the player's life status has been affected by the angel
            if (player.getPlayerMonitor().getDeath() != player.isDead()) {
                if (player.isDead()) {
                    fileWriter.write("Player " + player.getType() + " " + player.getId()
                            + " was killed by an angel" + '\n');
                } else {
                    fileWriter.write("Player " + player.getType() + " " + player.getId()
                            + " was brought to life by an angel" + '\n');
                }
            }

            //Check if the player has leveled up due to the angel
            if (player.getPlayerMonitor().getLevel() != player.getLevel()) {
                int oldLevel = player.getPlayerMonitor().getLevel();
                while (oldLevel != player.getLevel()) {
                    oldLevel++;
                    fileWriter.write(player.getType() + " " + player.getId() + " reached level "
                            + oldLevel + '\n');
                }
            }
        }

    }
}
