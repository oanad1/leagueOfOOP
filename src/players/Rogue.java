package players;

import abilities.PlayerVisitor;
import constants.RogueConstants;
import constants.UniversalConstants;

/**
 * An implementation of a Rogue player, extension of a Player class.
 */
public class Rogue extends Player implements Visitable {

    private int nrBackstabHits = 0;   //The number of backstab hits applied by this player


    public Rogue(final int rowPos, final int columnPos, final int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(RogueConstants.BASE_HP);
    }


    public final void accept(final PlayerVisitor visitor) {
        visitor.visit(this);
    }


    public final void checkLevelUp() {

        int newLevel = (this.getCurrentXP() - UniversalConstants.LEVEL_UP_BASE)
                / UniversalConstants.LEVEL_UP_MULTIPLIER + 1;

        if (newLevel > this.getLevel()) {
            this.setCurrentHP(RogueConstants.BASE_HP + newLevel * RogueConstants.LEVEL_HP);
            this.setLevel(newLevel);
        }
    }


    public final int getNrBackstabHits() {
        return nrBackstabHits;
    }

    public final void setNrBackstabHits(final int nrBackstabHits) {
        this.nrBackstabHits = nrBackstabHits;
    }
}
