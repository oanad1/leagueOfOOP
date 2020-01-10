package observers;

import input.GameInfo;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Observer for angels - prints message in case a new angel
 * appears on the battlefield.
 */
public final class AngelPositionObserver implements GreatMagicianObserver {

    private static AngelPositionObserver instance = null;

    private AngelPositionObserver() { }
    public static AngelPositionObserver getInstance() {
        if (instance == null) {
            instance = new AngelPositionObserver();
        }
        return instance;
    }

    public void update(final EventMonitor eventMonitor, final FileWriter fileWriter)
            throws IOException {
        GameInfo.Angel angel = eventMonitor.getAngel();

            fileWriter.write("Angel " + angel.getName() + " was spawned at "
                    + angel.getxCoord() + " "  +  angel.getyCoord() + '\n');

    }
}
