package angels;

import input.Battlefield;
import players.*;

public class TheDoomer implements AngelVisitor {

    private static TheDoomer instance = null;

    private TheDoomer() { }
    public static TheDoomer getInstance() {
        if (instance == null) {
            instance = new TheDoomer();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {
       kill(pyromancer);
    }

    @Override
    public void visit(Rogue rogue) {
        kill(rogue);
    }

    @Override
    public void visit(Wizard wizard) {
        kill(wizard);
    }

    @Override
    public void visit(Knight knight) {
        kill(knight);
    }

    public void kill(Player player) {
        if(!player.isDead()){
            player.setDead(true);
            Battlefield.getInstance().removePlayer(player);
        }
    }
}
