package com.tema.players;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.KnightConstants;
import com.tema.constants.WizardConstants;

public class Knight extends Player implements Visitable {

    public Knight(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(KnightConstants.BASE_HP);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
    }

    public void resetHP(){
        this.setCurrentHP(KnightConstants.BASE_HP + this.getLevel() * KnightConstants.LEVEL_HP);
    }
}
