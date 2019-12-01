package players;

import abilities.PlayerVisitor;
import constants.KnightConstants;
import constants.RogueConstants;
import constants.WizardConstants;

public class Rogue extends Player implements Visitable{
    private int nrBackstabHits = 0;
    public Rogue(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(RogueConstants.BASE_HP);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
    }

    public void resetHP(){
        this.setCurrentHP(RogueConstants.BASE_HP + this.getLevel() * RogueConstants.LEVEL_HP);
    }

    public int getNrBackstabHits() {
        return nrBackstabHits;
    }

    public void setNrBackstabHits(int nrBackstabHits) {
        this.nrBackstabHits = nrBackstabHits;
    }
}
