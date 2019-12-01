package players;

import abilities.PlayerVisitor;

public abstract class Player implements Visitable{
    private int rowPos;
    private int columnPos;
    private int id;
    private int immobilized = 0;
    private int currentHP;
    private int currentXP;
    private int level;
    private int roundDamage;
    private int overtimeRounds = 0;
    private int overtimeDamage;
    private boolean priority;

    public Player(int rowPos, int columnPos, int id){
        this.rowPos = rowPos;
        this.columnPos = columnPos;
        this.id = id;
        this.currentXP = 0;
        this.level = 0;
        this.roundDamage = 0;
        this.priority = true;
    }
    public abstract void accept(PlayerVisitor visitor);

    public void LevelUp(int opponentLevel) {

        if(200 - 40 * (this.level - opponentLevel) > 0){
            this.currentXP += 200 - 40 * (this.level - opponentLevel);
        }

        this.level = (this.currentXP - 250)/50 + 1;
    }

    public abstract void resetHP();

    public boolean isAlive(){
        if(this.level == -1){
            return false;
        }
        return true;
    }

    public int getOvertimeRounds() {
        return overtimeRounds;
    }

    public void setOvertimeRounds(int overtimeRounds) {
        this.overtimeRounds = overtimeRounds;
    }

    public boolean getPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public int getRoundDamage() {
        return roundDamage;
    }

    public void setRoundDamage(int roundDamage) {
        this.roundDamage = roundDamage;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getCurrentXP() {
        return currentXP;
    }

    public void setCurrentXP(int currentXP) {
        this.currentXP = currentXP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getrowPos() {
        return rowPos;
    }

    public void setrowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public int getcolumnPos() {
        return columnPos;
    }

    public void setcolumnPos(int columnPos) {
        this.columnPos = columnPos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImmobilized() {
        return immobilized;
    }

    public void setImmobilized(int immobilized) {
        this.immobilized = immobilized;
    }

    public int getOvertimeDamage() {
        return overtimeDamage;
    }

    public void setOvertimeDamage(int overtimeDamage) {
        this.overtimeDamage = overtimeDamage;
    }
}
