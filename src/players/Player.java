package players;

import abilities.PlayerVisitor;
import constants.UniversalConstants;

/**
 * An abstract class which provides common methods
 * and information for all players.
 */
public abstract class Player implements Visitable {
    private int rowPos;               //The player's row index in the map
    private int columnPos;            //The player's column index in the map
    private int id;                   //The player's id in the initial player array
    private int immobilized = 0;      //The number of rounds left in which the player is immobilized
    private int currentHP;            //The player's current HP
    private int currentXP;            //The player's current XP
    private int level;                //The player's current level
    private int roundDamage;          //The damage suffered in the current round
    private int overtimeRounds = 0;   //The number of overtime rounds the player has left
    private int overtimeDamage;       //The overtime damage
    private boolean priority;         //Set to true for all players except wizard

    public Player(final int rowPosition, final int columnPosition, final int id) {
        this.rowPos = rowPosition;
        this.columnPos = columnPosition;
        this.id = id;
        this.currentXP = 0;
        this.level = 0;
        this.roundDamage = 0;
        this.priority = true;
    }

    /**
     * Abstract method implementing the Visitor pattern.
     * @param visitor the type of action applied to the player
     * **/
    public abstract void accept(PlayerVisitor visitor);


    /**
     * Method to calculate the player's gained XP as result of a fight.
     * @param opponentLevel the level of the killed opponent
     * **/
    public final void gainXP(final int opponentLevel) {

        if (UniversalConstants.WINNER_XP_BASE - UniversalConstants.WINNER_XP_MULTIPLIER
                * (this.level - opponentLevel) > 0) {
            this.currentXP += UniversalConstants.WINNER_XP_BASE
                    - UniversalConstants.WINNER_XP_MULTIPLIER * (this.level - opponentLevel);
        }
    }

    /**
     * Abstract method to level up a player.
     * **/
    public abstract void checkLevelUp();


    /**
     * A method to check if a player is alive.
     * **/
    public final boolean isAlive() {
        if (this.level == -1) {
            return false;
        }
        return true;
    }


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

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getImmobilized() {
        return immobilized;
    }

    public final void setImmobilized(final int immobilized) {
        this.immobilized = immobilized;
    }

    public final int getOvertimeDamage() {
        return overtimeDamage;
    }

    public final void setOvertimeDamage(final int overtimeDamage) {
        this.overtimeDamage = overtimeDamage;
    }
}
