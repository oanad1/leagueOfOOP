package com.tema.players;

import com.tema.abilities.PlayerVisitor;

public class Knight extends Player implements Visitable {

    public Knight(int xCoord, int yCoord, int id) {
        super(xCoord, yCoord, id);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
    }
}
