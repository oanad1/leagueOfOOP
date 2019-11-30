package com.tema.players;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.PyromancerConstants;

public class Pyromancer extends Player implements Visitable{

    public Pyromancer(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
        this.setCurrentHP(PyromancerConstants.BASE_HP);
    }
}
