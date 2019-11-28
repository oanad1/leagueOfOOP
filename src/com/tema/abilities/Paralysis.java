package com.tema.abilities;

import com.tema.players.Knight;
import com.tema.players.Pyromancer;
import com.tema.players.Rogue;
import com.tema.players.Wizard;

public class Paralysis implements PlayerVisitor {
    private static Paralysis instance = null;

    private Paralysis(){}
    public static Paralysis getInstance() {
        if(instance == null) {
            instance = new Paralysis();
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
