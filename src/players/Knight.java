package players;

import main.PlayersVisitor;
import angels.AngelVisitor;
import constants.KnightConstants;
import constants.PlayerConstants;

/**
 * Knight player, extension of Player class.
 */
public final class Knight extends Player implements Visitable {
    private String type = "Knight";

    public Knight(final int rowPos, final int columnPos, final int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(KnightConstants.BASE_HP);
    }


    public void accept(final PlayersVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(final AngelVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Method used by the knight to level up.
     */
    public void checkLevelUp() {

        int newLevel = (this.getCurrentXP() - PlayerConstants.LEVEL_UP_BASE)
                / PlayerConstants.LEVEL_UP_MULTIPLIER + 1;

        if (this.getCurrentXP() < PlayerConstants.LEVEL_UP_BASE) {
            newLevel = 0;
        }

        if (newLevel > this.getLevel()) {
            this.setCurrentHP(KnightConstants.BASE_HP + newLevel * KnightConstants.LEVEL_HP);
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
