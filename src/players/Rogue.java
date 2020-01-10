package players;

import main.PlayersVisitor;
import angels.AngelVisitor;
import constants.RogueConstants;
import constants.PlayerConstants;

/**
 * Rogue player, extension of Player class.
 */
public final class Rogue extends Player implements Visitable {

    private int nrBackstabHits = 0;   //The number of backstab hits applied by this player
    private String type  = "Rogue";

    public Rogue(final int rowPos, final int columnPos, final int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(RogueConstants.BASE_HP);
    }


    public void accept(final PlayersVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(final AngelVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Method used by the rogue to level up.
     */
    public void checkLevelUp() {

        int newLevel = (this.getCurrentXP() - PlayerConstants.LEVEL_UP_BASE)
                / PlayerConstants.LEVEL_UP_MULTIPLIER + 1;

        if (this.getCurrentXP() < PlayerConstants.LEVEL_UP_BASE) {
            newLevel = 0;
        }

        if (newLevel > this.getLevel()) {
            this.setCurrentHP(RogueConstants.BASE_HP + newLevel * RogueConstants.LEVEL_HP);
            this.setLevel(newLevel);
        }
    }


    public int getNrBackstabHits() {
        return nrBackstabHits;
    }

    public void setNrBackstabHits(final int nrBackstabHits) {
        this.nrBackstabHits = nrBackstabHits;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}
