package players;

import main.PlayersVisitor;
import angels.AngelVisitor;
import constants.PlayerConstants;

import java.util.ArrayList;

/**
 * An abstract class which provides common methods
 * and information for all players.
 */
public abstract class Player implements Visitable {
    private int rowPos;               //The player's row index in the map
    private int columnPos;            //The player's column index in the map
    private int id;                   //The player's id
    private int immobilized;          //Number of immobilized rounds
    private boolean toImobilize;      //Mark used to immobilize the player at the end of the round
    private int currentHP;            //The player's current HP
    private int currentXP;            //The player's current XP
    private int level;                //The player's current level
    private int roundDamage;          //The damage suffered in the current round
    private int overtimeRounds = 0;   //The number of overtime rounds the player has left
    private int overtimeDamage;       //The overtime damage
    private boolean priority;         //Set to true for all players except wizard
    private boolean dead;             //The player's life status

    private ArrayList<Float> bonusModifiers = new ArrayList<>();    //Angel and strategy modifiers
    private PlayerMonitor playerMonitor = new PlayerMonitor(this);  //Monitors the previous state

    public Player(final int rowPosition, final int columnPosition, final int id) {
        this.rowPos = rowPosition;
        this.columnPos = columnPosition;
        this.id = id;
        this.currentXP = 0;
        this.level = 0;
        this.roundDamage = 0;
        this.priority = true;
        this.dead = false;
    }

    /**
     * Abstract methods implementing the Visitor pattern.
     * @param visitor the type of action applied to the player
     * **/
    public abstract void accept(PlayersVisitor visitor);

    public abstract void accept(AngelVisitor visitor);


    /**
     * Method to calculate the player's gained XP as result of a fight.
     * @param opponentLevel the level of the killed opponent
     * **/
    public final void gainXP(final int opponentLevel) {

        if (PlayerConstants.WINNER_XP_BASE - PlayerConstants.WINNER_XP_MULTIPLIER
                * (this.level - opponentLevel) > 0) {
            this.currentXP += PlayerConstants.WINNER_XP_BASE
                    - PlayerConstants.WINNER_XP_MULTIPLIER * (this.level - opponentLevel);
        }
    }

    /**
     * Abstract method to level up a player.
     * **/
    public abstract void checkLevelUp();

    public final int getOvertimeRounds() {
        return overtimeRounds;
    }

    public final void setOvertimeRounds(final int overtimeRounds) {
        this.overtimeRounds = overtimeRounds;
    }

    public final boolean getPriority() {
        return priority;
    }

    public final void setPriority(final boolean priority) {
        this.priority = priority;
    }

    public final int getRoundDamage() {
        return roundDamage;
    }

    public final void setRoundDamage(final int roundDamage) {
        this.roundDamage = roundDamage;
    }

    public final int getCurrentHP() {
        return currentHP;
    }

    public final void setCurrentHP(final int currentHP) {
        this.currentHP = currentHP;
    }

    public final int getCurrentXP() {
        return currentXP;
    }

    public final void setCurrentXP(final int currentXP) {
        this.currentXP = currentXP;
    }

    public final int getLevel() {
        return level;
    }

    public final void setLevel(final int level) {
        this.level = level;
    }

    public final int getrowPos() {
        return rowPos;
    }

    public final void setrowPos(final int rowPosition) {
        this.rowPos = rowPosition;
    }

    public final int getcolumnPos() {
        return columnPos;
    }

    public final void setcolumnPos(final int columnPosition) {
        this.columnPos = columnPosition;
    }

    public final int getId() {
        return id;
    }

    public final int isImmobilized() {
        return immobilized;
    }

    public final void setImmobilized(final int immobilized) {
        this.immobilized = immobilized;
    }

    public final boolean isToImobilize() {
        return toImobilize;
    }

    public final void setToImobilize(final boolean toImobilize) {
        this.toImobilize = toImobilize;
    }

    public final int getOvertimeDamage() {
        return overtimeDamage;
    }

    public final void setOvertimeDamage(final int overtimeDamage) {
        this.overtimeDamage = overtimeDamage;
    }

    public final ArrayList<Float> getBonusModifiers() {
        return bonusModifiers;
    }

    public final void addBonusModifier(final Float bonusModifier) {
        bonusModifiers.add(bonusModifier);
    }

    public final boolean isDead() {
        return dead;
    }

    public final void setDead(final boolean dead) {
        this.dead = dead;
    }

    public abstract String getType();

    public final PlayerMonitor getPlayerMonitor() {
        return playerMonitor;
    }

    /**
     * Internal class used to capture the player's level and life status
     * before a change takes place.
     */
    public final class PlayerMonitor {
        private int level;            //Last captured level for the player
        private boolean death;        //Last captured life status for the player
        private Player player;

       public PlayerMonitor(final Player player) {
           this.player = player;
       }

        public void captureState() {
            this.death = player.isDead();
            this.level = player.getLevel();
        }

        public int getLevel() {
            return level;
        }

        public boolean getDeath() {
            return death;
        }
    }
}
