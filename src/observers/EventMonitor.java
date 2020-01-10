package observers;

import input.GameInfo;
import players.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class used to keep track of events which happen during a round
 * and notify observers when changes occur.
 */
public final class EventMonitor {

    private ArrayList<GreatMagicianObserver> observers = new ArrayList<GreatMagicianObserver>();
    private ArrayList<Player> players;
    private GameInfo.Angel angel;

    public EventMonitor(final ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Method used to add a new observer.
     * @param observer a type of Great Magician observer
     */
    public void addObserver(final GreatMagicianObserver observer) {
        observers.add(observer);
    }

    /**
     * Method used to remove an observer.
     * @param observer a type of Great Magician observer
     */
    public void removeObserver(final GreatMagicianObserver observer) {
        observers.remove(observer);
    }


    /**
     * Method used to notify all observers when changes occur.
     * @param fileWriter used to output results
     */
    public void alertObservers(final FileWriter fileWriter) throws IOException {

        for (GreatMagicianObserver observer: observers) {
            observer.update(this, fileWriter);
        }
    }

    /**
     * Method used to notify a possible change.
     * @param fileWriter used to output results
     */
    public void setChange(final FileWriter fileWriter) throws IOException {
        alertObservers(fileWriter);
    }

    public GameInfo.Angel getAngel() {
        return angel;
    }

    public void setAngel(final GameInfo.Angel angel) {
        this.angel = angel;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(final ArrayList<Player> players) {
        this.players = players;
    }
}
