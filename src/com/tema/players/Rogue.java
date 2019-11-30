package com.tema.players;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.RogueConstants;

public class Rogue extends Player implements Visitable{

    public Rogue(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
        this.setCurrentHP(RogueConstants.BASE_HP);
    }
}
