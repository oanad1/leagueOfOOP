package abilities;

import input.Battlefield;
import players.*;

public class FightMode implements PlayerVisitor{
    private static FightMode instance = null;
    private Battlefield battlefield = Battlefield.getInstance();
    private int roundNr;

    private FightMode(){}
    public static FightMode getInstance() {
        if(instance == null) {
            instance = new FightMode();
        }
        return instance;
    }

    public int getRoundNr() {
        return roundNr;
    }

    public void setRoundNr(int roundNr) {
        this.roundNr = roundNr;
    }

    public void visit(Rogue rogue){
        Visitable opponent = battlefield.GetOpponent(rogue);
        opponent.accept(Backstab.getInstance());
        opponent.accept(Paralysis.getInstance());
    }

    public void visit(Pyromancer pyromancer){
        Visitable opponent = battlefield.GetOpponent(pyromancer);
        opponent.accept(Fireblast.getInstance());
        opponent.accept(Ignite.getInstance());
    }

    public void visit(Wizard wizard){
        Visitable opponent = battlefield.GetOpponent(wizard);
        opponent.accept(Drain.getInstance());
        opponent.accept(Deflect.getInstance());
    }

    public void visit(Knight knight){
        Visitable opponent = battlefield.GetOpponent(knight);
        opponent.accept(Execute.getInstance());
        opponent.accept(Slam.getInstance());
    }


}
