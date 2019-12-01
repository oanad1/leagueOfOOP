package com.tema.players;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.WizardConstants;

public class Wizard extends Player implements Visitable {

    private int unmodifiedDamage;

    public Wizard(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
        this.setCurrentHP(WizardConstants.BASE_HP);
    }
    public void LevelUp() {
        this.setCurrentHP(WizardConstants.BASE_HP);
    }

    public int getUnmodifiedDamage() {
        return unmodifiedDamage;
    }

    public void setUnmodifiedDamage(int unmodifiedDamage) {
        this.unmodifiedDamage = unmodifiedDamage;
    }
}

