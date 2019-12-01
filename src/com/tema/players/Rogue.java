package com.tema.players;

import com.tema.abilities.PlayerVisitor;
import com.tema.constants.RogueConstants;
import com.tema.constants.WizardConstants;

public class Rogue extends Player implements Visitable{
    private int nrBackstabHits = 0;
    public Rogue(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
        this.setCurrentHP(RogueConstants.BASE_HP);
    }
    public void LevelUp() {
        this.setCurrentHP(RogueConstants.BASE_HP);
    }

    public int getNrBackstabHits() {
        return nrBackstabHits;
    }

    public void setNrBackstabHits(int nrBackstabHits) {
        this.nrBackstabHits = nrBackstabHits;
    }
}
