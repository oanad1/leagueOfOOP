package com.tema.players;

import com.tema.abilities.PlayerVisitor;

public class Pyromancer extends Player implements Visitable{

    public Pyromancer(int xCoord, int yCoord, int id) {
        super(xCoord, yCoord, id);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
    }
}
