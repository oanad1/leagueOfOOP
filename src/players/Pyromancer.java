package players;

import main.PlayersVisitor;
import angels.AngelVisitor;
import constants.PyromancerConstants;
import constants.PlayerConstants;

/**
 * Pyromancer player, extension of Player class.
 */
public final class Pyromancer extends Player implements Visitable {

    private String type = "Pyromancer";

    public Pyromancer(final int rowPos, final int columnPos, final int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(PyromancerConstants.BASE_HP);
    }


    public void accept(final PlayersVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(final AngelVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Method used by the pyromancer to level up.
     */
    public void checkLevelUp() {

        int newLevel = (this.getCurrentXP() - PlayerConstants.LEVEL_UP_BASE)
                / PlayerConstants.LEVEL_UP_MULTIPLIER + 1;

        if (this.getCurrentXP() < PlayerConstants.LEVEL_UP_BASE) {
            newLevel = 0;
        }

        if (newLevel > this.getLevel()) {
            this.setCurrentHP(PyromancerConstants.BASE_HP
                    + newLevel * PyromancerConstants.LEVEL_HP);
            this.setLevel(newLevel);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

}
