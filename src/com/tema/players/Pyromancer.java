package com.tema.players;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.KnightConstants;
import com.tema.constants.PyromancerConstants;
import com.tema.constants.WizardConstants;

public class Pyromancer extends Player implements Visitable{

    public Pyromancer(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(PyromancerConstants.BASE_HP);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
    }

    public void resetHP(){
        this.setCurrentHP(PyromancerConstants.BASE_HP + this.getLevel() * PyromancerConstants.LEVEL_HP);
    }
}
