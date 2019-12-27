package angels;

import constants.AngelConstants;
import input.Battlefield;
import players.Knight;
import players.Pyromancer;
import players.Rogue;
import players.Wizard;

public class Spawner implements AngelVisitor {

    private static Spawner instance = null;

    private Spawner() { }
    public static Spawner getInstance() {
        if (instance == null) {
            instance = new Spawner();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
     if(pyromancer.isDead()){
         pyromancer.setDead(false);
         Battlefield.getInstance().addPlayer(pyromancer);
         pyromancer.setCurrentHP(AngelConstants.SPAWNER_HP_P);
     }
    }

    @Override
    public void visit(Rogue rogue) {
        if(rogue.isDead()){
            rogue.setDead(false);
            Battlefield.getInstance().addPlayer(rogue);
            rogue.setCurrentHP(AngelConstants.SPAWNER_HP_R);
        }
    }

    @Override
    public void visit(Wizard wizard) {
        if(wizard.isDead()){
            wizard.setDead(false);
            Battlefield.getInstance().addPlayer(wizard);
            wizard.setCurrentHP(AngelConstants.SPAWNER_HP_W);
        }
    }

    @Override
    public void visit(Knight knight) {
        if(knight.isDead()){
            knight.setDead(false);
            Battlefield.getInstance().addPlayer(knight);
            knight.setCurrentHP(AngelConstants.SPAWNER_HP_K);
        }
    }
}
