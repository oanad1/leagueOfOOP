package com.tema.players;

import com.tema.abilities.PlayerVisitor;

public class Player {
    private int rowPos;
    private int columnPos;
    private int id;
    private boolean immobilized = false;

    public Player(int rowPos, int columnPos, int id){
        this.rowPos = rowPos;
        this.columnPos = columnPos;
        this.id = id;
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

    public boolean isImmobilized() {
        return immobilized;
    }

    public void setImmobilized(boolean imobilizedState) {
        this.immobilized = imobilizedState;
    }
}
