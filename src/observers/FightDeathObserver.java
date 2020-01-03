package observers;

import input.Battlefield;
import players.Player;

import java.io.FileWriter;
import java.io.IOException;

public class FightDeathObserver implements GreatMagicianObserver {

    private static FightDeathObserver instance = null;

    private FightDeathObserver() { }
    public static FightDeathObserver getInstance() {
        if (instance == null) {
            instance = new FightDeathObserver();
        }
        return instance;
    }

    public void update(EventMonitor eventMonitor, FileWriter fileWriter) throws IOException {
        Player player = eventMonitor.getPlayer();
        Player opponent = Battlefield.getInstance().getOpponent(player);

       if(eventMonitor.getDeath() == false && player.isDead() == true) {
           fileWriter.write("Player " + player.getType() + " " + player.getId() + " was killed by "
                   + opponent.getType() + " " + opponent.getId() + '\n');
       }
    }
}
