package com.tema.abilities;

import com.tema.abilities.PlayerVisitor;
import com.tema.players.Knight;
import com.tema.players.Pyromancer;
import com.tema.players.Rogue;
import com.tema.players.Wizard;

public class Slam implements PlayerVisitor {
    private static Slam instance = null;

    private Slam(){}
    public static Slam getInstance() {
        if(instance == null) {
            instance = new Slam();
        }
        return instance;
    }

    @Override
    public void visit(Pyromancer pyromancer) {

    }

    @Override
    public void visit(Rogue rogue) {

    }

    @Override
    public void visit(Wizard wizard) {

    }

    @Override
    public void visit(Knight knight) {

    }
}
