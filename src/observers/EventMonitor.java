package observers;

import input.Battlefield;
import input.GameInfo;
import players.Player;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EventMonitor{

    private ArrayList<GreatMagicianObserver> observers = new ArrayList<GreatMagicianObserver>();
    private int level;
    private boolean death;
    private Player player;
    private GameInfo.Angel angel;
    private  ArrayList<Player> playersLifeChange;


    public EventMonitor(Player player){
        this.level = player.getLevel();
        this.death = player.isDead();
        this.player = player;
    }

    public EventMonitor(GameInfo.Angel angel){
        this.angel = angel;
        this.playersLifeChange = new ArrayList<>();
    }

    public void addObserver(GreatMagicianObserver observer){
        observers.add(observer);
    }

    public void alertObservers(FileWriter fileWriter) throws IOException {

        for(GreatMagicianObserver observer: observers){
            observer.update(this, fileWriter);
        }
    }

    public void setChange(Player player, FileWriter fileWriter) throws IOException {
        this.player = player;
        alertObservers(fileWriter);
    }

    public void setChange(GameInfo.Angel angel, FileWriter fileWriter) throws IOException {
        this.angel = angel;
        alertObservers(fileWriter);
    }

    public int getLevel() {
        return level;
    }

    public boolean getDeath() {
        return death;
    }

    public Player getPlayer() {
        return player;
    }

    public GameInfo.Angel getAngel() {
        return angel;
    }

    public ArrayList<Player> getPlayersLifeChange() {
        return playersLifeChange;
    }

}
