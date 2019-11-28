package com.tema.abilities;

import com.tema.players.Knight;
import com.tema.players.Pyromancer;
import com.tema.players.Rogue;
import com.tema.players.Wizard;

public class Ignite implements PlayerVisitor {
    private static Ignite instance = null;

    private Ignite(){}
    public static Ignite getInstance() {
        if(instance == null) {
            instance = new Ignite();
        }
        return instance;
    }

    public void visit(Pyromancer pyromancer) {

    }

    public void visit(Rogue rogue) {

    }

    public void visit(Wizard wizard) {

    }

    public void visit(Knight knight) {

    }
}
