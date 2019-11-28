package com.tema.abilities;

import com.tema.input.Battlefield;
import com.tema.players.Knight;
import com.tema.players.Pyromancer;
import com.tema.players.Rogue;
import com.tema.players.Wizard;

public class Backstab implements PlayerVisitor {
    private static Backstab instance = null;

    private Backstab(){}
    public static Backstab getInstance() {
        if(instance == null) {
            instance = new Backstab();
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
