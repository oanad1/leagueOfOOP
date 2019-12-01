package players;

import abilities.PlayerVisitor;
import constants.PyromancerConstants;
import constants.UniversalConstants;

public class Pyromancer extends Player implements Visitable {

    public Pyromancer(final int rowPos, final int columnPos, final int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(PyromancerConstants.BASE_HP);
    }

    public final void accept(final PlayerVisitor visitor) {
        visitor.visit(this);
    }

    public final void checkLevelUp() {

        int newLevel = (this.getCurrentXP() - UniversalConstants.LEVEL_UP_BASE)
                / UniversalConstants.LEVEL_UP_MULTIPLIER + 1;

        if (newLevel > this.getLevel()) {
            this.setCurrentHP(PyromancerConstants.BASE_HP
                    + newLevel * PyromancerConstants.LEVEL_HP);
            this.setLevel(newLevel);
        }
    }
}
