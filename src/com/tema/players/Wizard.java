package com.tema.players;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.WizardConstants;

public class Wizard extends Player implements Visitable {

    public Wizard(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
        this.setCurrentHP(WizardConstants.BASE_HP);
    }
}

