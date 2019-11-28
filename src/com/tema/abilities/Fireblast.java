package com.tema.abilities;

import com.tema.players.Knight;
import com.tema.players.Pyromancer;
import com.tema.players.Rogue;
import com.tema.players.Wizard;

public class Fireblast implements PlayerVisitor {
    private static Fireblast instance = null;

    private Fireblast(){}
    public static Fireblast getInstance() {
        if(instance == null) {
            instance = new Fireblast();
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
