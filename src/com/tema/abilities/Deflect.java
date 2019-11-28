package com.tema.abilities;

import com.tema.players.Knight;
import com.tema.players.Pyromancer;
import com.tema.players.Rogue;
import com.tema.players.Wizard;

public class Deflect implements PlayerVisitor {
    private static Deflect instance = null;

    private Deflect(){}
    public static Deflect getInstance() {
        if(instance == null) {
            instance = new Deflect();
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
