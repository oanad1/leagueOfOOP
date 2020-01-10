package players;

import main.PlayersVisitor;
import angels.AngelVisitor;
import constants.PlayerConstants;
import constants.WizardConstants;

/**
 * Wizard player, extension of a Player class.
 */
public final class Wizard extends Player implements Visitable {

    private int unmodifiedDamage;           //Total round damage without race modifiers
    private String type = "Wizard";

    public Wizard(final int rowPos, final int columnPos, final int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(WizardConstants.BASE_HP);
        this.setPriority(false);
    }


    public void accept(final PlayersVisitor visitor) {
        visitor.visit(this);
    }

    public void accept(final AngelVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * Method used by the wizard to level up.
     */
    public void checkLevelUp() {

        int newLevel = (this.getCurrentXP() - PlayerConstants.LEVEL_UP_BASE)
                / PlayerConstants.LEVEL_UP_MULTIPLIER + 1;

        if (this.getCurrentXP() < PlayerConstants.LEVEL_UP_BASE) {
            newLevel = 0;
        }

        if (newLevel > this.getLevel()) {
            this.setCurrentHP(WizardConstants.BASE_HP + newLevel * WizardConstants.LEVEL_HP);
            this.setLevel(newLevel);
        }
    }


    public int getUnmodifiedDamage() {
        return unmodifiedDamage;
    }

    public void setUnmodifiedDamage(final int unmodifiedDamage) {
        this.unmodifiedDamage = unmodifiedDamage;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}

