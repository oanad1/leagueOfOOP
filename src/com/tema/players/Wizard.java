package com.tema.players;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.KnightConstants;
import com.tema.constants.WizardConstants;

public class Wizard extends Player implements Visitable {

    private int unmodifiedDamage;

    public Wizard(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(WizardConstants.BASE_HP);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
    }

    public void resetHP(){
        this.setCurrentHP(WizardConstants.BASE_HP + this.getLevel() * WizardConstants.LEVEL_HP);
    }

    public int getUnmodifiedDamage() {
        return unmodifiedDamage;
    }

    public void setUnmodifiedDamage(int unmodifiedDamage) {
        this.unmodifiedDamage = unmodifiedDamage;
    }
}

