package observers;

import input.GameInfo;
import players.Player;

import java.io.FileWriter;
import java.io.IOException;

public class AngelPositionObserver implements GreatMagicianObserver{

    private static AngelPositionObserver instance = null;

    private AngelPositionObserver() { }
    public static AngelPositionObserver getInstance() {
        if (instance == null) {
            instance = new AngelPositionObserver();
        }
        return instance;
    }

    public void update(EventMonitor eventMonitor, FileWriter fileWriter) throws IOException {
        GameInfo.Angel angel = eventMonitor.getAngel();

            fileWriter.write("Angel " + angel.getName() + " was spawned at "
                    + angel.getxCoord() + " "  +  angel.getyCoord() + '\n');

    }
}
