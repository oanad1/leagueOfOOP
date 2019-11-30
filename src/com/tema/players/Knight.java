package com.tema.players;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.KnightConstants;

public class Knight extends Player implements Visitable {

    public Knight(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
        this.setCurrentHP(KnightConstants.BASE_HP);
    }
}
