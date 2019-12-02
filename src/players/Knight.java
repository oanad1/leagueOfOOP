package players;

import abilities.PlayerVisitor;
import constants.KnightConstants;
import constants.UniversalConstants;

/**
 * An implementation of a Knight player, extension of a Player class.
 */
public class Knight extends Player implements Visitable {

    public Knight(final int rowPos, final int columnPos, final int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(KnightConstants.BASE_HP);
    }


    public final void accept(final PlayerVisitor visitor) {
        visitor.visit(this);
    }


    public final void checkLevelUp() {

        int newLevel = (this.getCurrentXP() - UniversalConstants.LEVEL_UP_BASE)
                / UniversalConstants.LEVEL_UP_MULTIPLIER + 1;

        if (newLevel > this.getLevel()) {
            this.setCurrentHP(KnightConstants.BASE_HP + newLevel * KnightConstants.LEVEL_HP);
            this.setLevel(newLevel);
        }
    }
}
