package players;

import abilities.PlayerVisitor;
import constants.UniversalConstants;
import constants.WizardConstants;

/**
 * An implementation of a Wizard player, extension of a Player class
 */
public class Wizard extends Player implements Visitable {

    private int unmodifiedDamage;           //Total round damage without race modifiers


    public Wizard(final int rowPos, final int columnPos, final int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(WizardConstants.BASE_HP);
        this.setPriority(false);
    }


    public final void accept(final PlayerVisitor visitor) {
        visitor.visit(this);
    }


    public final void checkLevelUp() {

        int newLevel = (this.getCurrentXP() - UniversalConstants.LEVEL_UP_BASE)
                / UniversalConstants.LEVEL_UP_MULTIPLIER + 1;

        if (newLevel > this.getLevel()) {
            this.setCurrentHP(WizardConstants.BASE_HP + newLevel * WizardConstants.LEVEL_HP);
            this.setLevel(newLevel);
        }
    }


    public final int getUnmodifiedDamage() {
        return unmodifiedDamage;
    }

    public final void setUnmodifiedDamage(final int unmodifiedDamage) {
        this.unmodifiedDamage = unmodifiedDamage;
    }
}

