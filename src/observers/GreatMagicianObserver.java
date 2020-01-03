package observers;

import java.io.FileWriter;
import java.io.IOException;

public interface GreatMagicianObserver {

     void update(EventMonitor eventMonitor, FileWriter fileWriter) throws IOException;
}
