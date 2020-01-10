package observers;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Interface for observers which are notified of changes
 * happening with players and angels.
 */
public interface GreatMagicianObserver {

     void update(EventMonitor eventMonitor, FileWriter fileWriter) throws IOException;
}
