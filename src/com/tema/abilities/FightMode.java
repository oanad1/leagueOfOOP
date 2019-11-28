package com.tema.abilities;

import com.tema.input.Battlefield;
import com.tema.players.*;

public class FightMode implements PlayerVisitor{
    private static FightMode instance = null;

    private FightMode(){}
    public static FightMode getInstance() {
        if(instance == null) {
            instance = new FightMode();
        }
        return instance;
    }

    public void visit(Rogue rogue){
        Visitable opponent = GetOpponent(rogue);
        opponent.accept(Backstab.getInstance());
        opponent.accept(Paralysis.getInstance());
    }

    public void visit(Pyromancer pyromancer){
        Visitable opponent = GetOpponent(pyromancer);
        opponent.accept(Fireblast.getInstance());
        opponent.accept(Ignite.getInstance());
    }

    public void visit(Wizard wizard){
        Visitable opponent = GetOpponent(wizard);
        opponent.accept(Drain.getInstance());
        opponent.accept(Deflect.getInstance());
    }

    public void visit(Knight knight){
        Visitable opponent = GetOpponent(knight);
        opponent.accept(Execute.getInstance());
        opponent.accept(Slam.getInstance());
    }

    private Player GetOpponent(Player assailant){
        Battlefield battlefield = Battlefield.getInstance();
        Battlefield.Lot lot = battlefield.getLot(assailant);
        Player opponent = null;

        for(Player p: lot.getOccupants()){
            if(!p.equals(assailant)) {
                opponent = p;
            }
        }
        return opponent;
    }
}
