package observers;

import input.Battlefield;
import input.GameInfo;
import main.PlayersVisitor;
import players.Player;

import java.io.FileWriter;
import java.io.IOException;

public class AngelActionObserver implements GreatMagicianObserver {

    private static AngelActionObserver instance = null;

    private AngelActionObserver() { }
    public static AngelActionObserver getInstance() {
        if (instance == null) {
            instance = new AngelActionObserver();
        }
        return instance;
    }

    public void update(EventMonitor eventMonitor, FileWriter fileWriter) throws IOException {
        GameInfo.Angel angel = eventMonitor.getAngel();
        Battlefield.Lot lot = Battlefield.getInstance().getBattlefieldMat()[angel.getxCoord()][angel.getyCoord()];

        for(Player player: lot.getOccupants()) {
            switch (angel.getName()) {
                case "DarkAngel":
                case "Dracula":
                case "TheDoomer":
                    fileWriter.write(angel.getName() + " hit " + player.getType() + " "
                            + player.getId() + '\n');
                    break;

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
            if(eventMonitor.getPlayersLifeChange().contains(player)) {
                if(player.isDead() == true) {
                    fileWriter.write("Player " + player.getType() + " " + player.getId()
                            + " was killed by an angel" + '\n');
                } else {
                    fileWriter.write("Player " + player.getType() + " " + player.getId()
                            + " was brought to life by an angel" + '\n');
                }
            }
        }
    }
}
