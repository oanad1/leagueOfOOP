package players;

import abilities.PlayerVisitor;
import constants.KnightConstants;
import constants.WizardConstants;

public class Wizard extends Player implements Visitable {

    private int unmodifiedDamage;

    public Wizard(int rowPos, int columnPos, int id) {
        super(rowPos, columnPos, id);
        this.setCurrentHP(WizardConstants.BASE_HP);
        this.setPriority(false);
    }

    public void accept(PlayerVisitor visitor) {
        visitor.visit(this);
    }

    public void resetHP(){
        this.setCurrentHP(WizardConstants.BASE_HP + this.getLevel() * WizardConstants.LEVEL_HP);
    }

    public int getUnmodifiedDamage() {
        return unmodifiedDamage;
    }

    public void setUnmodifiedDamage(int unmodifiedDamage) {
        this.unmodifiedDamage = unmodifiedDamage;
    }
}

